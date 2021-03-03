package edu.unomaha.flightriskassessment.models;

import edu.unomaha.flightriskassessment.services.MetarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Metar
{
    private static final Logger logger = LogManager.getLogger(Metar.class);

    private String rawText;

    private String airportID;

    private String time;

    private float temperature;

    private float dewPoint;

    private int windDirection;

    private int windSpeed;

    private int windGust;

    private float visibility;

    private String flightCategory;

    private String skyCoverage;

    private String cloudBases;

    public Metar(){};
    public Metar(String airportID, String time, float temperature, float dewPoint, int windDirection, int windSpeed, int windGust, float visibility, String flightCategory, String skyCoverage, String cloudBases)
    {
        logger.info("Creating METAR entry...");
        logger.debug("Metar[airportID: %s, time: %s, temperature: %d, dewPoint: %d, windDirection: %d, windSpeed: %d, windGust: %d, vis: %d, flightCategory: %d, skyCoverage: %d, cloudBases: %d",
                airportID, time, temperature, dewPoint, windDirection, windSpeed, windGust, visibility, flightCategory, skyCoverage, cloudBases);

        this.airportID = airportID;
        this.time = time;
        this.temperature = temperature;
        this.dewPoint = dewPoint;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.visibility = visibility;
        this.flightCategory = flightCategory;
        this.skyCoverage = skyCoverage;
        this.cloudBases = cloudBases;
    }

    public String getRawText()
    {
        return rawText;
    }

    public void setRawText(String rawText)
    {
        logger.debug("Setting rawText to : "+rawText);
        this.rawText = rawText;
    }

    public String getAirportID()
    {
        return airportID;
    }

    public void setAirportID(String airportID)
    {
        logger.debug("Setting Airport ID to %s",airportID);
        this.airportID = airportID;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        logger.debug("Setting time to %s",time);
        this.time = time;
    }

    public float getTemperature()
    {
        return temperature;
    }

    public void setTemperature(float temperature)
    {
        logger.debug("Setting temperature to %d",temperature);
        this.temperature = temperature;
    }

    public float getDewPoint()
    {
        return dewPoint;
    }

    public void setDewPoint(float dewPoint)
    {
        logger.debug("Setting dew point to %d",dewPoint);
        this.dewPoint = dewPoint;
    }

    public int getWindDirection()
    {
        return windDirection;
    }

    public void setWindDirection(int windDirection)
    {
        logger.debug("Setting wind direction to %d",windDirection);
        this.windDirection = windDirection;
    }
    //TODO: Finish setting log statements
    public int getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public int getWindGust()
    {
        return windGust;
    }

    public void setWindGust(int windGust)
    {
        this.windGust = windGust;
    }

    public float getVisibility()
    {
        return visibility;
    }

    public void setVisibility(float visibility)
    {
        this.visibility = visibility;
    }

    public String getFlightCategory()
    {
        return flightCategory;
    }

    public void setFlightCategory(String flightCategory)
    {
        this.flightCategory = flightCategory;
    }

    public String getSkyCoverage()
    {
        return skyCoverage;
    }

    public void setSkyCoverage(String skyCoverage)
    {
        this.skyCoverage = skyCoverage;
    }

    public String getCloudBases()
    {
        return cloudBases;
    }

    public void setCloudBases(String cloudBases)
    {
        this.cloudBases = cloudBases;
    }
}
