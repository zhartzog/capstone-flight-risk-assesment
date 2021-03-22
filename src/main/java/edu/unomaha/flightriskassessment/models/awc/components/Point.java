package edu.unomaha.flightriskassessment.models.awc.components;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "point" )
public class Point
{
    private double longitude;

    private double latitude;

    public double getLongitude()
    {
        return longitude;
    }

    @XmlElement(name = "longitude")
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    @XmlElement( name = "latitude")
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}
