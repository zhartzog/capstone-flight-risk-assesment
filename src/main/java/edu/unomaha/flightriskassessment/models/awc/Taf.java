package edu.unomaha.flightriskassessment.models.awc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is the root class for unmarshalling TAF data recieved from the Aviation Weather Center (AWC). The schema for the
 * XML file can be found at https://aviationweather.gov/docs/dataserver/schema/taf1_2.xsd
 */
@XmlRootElement(name = "TAF")
public class Taf
{
    private static final Logger logger = LogManager.getLogger(Taf.class);

    private String rawText;

    private String airportID;

    private String validFrom;

    private String validTo;

    private List<TafForecast> forecastList;

    public String getRawText()
    {
        return rawText;
    }

    @XmlElement(name = "raw_text" )
    public void setRawText(String rawText)
    {
        this.rawText = rawText;
    }

    public String getAirportID()
    {
        return airportID;
    }

    @XmlElement(name = "station_id" )
    public void setAirportID(String airportID)
    {
        this.airportID = airportID;
    }

    public String getValidFrom()
    {
        return validFrom;
    }

    @XmlElement(name = "valid_time_from" )
    public void setValidFrom(String validFrom)
    {
        this.validFrom = validFrom;
    }

    public String getValidTo()
    {
        return validTo;
    }

    @XmlElement(name = "valid_time_to" )
    public void setValidTo(String validTo)
    {
        this.validTo = validTo;
    }

    public List<TafForecast> getForecastList()
    {
        return forecastList;
    }

    @XmlElement(name = "forecast" )
    public void setForecastList(List<TafForecast> forecastList)
    {
        this.forecastList = forecastList;
    }
}
