package edu.unomaha.flightriskassessment.models.awc;

import edu.unomaha.flightriskassessment.models.awc.components.Icing;
import edu.unomaha.flightriskassessment.models.awc.components.SkyCondition;
import edu.unomaha.flightriskassessment.models.awc.components.Turbulence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecast")
public class TafForecast
{
    private static final Logger logger = LogManager.getLogger(TafForecast.class);


    private String forcast_start;

    private String forcast_end;

    private int windDirection;

    private int windSpeed;

    private int windGust;

    private Double visibility;

    private int windShearHeight;

    private int windShearDirection;

    private int getWindShearSpeed;

    private int forecastProbabilty;

    private Turbulence turbulence;

    private Icing icing;


    private List<SkyCondition> skyCoverage;

    public String getForcast_start()
    {
        return forcast_start;
    }

    @XmlElement(name = "fcst_time_from")
    public void setForcast_start(String forcast_start)
    {
        this.forcast_start = forcast_start;
    }

    public String getForcast_end()
    {
        return forcast_end;
    }

    @XmlElement(name = "fcst_time_to" )
    public void setForcast_end(String forcast_end)
    {
        this.forcast_end = forcast_end;
    }

    public int getWindDirection()
    {
        return windDirection;
    }

    @XmlElement(name = "wind_dir_degreees" )
    public void setWindDirection(int windDirection)
    {
        this.windDirection = windDirection;
    }

    public int getWindSpeed()
    {
        return windSpeed;
    }

    @XmlElement(name = "wind_speed_kt" )
    public void setWindSpeed(int windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public int getWindGust()
    {
        return windGust;
    }

    @XmlElement(name = "wind_gust_kt" )
    public void setWindGust(int windGust)
    {
        this.windGust = windGust;
    }

    public Double getVisibility()
    {
        return visibility;
    }

    @XmlElement(name = "visibility_statute_mi" )
    public void setVisibility(Double visibility)
    {
        this.visibility = visibility;
    }

    public List<SkyCondition> getSkyCoverage()
    {
        return skyCoverage;
    }

    @XmlElement(name = "sky_condition" )
    public void setSkyCoverage(List<SkyCondition> skyCoverage)
    {
        this.skyCoverage = skyCoverage;
    }

    public int getWindShearHeight()
    {
        return windShearHeight;
    }

    @XmlElement( name = "wind_shear_hgt_ft_agl")
    public void setWindShearHeight(int windShearHeight)
    {
        this.windShearHeight = windShearHeight;
    }

    public int getWindShearDirection()
    {
        return windShearDirection;
    }

    @XmlElement( name = "wind_shear_dir_degrees")
    public void setWindShearDirection(int windShearDirection)
    {
        this.windShearDirection = windShearDirection;
    }

    public int getGetWindShearSpeed()
    {
        return getWindShearSpeed;
    }

    @XmlElement( name = "wind_shear_speed_kt")
    public void setGetWindShearSpeed(int getWindShearSpeed)
    {
        this.getWindShearSpeed = getWindShearSpeed;
    }

    public int getForecastProbabilty()
    {
        return forecastProbabilty;
    }

    @XmlElement( name = "probability")
    public void setForecastProbabilty(int forecastProbabilty)
    {
        this.forecastProbabilty = forecastProbabilty;
    }

    public Turbulence getTurbulence()
    {
        return turbulence;
    }

    @XmlElement(name = "turbulence_condition")
    public void setTurbulence(Turbulence turbulence)
    {
        this.turbulence = turbulence;
    }

    public Icing getIcing()
    {
        return icing;
    }

    @XmlElement( name = "icing_condition")
    public void setIcing(Icing icing)
    {
        this.icing = icing;
    }
}
