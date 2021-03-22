package edu.unomaha.flightriskassessment.models.awc.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "turbulence_condition")
public class Turbulence
{
    private String intensity;

    private int minAltitude;

    private int maxAltitude;

    public Turbulence(){}

    public Turbulence(String intensity, int minAltitude, int maxAltitude)
    {
        this.intensity = intensity;
        this.minAltitude = minAltitude;
        this.maxAltitude = maxAltitude;
    }

    public String getIntensity()
    {
        return intensity;
    }

    @XmlAttribute(name = "turbulence_intensity")
    public void setIntensity(String intensity)
    {
        this.intensity = intensity;
    }

    public int getMinAltitude()
    {
        return minAltitude;
    }

    @XmlAttribute( name = "turbulence_min_alt_ft_agl")
    public void setMinAltitude(int minAltitude)
    {
        this.minAltitude = minAltitude;
    }

    public int getMaxAltitude()
    {
        return maxAltitude;
    }

    @XmlAttribute( name = "turbulence_max_alt_ft_agl")
    public void setMaxAltitude(int maxAltitude)
    {
        this.maxAltitude = maxAltitude;
    }
}
