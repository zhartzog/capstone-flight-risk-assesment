package edu.unomaha.flightriskassessment.models.awc.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "icing_condition")
public class Icing
{
    private String intensity;

    private int minAltitude;

    private int maxAltitude;

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

    @XmlAttribute(name = "turbulence_min_alt_ft_agl")
    public void setMinAltitude(int minAltitude)
    {
        this.minAltitude = minAltitude;
    }

    public int getMaxAltitude()
    {
        return maxAltitude;
    }

    @XmlAttribute(name = "turbulence_max_alt_ft_agl")
    public void setMaxAltitude(int maxAltitude)
    {
        this.maxAltitude = maxAltitude;
    }
}
