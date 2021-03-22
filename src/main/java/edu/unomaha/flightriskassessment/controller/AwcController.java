package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.models.awc.AirSigmet;
import edu.unomaha.flightriskassessment.models.awc.Metar;
import edu.unomaha.flightriskassessment.models.awc.Pirep;
import edu.unomaha.flightriskassessment.models.awc.Taf;
import edu.unomaha.flightriskassessment.services.AWCServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AwcController
{
    private static final Logger              logger = LogManager.getLogger(AwcController.class);

    @Autowired
    private AWCServices awcServices = new AWCServices();

    @GetMapping(path="/getMetar")
    public @ResponseBody
    Metar getMetarData(String airportID)
    {
        logger.info("Beginning getMetarData request function...");
        return awcServices.getMetarData(airportID);
    }

    @GetMapping(path="/getTaf")
    public @ResponseBody
    Taf getTafData(@RequestParam int radius, @RequestParam String latLong)
    {
        logger.info("Beginning getTafData...");
        return awcServices.getTafData(radius, latLong);

    }

    @GetMapping(path="/getAirSigmet")
    public @ResponseBody
    List<AirSigmet> getAirSigmet()
    {
        logger.info("Beginning getAirSigmet...");
        return awcServices.getAirSigmet();

    }

    @GetMapping(path="/getPireps")
    public @ResponseBody
    List<Pirep> getPireps(@RequestParam int radius, @RequestParam String latLong)
    {
        logger.info("Beginning getPirep request...");
        return awcServices.getPireps(radius,latLong);

    }

}
