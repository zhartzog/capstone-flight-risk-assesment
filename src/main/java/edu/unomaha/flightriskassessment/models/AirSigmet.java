package edu.unomaha.flightriskassessment.models;

import edu.unomaha.flightriskassessment.models.components.Altitude;
import edu.unomaha.flightriskassessment.models.components.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement( name = "AIRSIGMET")
public class AirSigmet
{
    private static final Logger logger = LogManager.getLogger(AirSigmet.class);

    private String rawText;

    //Start time from when the Airmet/Sigmet is valid from. ISO8601 format
    private String timeStart;
    //End time from when the Airmet/Sigmet is valid from. ISO8601 format
    private String timeEnd;
    //Altitudes that the forecast is valid for. Measured in feet MSL.
    private Altitude altitude;
    //The magnetic heading the forecasted condition is moving. Generally only applicable to thunderstorms.
    private int movementHeading;
    //The speed in knots the forecasted condition is moving. Generally only applicable to thunderstorms.
    private int movementSpeed;
    //Type of forecast. 'OUTLOOK', 'AIRMET', or 'SIGMET'
    private String      type;
    //List of coordinates that define the forecasted region.
    private List<Point> area;

    public String getRawText()
    {
        return rawText;
    }

    @XmlElement( name = "raw_text")
    public void setRawText(String rawText)
    {
        this.rawText = rawText;
    }

    public String getTimeStart()
    {
        return timeStart;
    }

    @XmlElement(name = "valid_time_from")
    public void setTimeStart(String timeStart)
    {
        this.timeStart = timeStart;
    }

    public String getTimeEnd()
    {
        return timeEnd;
    }

    @XmlElement( name = "valid_time_to" )
    public void setTimeEnd(String timeEnd)
    {
        this.timeEnd = timeEnd;
    }

    public Altitude getAltitude()
    {
        return altitude;
    }

    @XmlElement( name = "altitude" )
    public void setAltitude(Altitude altitude)
    {
        this.altitude = altitude;
    }

    public int getMovementHeading()
    {
        return movementHeading;
    }

    @XmlElement( name = "movement_dir_degrees" )
    public void setMovementHeading(int movementHeading)
    {
        this.movementHeading = movementHeading;
    }

    public int getMovementSpeed()
    {
        return movementSpeed;
    }

    @XmlElement( name = "movement_speed_kt" )
    public void setMovementSpeed(int movementSpeed)
    {
        this.movementSpeed = movementSpeed;
    }

    public String getType()
    {
        return type;
    }

    @XmlElement( name = "airsigmet_type" )
    public void setType(String type)
    {
        this.type = type;
    }

    public List<Point> getArea()
    {
        return area;
    }

    @XmlElementWrapper( name = "area")
    @XmlElement( name = "point" )
    public void setArea(List<Point> area)
    {
        this.area = area;
    }

    public boolean contains(double latitude, double longitude)
    {
        Coordinate[] points = generateCoordinates();
        GeometryFactory gf = new GeometryFactory();
        LinearRing ring = gf.createLinearRing(points);
        Polygon polygon = gf.createPolygon();

        Coordinate tempCoordinate = new Coordinate(latitude, longitude);
        org.locationtech.jts.geom.Point pt = gf.createPoint(tempCoordinate);

        return polygon.contains(pt);

    }

    private Coordinate[] generateCoordinates()
    {
        ArrayList<Coordinate> points = new ArrayList<Coordinate>();
        for ( Point i: this.area)
        {
            points.add( new Coordinate(i.getLongitude(), i.getLatitude()));
        }
        return points.toArray(new Coordinate[0]);
    }
}
