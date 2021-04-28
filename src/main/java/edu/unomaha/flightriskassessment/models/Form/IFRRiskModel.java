package edu.unomaha.flightriskassessment.models.Form;
import com.fasterxml.jackson.annotation.JsonProperty;


public class IFRRiskModel
{
    @JsonProperty("is_local")
    private boolean isLocal;

    @JsonProperty("departure_vis")
    private int departure_vis;

    @JsonProperty("departure_ceilings")
    private int departure_ceilings;

    @JsonProperty("departure_iap")
    private String departure_iap;

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

    @JsonProperty("time_enroute")
    private int timeEnroute;

    @JsonProperty("thunderstorm_risk")
    private int thunderstorm_risk;

    @JsonProperty("fuel_at_alternate")
    private int fuelAtAlternate;

    @JsonProperty("destination_vis")
    private int destination_vis;

    @JsonProperty("destination_ceilings")
    private int destination_ceilings;

    @JsonProperty("destination_iap")
    private String destination_iap;

    @JsonProperty("destination_winds")
    private int destination_winds;

    @JsonProperty("destination_gusts")
    private int destination_gusts;

    @JsonProperty("destination_crosswind")
    private int destination_crosswind;

    @JsonProperty("alternate_vis")
    private int alternate_vis;

    @JsonProperty("alternate_ceilings")
    private int alternate_ceilings;

    @JsonProperty("alternate_iap")
    private String alternate_iap;

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

    public String getDeparture_iap()
    {
        return departure_iap;
    }

    public int getThunderstorm_risk()
    {
        return thunderstorm_risk;
    }

    public String getDestination_iap()
    {
        return destination_iap;
    }

    public String getAlternate_iap()
    {
        return alternate_iap;
    }

    public void setLocal(boolean local)
    {
        isLocal = local;
    }

    public void setDeparture_vis(int departure_vis)
    {
        this.departure_vis = departure_vis;
    }

    public void setDeparture_ceilings(int departure_ceilings)
    {
        this.departure_ceilings = departure_ceilings;
    }

    public void setDeparture_iap(String departure_iap)
    {
        this.departure_iap = departure_iap;
    }

    public void setDeparture_winds(int departure_winds)
    {
        this.departure_winds = departure_winds;
    }

    public void setDeparture_gusts(int departure_gusts)
    {
        this.departure_gusts = departure_gusts;
    }

    public void setDeparture_crosswind(int departure_crosswind)
    {
        this.departure_crosswind = departure_crosswind;
    }

    public void setEnroute_ceilings(int enroute_ceilings)
    {
        this.enroute_ceilings = enroute_ceilings;
    }

    public void setEnroute_vis(int enroute_vis)
    {
        this.enroute_vis = enroute_vis;
    }

    public void setTimeEnroute(int timeEnroute)
    {
        this.timeEnroute = timeEnroute;
    }

    public void setThunderstorm_risk(int thunderstorm_risk)
    {
        this.thunderstorm_risk = thunderstorm_risk;
    }

    public void setFuelAtAlternate(int fuelAtAlternate)
    {
        this.fuelAtAlternate = fuelAtAlternate;
    }

    public void setDestination_vis(int destination_vis)
    {
        this.destination_vis = destination_vis;
    }

    public void setDestination_ceilings(int destination_ceilings)
    {
        this.destination_ceilings = destination_ceilings;
    }

    public void setDestination_iap(String destination_iap)
    {
        this.destination_iap = destination_iap;
    }

    public void setDestination_winds(int destination_winds)
    {
        this.destination_winds = destination_winds;
    }

    public void setDestination_gusts(int destination_gusts)
    {
        this.destination_gusts = destination_gusts;
    }

    public void setDestination_crosswind(int destination_crosswind)
    {
        this.destination_crosswind = destination_crosswind;
    }

    public void setAlternate_vis(int alternate_vis)
    {
        this.alternate_vis = alternate_vis;
    }

    public void setAlternate_ceilings(int alternate_ceilings)
    {
        this.alternate_ceilings = alternate_ceilings;
    }

    public void setAlternate_iap(String alternate_iap)
    {
        this.alternate_iap = alternate_iap;
    }

    public void setAlternate_winds(int alternate_winds)
    {
        this.alternate_winds = alternate_winds;
    }

    public void setAlternate_gusts(int alternate_gusts)
    {
        this.alternate_gusts = alternate_gusts;
    }

    public void setAlternate_crosswind(int alternate_crosswind)
    {
        this.alternate_crosswind = alternate_crosswind;
    }

    public void setTime_of_day(String time_of_day)
    {
        this.time_of_day = time_of_day;
    }

    public void setFlight_duty_period(int flight_duty_period)
    {
        this.flight_duty_period = flight_duty_period;
    }

    public void setPrevious_flights(int previous_flights)
    {
        this.previous_flights = previous_flights;
    }

    public void setType_of_flight(String type_of_flight)
    {
        this.type_of_flight = type_of_flight;
    }

    public void setTemperature(int temperature)
    {
        this.temperature = temperature;
    }
}


