package edu.unomaha.flightriskassessment.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sky_condition")
public class CloudCoveragePair
{
    private String coverage;
    private int base;

    public CloudCoveragePair(){}

    public CloudCoveragePair(String coverage, int base)
    {
        this.coverage = coverage;
        this.base = base;
    }

    public String getCoverage()
    {
        return coverage;
    }

    @XmlElement(name = "sky_cover")
    public void setCoverage(String coverage)
    {
        this.coverage = coverage;
    }

    public int getBase()
    {
        return base;
    }

    @XmlElement(name = "cloud_base_ft_agl")
    public void setBase(int base)
    {
        this.base = base;
    }
}
