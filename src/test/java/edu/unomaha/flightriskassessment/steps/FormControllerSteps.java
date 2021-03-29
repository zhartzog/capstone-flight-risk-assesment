package edu.unomaha.flightriskassessment.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.unomaha.flightriskassessment.controller.FormController;
import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FormControllerSteps {
	private FormController formController;
	private BasicFormInput basicFormInput;
	
	@Given("a request is made to fetch form data")
	public void givenBFIRequestIsMade() {
		formController = new FormController();
	}
	
	@When("that BFI request is made")
	public void whenFormRequestIsMade() {
		basicFormInput = formController.basicFormInput(basicFormInput);
	}
	
	@Then("the basic form object is populated")
	public void thenBFIIsPopulated() {
		assertEquals("KOMA", basicFormInput.getDeparture_airport());
	}
}
