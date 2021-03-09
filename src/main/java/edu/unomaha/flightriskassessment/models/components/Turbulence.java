package edu.unomaha.flightriskassessment.models.components;

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

    public void setIntensity(String intensity)
    {
        this.intensity = intensity;
    }

    public int getMinAltitude()
    {
        return minAltitude;
    }

    public void setMinAltitude(int minAltitude)
    {
        this.minAltitude = minAltitude;
    }

    public int getMaxAltitude()
    {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude)
    {
        this.maxAltitude = maxAltitude;
    }
}
