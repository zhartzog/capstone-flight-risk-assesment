package edu.unomaha.flightriskassessment.models.faa;

public class Runway
{
    private String designator;

    private int length;

    private int width;

    public Runway(String designator, int length, int width)
    {
       this.designator = designator;
        this.length = length;
        this.width = width;
    }

    public String getDesignator()
    {
        return designator;
    }

    public void setDesignator(String designator)
    {
        this.designator = designator;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }
}