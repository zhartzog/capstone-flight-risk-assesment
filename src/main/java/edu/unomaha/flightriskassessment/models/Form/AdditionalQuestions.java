package edu.unomaha.flightriskassessment.models.Form;

import edu.unomaha.flightriskassessment.models.awc.AirSigmet;
import edu.unomaha.flightriskassessment.models.awc.Metar;
import edu.unomaha.flightriskassessment.models.awc.Pirep;

import java.util.List;

public class AdditionalQuestions
{
    private boolean isInstrumentCurrent;

    private String winds;
    private int headwind;
    private int crosswind;
    private int headwind_gust;
    private int crosswind_gust;
    private String primaryRunway;

    private Metar metar;

    private List<AirSigmet> airSigmetList;

    private List<Pirep> pireps;

    public void setDepartureWinds(double[] winds)
    {
        headwind = (int) Math.ceil(winds[0]);
        crosswind = (int) Math.ceil(winds[1]);
    }

    public void setDeartureWinds_gusts(double[] gusts)
    {
        headwind_gust = (int) Math.ceil(gusts[0]);
        crosswind_gust = (int) Math.ceil(gusts[1]);
    }

    public String getWinds()
    {
        return winds;
    }

    public void setWinds(String winds)
    {
        this.winds = winds;
    }

    public List<AirSigmet> getAirSigmetList()
    {
        return airSigmetList;
    }

    public void setAirSigmetList(List<AirSigmet> airSigmetList)
    {
        this.airSigmetList = airSigmetList;
    }

    public void addAirSigmet(AirSigmet airSigmet)
    {
        this.airSigmetList.add(airSigmet);
    }

    public List<Pirep> getPireps()
    {
        return pireps;
    }

    public void setPireps(List<Pirep> pireps)
    {
        this.pireps = pireps;
    }

    public void addPirep(Pirep pirep)
    {
        this.pireps.add(pirep);
    }

    public double getHeadwind()
    {
        return headwind;
    }


    public double getCrosswind()
    {
        return crosswind;
    }

    public Metar getMetar()
    {
        return metar;
    }

    public void setMetar(Metar metar)
    {
        this.metar = metar;
    }

    public double getHeadwind_gust()
    {
        return headwind_gust;
    }

    public double getCrosswind_gust()
    {
        return crosswind_gust;
    }

    public String getPrimaryRunway()
    {
        return primaryRunway;
    }

    public void setPrimaryRunway(String primaryRunway)
    {
        this.primaryRunway = primaryRunway;
    }

    @Override
    public String toString()
    {
        return "AdditionalQuestions{" +
                "isInstrumentCurrent=" + isInstrumentCurrent +
                ", headwind=" + headwind +
                ", crosswind=" + crosswind +
                ", airSigmetList=" + airSigmetList +
                ", pireps=" + pireps +
                '}';
    }
}
