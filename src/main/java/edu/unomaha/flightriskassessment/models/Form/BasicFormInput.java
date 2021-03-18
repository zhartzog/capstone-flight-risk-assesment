package edu.unomaha.flightriskassessment.models.Form;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicFormInput
{

    @JsonProperty("departureTime")
    private String departure_date_time;

    @JsonProperty("departureAirport")
    private String departure_airport;

    @JsonProperty("studentName")
    private String students_name;

    @JsonProperty("studentLevel")
    private String level;

    @JsonProperty("isDualFlight")
    private boolean isDualFlight;

    @JsonProperty("prevFlights")
    private int previous_flight;

    @JsonProperty("flightDuty")
    private String flight_duty_begin;

    @JsonProperty("categoryOfFlight")
    private String flight_category;

    @JsonProperty("typeOfFlight")
    private String flight_type;

    @JsonProperty("xcDestination")
    private String xc_destination;

    public String getDeparture_date_time()
    {
        return departure_date_time;
    }

    public void setDeparture_date_time(String departure_date_time)
    {
        this.departure_date_time = departure_date_time;
    }

    public String getDeparture_airport()
    {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport)
    {
        this.departure_airport = departure_airport;
    }

    public String getStudents_name()
    {
        return students_name;
    }

    public void setStudents_name(String students_name)
    {
        this.students_name = students_name;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public boolean isDualFlight()
    {
        return isDualFlight;
    }

    public void setDualFlight(boolean dualFlight)
    {
        isDualFlight = dualFlight;
    }

    public int getPrevious_flight()
    {
        return previous_flight;
    }

    public void setPrevious_flight(int previous_flight)
    {
        this.previous_flight = previous_flight;
    }

    public String getFlight_duty_begin()
    {
        return flight_duty_begin;
    }

    public void setFlight_duty_begin(String flight_duty_begin)
    {
        this.flight_duty_begin = flight_duty_begin;
    }

    public String getFlight_category()
    {
        return flight_category;
    }

    public void setFlight_category(String flight_category)
    {
        this.flight_category = flight_category;
    }

    public String getFlight_type()
    {
        return flight_type;
    }

    public void setFlight_type(String flight_type)
    {
        this.flight_type = flight_type;
    }

    public String getXc_destination()
    {
        return xc_destination;
    }

    public void setXc_destination(String xc_destination)
    {
        this.xc_destination = xc_destination;
    }
}
