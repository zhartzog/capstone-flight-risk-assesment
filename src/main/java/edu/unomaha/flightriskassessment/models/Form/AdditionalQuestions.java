package edu.unomaha.flightriskassessment.models.Form;

public class AdditionalQuestions
{
    private boolean isInstrumentCurrent;

    private double headwind;
    private double crosswind;

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
}
