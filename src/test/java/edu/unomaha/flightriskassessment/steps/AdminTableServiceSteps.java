package edu.unomaha.flightriskassessment.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import edu.unomaha.flightriskassessment.models.adminThresholds;
import edu.unomaha.flightriskassessment.services.adminThresholdsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminTableServiceSteps {
	
	private adminThresholdsService svc;
	private ArrayList<adminThresholds> list;
	private adminThresholds toCheckByID;
	private adminThresholds toCheckByName;
	String validate;
	
	@Given("a call is made to the service class to get all thresholds")
	public void a_call_is_made_to_the_service_class_to_get_all_thresholds() {
		svc = new adminThresholdsService();
	}

	@When("the request to get all thresholds is made")
	public void the_request_is_made_to_get_all() {
		try {
			list = svc.getAllThresholds();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("all of the admin thresholds are returned")
	public void all_of_the_admin_thresholds_are_returned() {
		assertEquals(59, list.size());
	}
	
	@Then("the first element is correct")
	public void the_first_element_is_correct() {
		// First element
				adminThresholds toCheck = list.get(0);
				assertEquals(2, toCheck.getAdminThresholdId());
				assertEquals("ifr", toCheck.getGroup());
				assertEquals("ceilingDay", toCheck.getName());
				assertEquals("1000", toCheck.getLow());
				assertEquals("800", toCheck.getMed());
				assertEquals("600", toCheck.getHigh());
				assertEquals("departure", toCheck.getCategory());
	}
	
	@Then("the middle element is correct")
	public void the_middle_element_is_correct() {
		// Element in the middle
				adminThresholds toCheck = list.get(28);
				assertEquals(30, toCheck.getAdminThresholdId());
				assertEquals("vfr", toCheck.getGroup());
				assertEquals("ceilingNight", toCheck.getName());
				assertEquals("4000", toCheck.getLow());
				assertEquals("3500", toCheck.getMed());
				assertEquals("3000", toCheck.getHigh());
				assertEquals("localPattern", toCheck.getCategory());
	}
	
	@Then("the last element is correct")
	public void the_last_element_is_correct() {
		// Last element
				adminThresholds toCheck = list.get(58);
				assertEquals(60, toCheck.getAdminThresholdId());
				assertEquals("vfr", toCheck.getGroup());
				assertEquals("lastLandingComm", toCheck.getName());
				assertEquals("14", toCheck.getLow());
				assertEquals("28", toCheck.getMed());
				assertEquals("45", toCheck.getHigh());
				assertEquals("soloFactors", toCheck.getCategory());
	}
	
	@Given("a call is made to the service class to get a threshold by id")
	public void a_call_is_made_to_the_service_class_to_get_a_threshold_by_the_id() {
	    svc = new adminThresholdsService();
	}

	@When("the request is made to get a threshold by its ID")
	public void the_request_is_made_to_get_a_threshold_by_its_id() {
	    try {
			toCheckByID = svc.getThresholdsById(7);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("The threshold returned is the correct one")
	public void the_threshold_returned_is_the_correct_one() {
	    assertEquals(7, toCheckByID.getAdminThresholdId());
	    assertEquals("ifr", toCheckByID.getGroup());
	    assertEquals("gustIncr", toCheckByID.getName());
	    assertEquals("5", toCheckByID.getLow());
	    assertEquals("8", toCheckByID.getMed());
	    assertEquals("10", toCheckByID.getHigh());
	    assertEquals("departure", toCheckByID.getCategory());
	}
	
	@Given("a call is made to the service class to create a threshold")
	public void a_call_is_made_to_the_service_class_to_create_a_threshold() {
	    svc = new adminThresholdsService();
	}

	@When("the request is made to create a threshold")
	public void the_request_is_made_to_create_a_threshold() {
		adminThresholds toSave = new adminThresholds();
		toSave.setCategory("testDeparture");
		toSave.setGroup("testIfr");
		toSave.setHigh("test123");
		toSave.setMed("test213");
		toSave.setLow("test321");
		toSave.setName("testThreshold");
	    try {
			svc.save(toSave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Then("the threshold is created, validated, updated, and deleted")
	public void the_threshold_is_created_validated_and_deleted() {
		
		try {
			toCheckByName = svc.getByName("testThreshold");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("testDeparture", toCheckByName.getCategory());
		assertEquals("testIfr", toCheckByName.getGroup());
		assertEquals("test123", toCheckByName.getHigh());
		assertEquals("test213", toCheckByName.getMed());
		assertEquals("test321", toCheckByName.getLow());
		assertEquals("testThreshold", toCheckByName.getName());
		toCheckByName.setName("testUpdated");
		try {
			svc.update(toCheckByName, toCheckByName.getAdminThresholdId());
			adminThresholds localCheck = svc.getByName("testUpdated");
			assertEquals("testUpdated", localCheck.getName());
			svc.delete(localCheck.getAdminThresholdId());
			assertNull(svc.getThresholdsById(toCheckByName.getAdminThresholdId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
