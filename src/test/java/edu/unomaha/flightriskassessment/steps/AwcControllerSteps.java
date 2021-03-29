package edu.unomaha.flightriskassessment.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.unomaha.flightriskassessment.controller.AwcController;
import edu.unomaha.flightriskassessment.models.awc.Metar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AwcControllerSteps {
	private String airportID;
	private AwcController awcController = new AwcController();
	private Metar result;
	
	@Given ("a request is made to fetch metar data for {string}")
	public void givenARequestIsMade(String airportID) {
		this.airportID = airportID;
	}
	@When ("that request is made")
	public void whenARequestIsMade() {
		result = awcController.getMetarData(airportID);
	}
	@Then ("the METAR object is populated")
	public void thenMetarIsPopulated() {
		assertEquals(airportID, result.getAirportID());
		assertNotNull(result.getTime());
		assertNotNull(result.getTemperature());
		assertNotNull(result.getDewPoint());
		assertNotNull(result.getWindDirection());
		assertNotNull(result.getWindSpeed());
		assertNotNull(result.getWindGust());
		assertNotNull(result.getFlightCategory());
	}
}
