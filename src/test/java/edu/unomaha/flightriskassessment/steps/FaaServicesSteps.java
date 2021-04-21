package edu.unomaha.flightriskassessment.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.unomaha.flightriskassessment.models.faa.AirportInfo;
import edu.unomaha.flightriskassessment.services.FaaServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FaaServicesSteps {
	private FaaServices svc;
	private AirportInfo toCheck = null;
	
	@Given("a call is made to the FAA service to get info")
	public void a_call_is_made_to_the_faa_service_to_get_info() {
	    svc = new FaaServices();
	}

	@When("the call is made to check the airport {string}")
	public void the_call_is_made_to_check_the_airport(String string) {
	    toCheck = svc.getAirportInfo(string);
	}

	@Then("the data is validated and not null")
	public void the_data_is_validated_and_not_null() {
	    assertEquals("KOMA", toCheck.getID());
	    assertNotNull(toCheck.getElevation());
	    assertNotNull(toCheck.getGlobal_id());
	    assertNotNull(toCheck.getIdent());
	    assertNotNull(toCheck.getLatitudeDD());
	    assertNotNull(toCheck.getLatitudeDMS());
	    assertNotNull(toCheck.getLongitudeDD());
	    assertNotNull(toCheck.getLongitudeDMS());
	    assertNotNull(toCheck.getMinMaxLatLong());
	    assertNotNull(toCheck.getRunways());
	}
}
