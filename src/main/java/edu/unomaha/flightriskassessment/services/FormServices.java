package edu.unomaha.flightriskassessment.services;

import edu.unomaha.flightriskassessment.models.Form.AdditionalQuestions;
import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import edu.unomaha.flightriskassessment.models.awc.AirSigmet;
import edu.unomaha.flightriskassessment.models.awc.Metar;
import edu.unomaha.flightriskassessment.models.awc.Pirep;
import edu.unomaha.flightriskassessment.models.awc.Taf;
import edu.unomaha.flightriskassessment.models.faa.AirportInfo;
import edu.unomaha.flightriskassessment.models.faa.Runway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Service
public class FormServices
{
    private static final Logger      logger = LogManager.getLogger(FormServices.class);

    private static final int MAX_ALTITUDE = 6000;

    @Autowired
    private              AWCServices awcServices;

    @Autowired
    private  FaaServices faaServices;

    private AdditionalQuestions additionalQuestions;

    private AirportInfo airportInfo;

    private Metar metar;
    private Taf taf;
    private List<Pirep> pireps;
    private long deltaTime;


    //TODO: Fix errors. Not return correct values.
    public AdditionalQuestions getDynamicQuestions(BasicFormInput input)
    {
        logger.info("Beginning getDynamicQuestions...");
        additionalQuestions = new AdditionalQuestions();
        airportInfo = new AirportInfo(input.getDeparture_airport());

        //Get required data
        calculate_delta_time(input.getDeparture_date_time());
        metar = awcServices.getMetarData(input.getDeparture_airport());
        airportInfo = faaServices.getAirportInfo(input.getDeparture_airport());

        additionalQuestions.setInstrumentCurrent( isIFR() );
        calculateWindComponent();
        getAirSigmet();
        System.out.println("LatLong: "+airportInfo.getLatLongAsString());
        additionalQuestions.setPireps( awcServices.getPireps(20, airportInfo.getLatLongAsString() ) );
        additionalQuestions.setMetar(metar.getRawText());


        return additionalQuestions;
    }

    private void calculate_delta_time(String time)
    {
        logger.info("Beginning calculate_delta_time(time: "+time+")...");
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try
        {
            Date input = dateFormatter.parse(time);
            Date now = new Date();
            this.deltaTime = TimeUnit.MINUTES.convert(Math.abs(input.getTime() - now.getTime()), TimeUnit.MILLISECONDS);

        } catch ( ParseException e )
        {
            e.printStackTrace();
        }
        logger.info("Delta time: "+this.deltaTime);
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

    private void calculateWindComponent()
    {
        double[] wind = new double[]{Double.MAX_VALUE, Double.MAX_VALUE};
        double[] gusts = new double[]{Double.MAX_VALUE, Double.MAX_VALUE};
        String primaryRunway = "ERROR";
        List<Runway> runways = airportInfo.getRunways();
        System.out.println("Runway.length: "+runways.size());
        for ( Runway i: runways)
        {
            //You can take off in both directions, need to know which direction gets a headwind.
            String[] headings = i.getDesignator().split("/");
            if(this.deltaTime < 60)
            {
                System.out.printf("heading[0]: %s, H[1]: %s, metar: %d \n",headings[0],headings[1],this.metar.getWindDirection());
                //Get the smallest difference between the wind direction and runway heading.
                int angle_a = Math.abs( Integer.parseInt(headings[0])*10 -  this.metar.getWindDirection());
                int angle_b = Math.abs( Integer.parseInt(headings[1])*10 -  this.metar.getWindDirection());
                double angle = Math.toRadians( Math.min(angle_a, angle_b));

                System.out.printf("Angle A: %d, Angle B: %d, angle: %.2f \n",angle_a,angle_b,angle);

                //Calculate the winds
                double crosswind = Math.sin(angle) * this.metar.getWindSpeed();
                double crosswind_gusts = Math.sin(angle) * this.metar.getWindGust();
                double headwind = Math.cos(angle) * this.metar.getWindSpeed();
                double headwind_gusts = Math.cos(angle) * this.metar.getWindGust();

                System.out.printf("HW: %.2f, XW: %.2f \n",headwind,crosswind);

                if(wind[1] > crosswind || gusts[1] > crosswind_gusts)
                {
                    wind[0] = headwind;
                    gusts[0] = headwind_gusts;
                    wind[1] = crosswind;
                    gusts[1] = crosswind_gusts;

                    if(angle_a < angle_b)
                        primaryRunway = "Runway "+headings[0];
                    else
                        primaryRunway = "Runway "+headings[1];

                    System.out.printf("Set winds to %s, HW: %.2f, HWG: %.2f, XW: %.2f, XWG: %.2f\n",primaryRunway,wind[0],gusts[0],wind[1],gusts[1]);
                }
            }
            else
            {
                //TODO: Calculate winds with TAF data.
            }
        }

        additionalQuestions.setPrimaryRunway(primaryRunway);
        additionalQuestions.setDepartureWinds(wind);
        additionalQuestions.setDeartureWinds_gusts(gusts);

    }

    private void getAirSigmet()
    {
        int[] minMax = airportInfo.getMinMaxLatLong();
        this.additionalQuestions.setAirSigmetList(
                awcServices.getAirSigmet(minMax[0], minMax[1], minMax[2], minMax[3], MAX_ALTITUDE )
        );
    }


}
