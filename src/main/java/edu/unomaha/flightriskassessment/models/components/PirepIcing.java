package edu.unomaha.flightriskassessment.models.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "icing_condition")
public class PirepIcing
{
    //Type of icing. Allowed values are RIME|CLEAR|MIXED
    private String type;
    //Icing intensity. Allowed values are NEG|NEGclr|TRC|TRC-LGT|LGT|LGT-MOD|MOD|MOD-SEV|HVY|SEV
    private String intensity;
    //Lowest altitude the icing is reported. Measured in feet MSL.
    private int base;
    //highest altitude the icing is reported. Measured in feet MSL.
    private int top;

    public String getType()
    {
        return type;
    }

    @XmlAttribute( name = "icing_type" )
    public void setType(String type)
    {
        this.type = type;
    }

    public String getIntensity()
    {
        return intensity;
    }

    @XmlAttribute( name = "icing_intensity" )
    public void setIntensity(String intensity)
    {
        this.intensity = intensity;
    }

    public int getBase()
    {
        return base;
    }

    @XmlAttribute( name = "icing_base_ft_msl" )
    public void setBase(int base)
    {
        this.base = base;
    }

    public int getTop()
    {
        return top;
    }

    @XmlAttribute( name = "icing_top_ft_msl" )
    public void setTop(int top)
    {
        this.top = top;
    }
}
