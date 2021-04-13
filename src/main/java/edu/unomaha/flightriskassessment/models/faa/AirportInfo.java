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

    public AirportInfo(String ID)
    {
        this.ID = ID;
    }


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
            if(this.ID.charAt(0) == 'K')
                return this.ID.substring(1);
            else
                return this.ID;
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

        return (longitude + "," + lat);
    }

    //Converts coordinates from Degrees Minutes Seconds to decimal
    private double convertDMStoDD( String input)
    {
        String[] temp = input.split("-");
        int sign = 1;
        double degree = Double.parseDouble(temp[0]);
        double minute = Double.parseDouble(temp[1]);
        //Seconds ends with a N/S or E/W, need to remove it to do conversion
        double second = Double.parseDouble( temp[2].substring(0, temp.length-2));
        char direction = temp[2].charAt(temp[2].length()-1);
        if( direction == 'S' || direction == 'W' )
            sign = -1;

        return sign*(degree + (minute/60) + (second/3600));
    }

    /*
    * This method return the a rectangle of latitude/longitude such that the airport's latlong is in the center.
    */
    public int[] getMinMaxLatLong()
    {
        int[] minMax = new int[4];
        minMax[0] = (int) (Math.floor(getLatitudeDD())); //Min latitude
        minMax[1] = (int) (Math.ceil(getLatitudeDD())); //max latitude
        minMax[2] = (int) (Math.floor(getLongitudeDD())); //Min longitude
        minMax[3] = (int) (Math.ceil(getLongitudeDD())); //max longitude

        System.out.printf("minMax{%d,%d,%d,%d}\n",minMax[0],minMax[1],minMax[2],minMax[3]);
        return minMax;
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

}
