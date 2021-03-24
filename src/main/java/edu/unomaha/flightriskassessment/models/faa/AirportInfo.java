package edu.unomaha.flightriskassessment.models.faa;

import java.util.ArrayList;
import java.util.List;

public class AirportInfo
{
    private String ID;

    private String latitude;

    private String longitude;

    private String global_id;

    private long elevation;

    private boolean hasIAPs;

    private List<Runway> runways;

    public AirportInfo(){}


    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    /*Returns the three letter identifier for the airport. If the ID has a leading 'K', remove it.*/
    public String getIdent()
    {
        if(this.ID.length() > 0)
        {
            return this.ID.substring(1);
        }
        return "";
    }

    public String getLatitudeDMS()
    {
        return latitude;
    }

    public double getLatitudeDD()
    {
        return convertDMStoDD(this.latitude);
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitudeDMS()
    {
        return longitude;
    }

    public double getLongitudeDD()
    {
        return convertDMStoDD(this.longitude);
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatLongAsString()
    {
        double lat = getLatitudeDD();
        double longitude = getLongitudeDD();

        return (lat + "," + longitude);
    }

    public String getGlobal_id()
    {
        return global_id;
    }

    public void setGlobal_id(String global_id)
    {
        this.global_id = global_id;
    }

    public long getElevation()
    {
        return elevation;
    }

    public void setElevation(long elevation)
    {
        this.elevation = elevation;
    }

    public boolean isHasIAPs()
    {
        return hasIAPs;
    }

    public void setHasIAPs(boolean hasIAPs)
    {
        this.hasIAPs = hasIAPs;
    }

    public List<Runway> getRunways()
    {
        return runways;
    }

    public void setRunways(List<Runway> runways)
    {
        this.runways = runways;
    }

    public void addRunway(Runway runway)
    {
        if(this.runways == null)
            this.runways = new ArrayList<>();
        this.runways.add(runway);
    }

    //Converts coordinates from Degrees Minutes Seconds to decimal
    private double convertDMStoDD( String input)
    {
        String[] temp = input.split("-");
        double degree = Double.parseDouble(temp[0]);
        double minute = Double.parseDouble(temp[1]);
        //Seconds ends with a N/S or E/W, need to remove it to do conversion
        double second = Double.parseDouble( temp[2].substring(0, temp.length-2));

        return degree + (minute/60) + (second/3600);
    }

}
