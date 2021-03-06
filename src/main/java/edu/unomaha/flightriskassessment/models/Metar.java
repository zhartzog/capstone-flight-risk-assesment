package edu.unomaha.flightriskassessment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@XmlRootElement(name = "METAR")
public class Metar
{
	@Id
    @GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;

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

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getRawText() { return rawText; }

    @XmlElement(name = "raw_text")
    public void setRawText(String rawText) {
        logger.debug("Setting rawText to : " + rawText);
        this.rawText = rawText;
    }

    public String getAirportID() { return airportID; }

    @XmlElement(name = "station_id")
    public void setAirportID(String airportID) {
        logger.debug("Setting Airport ID to %s", airportID);
        this.airportID = airportID;
    }

    public String getTime() { return time; }

    @XmlElement(name = "observation_time")
    public void setTime(String time)
    {
        logger.debug("Setting time to %s",time);
        this.time = time;
    }

    public float getTemperature() { return temperature; }

    @XmlElement(name = "temp_c")
    public void setTemperature(float temperature) {
        logger.debug("Setting temperature to %d", temperature);
        this.temperature = temperature;
    }

    public float getDewPoint() { return dewPoint; }

    @XmlElement(name = "dewpoint_c")
    public void setDewPoint(float dewPoint) {
        logger.debug("Setting dew point to %d", dewPoint);
        this.dewPoint = dewPoint;
    }

    public int getWindDirection() { return windDirection; }

    @XmlElement(name = "wind_dir_degrees")
    public void setWindDirection(int windDirection) {
        logger.debug("Setting wind direction to %d", windDirection);
        this.windDirection = windDirection;
    }

    public int getWindSpeed() { return windSpeed; }

    @XmlElement(name = "wind_speed_kt")
    public void setWindSpeed(int windSpeed) {
    	logger.debug("Setting wind speed to %d", windSpeed);
        this.windSpeed = windSpeed;
    }

    public int getWindGust() { return windGust; }

    @XmlElement(name = "wind_gust_kt")
    public void setWindGust(int windGust) {
    	logger.debug("Setting wind gust to %d", windGust);
        this.windGust = windGust;
    }

    public float getVisibility() { return visibility; }

    @XmlElement(name = "visibility_statue_mi")
    public void setVisibility(float visibility) {
    	logger.debug("Setting visibility to %f", visibility);
        this.visibility = visibility;
    }

    public String getFlightCategory() { return flightCategory; }

    @XmlElement(name = "flight_category")
    public void setFlightCategory(String flightCategory) {
    	logger.debug("Setting flight category to %s", flightCategory);
        this.flightCategory = flightCategory;
    }

    public String getSkyCoverage() { return skyCoverage; }

    @XmlElement(name = "wx_string")
    public void setSkyCoverage(String skyCoverage) {
    	logger.debug("Setting sky coverage to %s", skyCoverage);
        this.skyCoverage = skyCoverage;
    }

    public String getCloudBases() { return cloudBases; }

    @XmlElement(name = "sky_cover")
    public void setCloudBases(String cloudBases) {
    	logger.debug("Setting cloud bases to %s", cloudBases);
        this.cloudBases = cloudBases;
    }
}
