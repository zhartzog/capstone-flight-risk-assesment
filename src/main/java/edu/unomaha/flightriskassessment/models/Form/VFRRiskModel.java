package edu.unomaha.flightriskassessment.models.Form;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VFRRiskModel
{
    @JsonProperty("isLocal")
    private boolean isLocal;

    @JsonProperty("isDual")
    private boolean isDual;

    @JsonProperty("departure_vis")
    private int departure_vis;

    @JsonProperty("departure_ceilings")
    private int departure_ceilings;

    @JsonProperty("departure_winds")
    private int departure_winds;

    @JsonProperty("departure_gusts")
    private int departure_gusts;

    @JsonProperty("departure_crosswind")
    private int departure_crosswind;

    @JsonProperty("enroute_ceilings")
    private int enroute_ceilings;

    @JsonProperty("enroute_vis")
    private int enroute_vis;

    @JsonProperty("vfr_checkpoints")
    private int vfrCheckpoints;

    @JsonProperty("time_enroute")
    private int timeEnroute;

    @JsonProperty("fuel_at_alternate")
    private int fuelAtAlternate;

    @JsonProperty("destination_vis")
    private int destination_vis;

    @JsonProperty("destination_ceilings")
    private int destination_ceilings;

    @JsonProperty("destination_winds")
    private int destination_winds;

    @JsonProperty("destination_gusts")
    private int destination_gusts;

    @JsonProperty("departure_crosswind")
    private int destination_crosswind;

    @JsonProperty("alternate_vis")
    private int alternate_vis;

    @JsonProperty("alternate_ceilings")
    private int alternate_ceilings;

    @JsonProperty("alternate_winds")
    private int alternate_winds;

    @JsonProperty("alternate_gusts")
    private int alternate_gusts;

    @JsonProperty("alternate_crosswind")
    private int alternate_crosswind;

    @JsonProperty("time_of_day")
    private String time_of_day;

    @JsonProperty("flight_duty_period")
    private int flight_duty_period;

    @JsonProperty("previous_flights")
    private int previous_flights;

    @JsonProperty("type_of_flight")
    private String type_of_flight;

    @JsonProperty("temperature")
    private int temperature;

    public boolean isLocal()
    {
        return isLocal;
    }

    public boolean isDual()
    {
        return isDual;
    }

    public boolean isNight()
    {
        return time_of_day.equals("night");
    }

    public int getDeparture_vis()
    {
        return departure_vis;
    }

    public int getDeparture_ceilings()
    {
        return departure_ceilings;
    }

    public int getDeparture_winds()
    {
        return departure_winds;
    }

    public int getDeparture_gusts()
    {
        return departure_gusts;
    }

    public int getDeparture_crosswind()
    {
        return departure_crosswind;
    }

    public int getEnroute_ceilings()
    {
        return enroute_ceilings;
    }

    public int getEnroute_vis()
    {
        return enroute_vis;
    }

    public int getVfrCheckpoints()
    {
        return vfrCheckpoints;
    }

    public int getTimeEnroute()
    {
        return timeEnroute;
    }

    public int getFuelAtAlternate()
    {
        return fuelAtAlternate;
    }

    public int getDestination_vis()
    {
        return destination_vis;
    }

    public int getDestination_ceilings()
    {
        return destination_ceilings;
    }

    public int getDestination_winds()
    {
        return destination_winds;
    }

    public int getDestination_gusts()
    {
        return destination_gusts;
    }

    public int getDestination_crosswind()
    {
        return destination_crosswind;
    }

    public int getAlternate_vis()
    {
        return alternate_vis;
    }

    public int getAlternate_ceilings()
    {
        return alternate_ceilings;
    }

    public int getAlternate_winds()
    {
        return alternate_winds;
    }

    public int getAlternate_gusts()
    {
        return alternate_gusts;
    }

    public int getAlternate_crosswind()
    {
        return alternate_crosswind;
    }

    public String getTime_of_day()
    {
        return time_of_day;
    }

    public int getFlight_duty_period()
    {
        return flight_duty_period;
    }

    public int getPrevious_flights()
    {
        return previous_flights;
    }

    public String getType_of_flight()
    {
        return type_of_flight;
    }

    public int getTemperature()
    {
        return temperature;
    }
}

