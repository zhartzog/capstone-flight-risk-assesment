package edu.unomaha.flightriskassessment.steps;

import edu.unomaha.flightriskassessment.services.adminThresholdsService;
import io.cucumber.java.en.Given;

public class AdminTableServiceSteps {
	private adminThresholdsService svc;
	@Given("a call is made to the service class to get the admin threshold with an ID of {string}")
	private void givenACallIsMadeToGetByID(String id) {
		
	}
}
