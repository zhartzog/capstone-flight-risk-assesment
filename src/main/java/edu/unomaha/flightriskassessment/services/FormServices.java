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
    private static final Logger logger = LogManager.getLogger(FormServices.class);

    private static final int MAX_ALTITUDE = 6000;

    @Autowired
    private AWCServices awcServices;

    @Autowired
    private FaaServices faaServices;

    private AdditionalQuestions additionalQuestions;

    private AirportInfo airportInfo;

    private Metar       metar;
    private Taf         taf;
    private List<Pirep> pireps;
    private long        deltaTime;


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

        additionalQuestions.setDeparture_lat(airportInfo.getLatitudeDD());
        additionalQuestions.setDeparture_long(airportInfo.getLongitudeDD());
        calculateWindComponent();
        getAirSigmet();
        if(!input.getFlight_type().equals("pattern"))
            getDestinationMetars(input.getFlight_category(), input.getFlight_type(), input.getXc_destination());


        // System.out.println("LatLong: "+airportInfo.getLatLongAsString());
        additionalQuestions.setPireps(awcServices.getPireps(20, airportInfo.getLatLongAsString()));
        additionalQuestions.setMetar(metar);
        if(input.getFlight_type().equals("cross_country"))
            additionalQuestions.setAlternateMetar(awcServices.getMetarData(input.getXc_alternate()));


        return additionalQuestions;
    }

    private void calculate_delta_time(String time)
    {
        logger.info("Beginning calculate_delta_time(time: " + time + ")...");
        String pattern = "MM/dd/yyyy HH:mm";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        try
        {
            Date input = dateFormatter.parse(time);
            Date now = new Date();
            System.out.printf("Current time: %d, input time: %d\n", now.getTime(), input.getTime());
            this.deltaTime = TimeUnit.MINUTES.convert(Math.abs(input.getTime() - now.getTime()), TimeUnit.MILLISECONDS);

        } catch ( ParseException e )
        {
            e.printStackTrace();
        }
        logger.info("Delta time: " + this.deltaTime);
    }


    //TODO: ADD ability to caluclate winds with TAF/MOS data
    private void calculateWindComponent()
    {
        double[] wind = new double[]{ Double.MAX_VALUE, Double.MAX_VALUE };
        double[] gusts = new double[]{ Double.MAX_VALUE, Double.MAX_VALUE };
        String primaryRunway = "ERROR";
        List<Runway> runways = airportInfo.getRunways();
        //System.out.println("Runway.length: "+runways.size());
        for ( Runway i : runways )
        {
            //You can take off in both directions, need to know which direction gets a headwind.
            String[] headings = i.getDesignator().split("/");
            if ( this.deltaTime < 60 )
            {
                //System.out.printf("heading[0]: %s, H[1]: %s, metar: %d \n",headings[0],headings[1],this.metar.getWindDirection());
                //Parallel Runways will end with a 'L', 'R', or 'C' (RWY 36L). Removes this.
                if ( headings[0].charAt(headings[0].length() - 1) == 'L' || headings[0].charAt(headings[0].length() - 1) == 'R' || headings[0].charAt(headings[0].length() - 1) == 'C' )
                {
                    headings[0] = headings[0].substring(0, headings[0].length() - 1);
                    headings[1] = headings[1].substring(0, headings[1].length() - 1);
                }
                //Get the smallest difference between the wind direction and runway heading.
                int angleA = Integer.parseInt(headings[0]) * 10;
                int angleB = Integer.parseInt(headings[1]) * 10;
                int angle_a = Math.abs(angleA - this.metar.getWindDirection());
                int angle_b = Math.abs(Integer.parseInt(headings[1]) * 10 - this.metar.getWindDirection());

                //Since 360 degrees is equal to 0 degrees, we have to figure out which way is the smallest.
                if ( angleA == 360 )
                    angle_a = Math.min(angle_a, metar.getWindDirection());
                else if ( angleB == 360 )
                    angle_b = Math.min(angle_b, metar.getWindDirection());

                double angle = Math.toRadians(Math.min(angle_a, angle_b));
                //System.out.printf("Angle A: %d, Angle B: %d, angle: %.2f \n",angle_a,angle_b,angle);

                //Calculate the winds
                double crosswind = Math.sin(angle) * this.metar.getWindSpeed();
                double crosswind_gusts = Math.sin(angle) * this.metar.getWindGust();
                double headwind = Math.cos(angle) * this.metar.getWindSpeed();
                double headwind_gusts = Math.cos(angle) * this.metar.getWindGust();

                System.out.printf("HW: %.2f, XW: %.2f \n", headwind, crosswind);

                if ( wind[1] > crosswind || gusts[1] > crosswind_gusts )
                {
                    wind[0] = headwind;
                    gusts[0] = headwind_gusts;
                    wind[1] = crosswind;
                    gusts[1] = crosswind_gusts;

                    if ( angle_a < angle_b )
                        primaryRunway = "Runway " + headings[0];
                    else
                        primaryRunway = "Runway " + headings[1];

                    // System.out.printf("Set winds to %s, HW: %.2f, HWG: %.2f, XW: %.2f, XWG: %.2f\n",primaryRunway,wind[0],gusts[0],wind[1],gusts[1]);
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
                awcServices.getAirSigmet(minMax[0], minMax[1], minMax[2], minMax[3], MAX_ALTITUDE)
        );
    }

    private void getDestinationMetars(String category, String type, String destinations)
    {
        if ( category.equals("normal") )
        {
            if ( type.equals("aux_field") )
            {
                if ( this.metar.getAirportID().equals("KCBF") )
                {
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KOMA"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KTQE"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KBTA"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KFET"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KAHQ"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KMLE"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KPMV"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KAFK"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KSDA"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KICL"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KRDK"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KAIO"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KADU"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KDNS"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KOFF"));
                }
                else if ( this.metar.getAirportID().equals("KMLE") )
                {
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KOMA"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KTQE"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KBTA"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KFET"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KAHQ"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KLNK"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KPMV"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KAFK"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KSDA"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KRDK"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KAIO"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KCBF"));
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData("KOFF"));
                }
            }
            else if ( type.equals("cross_country") )
            {
                String[] dests = destinations.split(",");

                for ( String i : dests )
                {
                    this.additionalQuestions.addDestinationMetar(awcServices.getMetarData(i));
                }
            }
        }
    }


}
