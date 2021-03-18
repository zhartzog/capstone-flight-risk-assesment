package edu.unomaha.flightriskassessment.models.awc.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "altitude" )
public class Altitude
{
    private int minimum;

    private int maximum;

    public int getMinimum()
    {
        return minimum;
    }

    @XmlAttribute( name = "min_ft_msl" )
    public void setMinimum(int minimum)
    {
        this.minimum = minimum;
    }

    public int getMaximum()
    {
        return maximum;
    }

    @XmlAttribute( name = "max_ft_msl" )
    public void setMaximum(int maximum)
    {
        this.maximum = maximum;
    }
}
