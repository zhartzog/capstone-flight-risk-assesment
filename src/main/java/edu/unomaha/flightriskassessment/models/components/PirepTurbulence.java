package edu.unomaha.flightriskassessment.models.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "turbulence_condition")
public class PirepTurbulence
{
    //Type of turbulence. Allowed values are CAT|CHOP|LLWS|MWAVE
    private String type;
    //Intensity of turbulence. Allowed values are NEG|SMTH-LGT|LGT|LGT-MOD|MOD|MOD-SEV|SEV|SEV-EXTM|EXTM
    private String intensity;
    //Lowest altitude the turbulence is reported. Measured in feet MSL.
    private int base;
    // Highest altitude the turbulence is reported. Measured in feet MSL>
    private int top;
    // Frequency of the turbulence. Allowed values are ISOL|OCNL|CONT
    private String frequency;

    public String getType()
    {
        return type;
    }

    @XmlAttribute( name = "turbulence_type")
    public void setType(String type)
    {
        this.type = type;
    }

    public String getIntensity()
    {
        return intensity;
    }

    @XmlAttribute( name = "turbulence_intensity")
    public void setIntensity(String intensity)
    {
        this.intensity = intensity;
    }

    public int getBase()
    {
        return base;
    }

    @XmlAttribute( name = "turbulence_base_ft_msl")
    public void setBase(int base)
    {
        this.base = base;
    }

    public int getTop()
    {
        return top;
    }

    @XmlAttribute( name = "turbulence_top_ft_msl")
    public void setTop(int top)
    {
        this.top = top;
    }

    public String getFrequency()
    {
        return frequency;
    }

    @XmlAttribute( name = "turbulence_freq")
    public void setFrequency(String frequency)
    {
        this.frequency = frequency;
    }
}
