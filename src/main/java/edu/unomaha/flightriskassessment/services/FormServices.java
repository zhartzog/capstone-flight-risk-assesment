package edu.unomaha.flightriskassessment.services;

import edu.unomaha.flightriskassessment.models.Form.AdditionalQuestions;
import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import edu.unomaha.flightriskassessment.models.awc.AirSigmet;
import edu.unomaha.flightriskassessment.models.awc.Metar;
import edu.unomaha.flightriskassessment.models.awc.Pirep;
import edu.unomaha.flightriskassessment.models.awc.Taf;
import edu.unomaha.flightriskassessment.models.faa.AirportInfo;
import edu.unomaha.flightriskassessment.models.faa.Runway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FormServices
{
    @Autowired
    private AWCServices awcServices;

    @Autowired
    private  FaaServices faaServices;

    private AdditionalQuestions additionalQuestions;

    private AirportInfo airportInfo;

    private Metar metar;
    private Taf taf;
    private List<AirSigmet>   airSigmet;
    private List<Pirep> pireps;
    private long deltaTime;


    //TODO: Fix errors. Not return correct values.
    public AdditionalQuestions getDynamicQuestions(BasicFormInput input)
    {
        System.out.println("Time: "+input.getDeparture_date_time());
        additionalQuestions = new AdditionalQuestions();

        //Get required data
        calculate_delta_time(input.getDeparture_date_time());
        metar = awcServices.getMetarData(input.getDeparture_airport());
        airSigmet = awcServices.getAirSigmet();
        airportInfo = faaServices.getAirportInfo(input.getDeparture_airport());

        additionalQuestions.setInstrumentCurrent( isIFR() );
        additionalQuestions.setDepartureWinds(calculateWindComponent(input.getDeparture_airport()));
        getAirSigmet();
        additionalQuestions.setPireps( awcServices.getPireps(15, airportInfo.getLatLongAsString() ) );


        return additionalQuestions;
    }

    private void calculate_delta_time(String time)
    {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

        try
        {
            Date input = dateFormatter.parse(time);
            Date now = new Date();

            this.deltaTime = TimeUnit.MINUTES.convert(Math.abs(input.getTime() - now.getTime()), TimeUnit.MILLISECONDS);

        } catch ( ParseException e )
        {
            e.printStackTrace();
        }
    }

    private boolean isIFR()
    {
        if(this.deltaTime < 60) //Departure time is within an hour, use METAR data
            {
                return metar.getFlightCategory().equals("IFR");
            }
            else
            {
                return false;
                //TODO: Check IFR with TAF data
            }
    }

    private double[] calculateWindComponent(String airportID)
    {
        double[] wind = new double[2];
        List<Runway> runways = airportInfo.getRunways();

        for ( Runway i: runways)
        {
            //You can take off in both directions, need to know which direction gets a headwind.
            String[] headings = i.getDesignator().split("/");
            if(this.deltaTime < 60)
            {
                //Get the smallest difference between the wind direction and runway heading.
                int angle_a = Math.abs( Integer.parseInt(headings[0])*10 -  this.metar.getWindDirection());
                int angle_b = Math.abs( Integer.parseInt(headings[1])*10 -  this.metar.getWindDirection());
                double angle = Math.toRadians( Math.min(angle_a, angle_b));

                //Calculate the winds
                double crosswind = Math.cos(angle) * this.metar.getWindSpeed();
                double crosswind_gusts = Math.cos(angle) * this.metar.getWindGust();
                double headwind = Math.cos(angle) * this.metar.getWindSpeed();
                double headwind_gusts = Math.cos(angle) * this.metar.getWindGust();

                wind[0] = Math.min( Math.min(headwind, headwind_gusts), wind[0] );
                wind[1] = Math.min( Math.min(crosswind, crosswind_gusts), wind[1] );
            }
            else
            {
                //TODO: Calculate winds with TAF data.
            }
        }

        return wind;
    }

    private void getAirSigmet()
    {
        for ( AirSigmet i: this.airSigmet)
        {
            if( i.contains(airportInfo.getLatitudeDD(), airportInfo.getLongitudeDD()))
            {
                if(i.getAltitude().getMaximum() < 6000)
                {
                    this.additionalQuestions.addAirSigmet(i);
                }
            }
        }
    }

    private void getPireps()
    {
        for( Pirep i : this.pireps)
        {
            double distance = Pirep.distance(i.getLatitude(),i.getLongitude(),airportInfo.getLatitudeDD(),airportInfo.getLongitudeDD(),"NM");
            if( distance < 20 )
            {
                this.additionalQuestions.addPirep(i);
            }
        }
    }
}
