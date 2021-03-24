package edu.unomaha.flightriskassessment.models.Form;

import edu.unomaha.flightriskassessment.models.awc.AirSigmet;
import edu.unomaha.flightriskassessment.models.awc.Pirep;

import java.util.List;

public class AdditionalQuestions
{
    private boolean isInstrumentCurrent;

    private double headwind;
    private double crosswind;

    private List<AirSigmet> airSigmetList;

    private List<Pirep> pireps;

    public boolean isInstrumentCurrent()
    {
        return isInstrumentCurrent;
    }

    public void setInstrumentCurrent(boolean instrumentCurrent)
    {
        isInstrumentCurrent = instrumentCurrent;
    }

    public void setDepartureWinds(double[] winds)
    {
        headwind = winds[0];
        crosswind = winds[1];
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
