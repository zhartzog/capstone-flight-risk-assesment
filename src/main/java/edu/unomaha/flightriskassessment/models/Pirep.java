package edu.unomaha.flightriskassessment.models;

import edu.unomaha.flightriskassessment.models.components.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "AircraftReport")
public class Pirep
{
    //Raw text of the PIREP
    private String raw_text;
    //Time of the observation. ISO 8601 date/time format
    private String time;
    //Type of aircraft or flight number
    private String aircraftType;
    //Latitude of the observation
    private float latitude;
    //Longitude of the observation
    private float longitude;
    //Altitude of report in MSL
    private int altitude;
    //Reported sky condition
    private SkyCondition    skyCondition;
    // Reported turbulence conditions.
    private PirepTurbulence turbulence;
    // Reported icing condition.
    private PirepIcing icing;
    // Reported visibility in statute miles
    private int visibility;
    // Uncategorized weather
    private String wxString;
    // Temperature reported
    private float temperature;
    // Wind direction
    private int windDegrees;
    // Wind speeds in knots
    private int windSpeed;
    // vertical wind gusts reported in meters per second.
    private int verticalGusts;


    public String getRaw_text()
    {
        return raw_text;
    }

    @XmlElement( name = "raw_text" )
    public void setRaw_text(String raw_text)
    {
        this.raw_text = raw_text;
    }

    public String getTime()
    {
        return time;
    }

    @XmlElement( name = "observation_time" )
    public void setTime(String time)
    {
        this.time = time;
    }

    @XmlElement( name = "aircraft_ref" )
    public void setAircraftType(String aircraftType)
    {
        this.aircraftType = aircraftType;
    }

    public String getAircraftType()
    {
        return aircraftType;
    }

    public float getLatitude()
    {
        return latitude;
    }

    @XmlElement( name = "latitude" )
    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    @XmlElement( name = "longitude" )
    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public int getAltitude()
    {
        return altitude;
    }

    @XmlElement( name = "altitude_ft_msl" )
    public void setAltitude(int altitude)
    {
        this.altitude = altitude;
    }

    public SkyCondition getSkyCondition()
    {
        return skyCondition;
    }

    @XmlElement( name = "sky_condition" )
    public void setSkyCondition(SkyCondition skyCondition)
    {
        this.skyCondition = skyCondition;
    }

    public PirepTurbulence getTurbulence()
    {
        return turbulence;
    }

    @XmlElement( name = "turbulence_condition" )
    public void setTurbulence(PirepTurbulence turbulence)
    {
        this.turbulence = turbulence;
    }

    public PirepIcing getIcing()
    {
        return icing;
    }

    @XmlElement( name = "icing_condition" )
    public void setIcing(PirepIcing icing)
    {
        this.icing = icing;
    }

    public int getVisibility()
    {
        return visibility;
    }

    @XmlElement( name = "visibility_statute_mi" )
    public void setVisibility(int visibiliy)
    {
        this.visibility = visibiliy;
    }

    public String getWxString()
    {
        return wxString;
    }

    @XmlElement( name = "wx_string" )
    public void setWxString(String wxString)
    {
        this.wxString = wxString;
    }

    public float getTemperature()
    {
        return temperature;
    }

    @XmlElement( name = "temp_c" )
    public void setTemperature(float temperature)
    {
        this.temperature = temperature;
    }

    public int getWindDegrees()
    {
        return windDegrees;
    }

    @XmlElement( name = "wind_dir_degrees" )
    public void setWindDegrees(int windDegrees)
    {
        this.windDegrees = windDegrees;
    }

    public int getWindSpeed()
    {
        return windSpeed;
    }

    @XmlElement( name = "wind_speed_kt" )
    public void setWindSpeed(int windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public int getVerticalGusts()
    {
        return verticalGusts;
    }

    @XmlElement( name = "vert_gust_kt" )
    public void setVerticalGusts(int verticalGusts)
    {
        this.verticalGusts = verticalGusts;
    }
}
