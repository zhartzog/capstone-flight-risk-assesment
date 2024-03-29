package edu.unomaha.flightriskassessment.models.awc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.unomaha.flightriskassessment.models.awc.components.SkyCondition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to unmarshall METAR data received from the Aviation Weather Center. The schema for the XML file can
 * be found at https://aviationweather.gov/docs/dataserver/schema/metar1_2.xsd
 */
//@Entity
@XmlRootElement(name = "METAR")
public class Metar
{
	//@Id
    //@GeneratedValue (strategy=GenerationType.AUTO)
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

    private List<SkyCondition> skyCoverage = new ArrayList<>();

    //Code for current weather. Legend can be found at https://aviationweather.gov/docs/metar/wxSymbols_anno2.pdf
    private String presentWeather;

    private float precipitation;

    private float snow;

    private int verticalVis; //When the ceilings get low skyCover will be null and the the field will be filled.

    public Metar(){}

    public Metar(String airportID, String time, float temperature, float dewPoint, int windDirection, int windSpeed, int windGust, float visibility, String flightCategory, String skyCoverage, int cloudBases)
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
        logger.debug("Setting Airport ID to " + airportID);
        this.airportID = airportID;
    }

    public String getTime() { return time; }

    @XmlElement(name = "observation_time")
    public void setTime(String time)
    {
        logger.debug("Setting time to " + time);
        this.time = time;
    }

    public float getTemperature() { return temperature; }

    @XmlElement(name = "temp_c")
    public void setTemperature(float temperature) {
        logger.debug("Setting temperature to " + temperature);
        this.temperature = temperature;
    }

    public float getDewPoint() { return dewPoint; }

    @XmlElement(name = "dewpoint_c")
    public void setDewPoint(float dewPoint) {
        logger.debug("Setting dew point to " + dewPoint);
        this.dewPoint = dewPoint;
    }

    public int getWindDirection() { return windDirection; }

    @XmlElement(name = "wind_dir_degrees")
    public void setWindDirection(int windDirection) {
        logger.debug("Setting wind direction to " + windDirection);
        this.windDirection = windDirection;
    }

    public int getWindSpeed() { return windSpeed; }

    @XmlElement(name = "wind_speed_kt")
    public void setWindSpeed(int windSpeed) {
    	logger.debug("Setting wind speed to " + windSpeed);
        this.windSpeed = windSpeed;
    }

    public int getWindGust() { return windGust; }

    @XmlElement(name = "wind_gust_kt")
    public void setWindGust(int windGust) {
    	logger.debug("Setting wind gust to " + windGust);
        this.windGust = windGust;
    }

    public float getVisibility() { return visibility; }

    @XmlElement(name = "visibility_statute_mi")
    public void setVisibility(float visibility) {
    	logger.debug("Setting visibility to " + visibility);
        this.visibility = visibility;
    }

    public String getFlightCategory() { return flightCategory; }

    @XmlElement(name = "flight_category")
    public void setFlightCategory(String flightCategory) {
    	logger.debug("Setting flight category to " + flightCategory);
        this.flightCategory = flightCategory;
    }

    public List<SkyCondition> getSkyCoverage()
    {
        return skyCoverage;
    }

    @XmlElement(name = "sky_condition")
    public void setSkyCoverage(List<SkyCondition> skyCoverage)
    {
        this.skyCoverage = skyCoverage;
    }

    public float getPrecipitation()
    {
        return precipitation;
    }

    @XmlElement(name = "precip_in")
    public void setPrecipitation(float precipitation)
    {
        this.precipitation = precipitation;
    }

    public float getSnow()
    {
        return snow;
    }

    @XmlElement(name = "snow_in")
    public void setSnow(float snow)
    {
        this.snow = snow;
    }

    public int getVerticalVis()
    {
        return verticalVis;
    }

    @XmlElement(name = "vert_vis_ft")
    public void setVerticalVis(int verticalVis)
    {
        this.verticalVis = verticalVis;
    }

    public String getPresentWeather()
    {
        return presentWeather;
    }

    @XmlElement(name = "wx_string")
    public void setPresentWeather(String presentWeather)
    {
        this.presentWeather = presentWeather;
    }

    /*Returns winds as a string. For example it will return the winds 180@12G20 or 030@04*/
    public String getWindsAsString()
    {
        System.out.println(toString());
        String winds = this.windDirection+"@"+this.windSpeed;
        if(this.windGust > 0)
            winds = winds + "G" + this.windGust;
        System.out.println(winds);
        return winds;
    }

    @Override
    public String toString()
    {
        return "Metar{" +
                "rawText='" + rawText + '\'' +
                ", airportID='" + airportID + '\'' +
                ", time='" + time + '\'' +
                ", temperature=" + temperature +
                ", dewPoint=" + dewPoint +
                ", windDirection=" + windDirection +
                ", windSpeed=" + windSpeed +
                ", windGust=" + windGust +
                ", visibility=" + visibility +
                ", flightCategory='" + flightCategory + '\'' +
                ", skyCoverage=" + skyCoverage +
                ", presentWeather='" + presentWeather + '\'' +
                ", precipitation=" + precipitation +
                ", snow=" + snow +
                ", verticalVis=" + verticalVis +
                '}';
    }
}
