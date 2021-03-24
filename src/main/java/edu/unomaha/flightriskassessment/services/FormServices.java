package edu.unomaha.flightriskassessment.services;

import edu.unomaha.flightriskassessment.models.Form.AdditionalQuestions;
import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import edu.unomaha.flightriskassessment.models.awc.AirSigmet;
import edu.unomaha.flightriskassessment.models.awc.Metar;
import edu.unomaha.flightriskassessment.models.awc.Pirep;
import edu.unomaha.flightriskassessment.models.awc.Taf;
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

    private AdditionalQuestions additionalQuestions;

    private Metar metar;
    private Taf taf;
    private AirSigmet   airSigmet;
    private List<Pirep> pireps;


    public String getDynamicQuestion(BasicFormInput input)
    {
        additionalQuestions = new AdditionalQuestions();

        //Get required data
        metar = awcServices.getMetarData(input.getDeparture_airport());

        additionalQuestions.setInstrumentCurrent( isIFR(input.getDeparture_date_time() ) );
        return "Not done";
    }

    private boolean isIFR(String time)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/DD/yyyy HH:mm");

        try
        {
            Date input = dateFormatter.parse(time);
            Date now = new Date();
            long difference = TimeUnit.MINUTES.convert(Math.abs(input.getTime() - now.getTime()), TimeUnit.MILLISECONDS);

            if(difference < 60) //Departure time is within an hour, use METAR data
            {
                return metar.getFlightCategory().equals("IFR");
            }
            else
            {
                //TODO: Check IFR with TAF data
            }

        } catch ( ParseException e )
        {
            e.printStackTrace();
        }
        return false;
    }
}
