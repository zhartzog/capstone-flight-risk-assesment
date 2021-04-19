package edu.unomaha.flightriskassessment.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;

import edu.unomaha.flightriskassessment.models.AdminTable;
import edu.unomaha.flightriskassessment.services.AdminTableService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminTableServiceSteps {
	
	private AdminTableService svc;
	private ArrayList<AdminTable> list;
	private AdminTable toCheckByID;
	private AdminTable toCheckByName;
	private AdminTable localCheck;
	String validate;
	
	@Given("a call is made to the service class to get all thresholds")
	public void a_call_is_made_to_the_service_class_to_get_all_thresholds() {
		svc = new AdminTableService();
	}

	@When("the request to get all thresholds is made")
	public void the_request_is_made_to_get_all() {
		list = svc.getAllThresholds();
	}

	@Then("all of the admin thresholds are returned")
	public void all_of_the_admin_thresholds_are_returned() {
		assertEquals(63, list.size());
	}
	
	@Then("the first element is correct")
	public void the_first_element_is_correct() {
		// First element
				AdminTable toCheck = list.get(0);
				assertEquals(2, toCheck.getAdminThresholdId());
				assertEquals("ifr", toCheck.getGroup());
				assertEquals("Ceiling (Day)", toCheck.getName());
				assertEquals("1000", toCheck.getLow());
				assertEquals("800", toCheck.getMed());
				assertEquals("600", toCheck.getHigh());
				assertEquals("departure", toCheck.getCategory());
				assertEquals(">", toCheck.getRanges());
	}
	
	@Then("the middle element is correct")
	public void the_middle_element_is_correct() {
		// Element in the middle
				AdminTable toCheck = list.get(28);
				assertEquals(30, toCheck.getAdminThresholdId());
				assertEquals("vfr", toCheck.getGroup());
				assertEquals("Ceiling (Night Dual)", toCheck.getName());
				assertEquals("4000", toCheck.getLow());
				assertEquals("3500", toCheck.getMed());
				assertEquals("3000", toCheck.getHigh());
				assertEquals("localPattern", toCheck.getCategory());
				assertEquals(">", toCheck.getRanges());
	}
	
	@Then("the last element is correct")
	public void the_last_element_is_correct() {
		// Last element
				AdminTable toCheck = list.get(58);
				assertEquals(60, toCheck.getAdminThresholdId());
				assertEquals("vfr", toCheck.getGroup());
				assertEquals("Last Dual Landing (Comm)", toCheck.getName());
				assertEquals("14", toCheck.getLow());
				assertEquals("28", toCheck.getMed());
				assertEquals("45", toCheck.getHigh());
				assertEquals("soloFactors", toCheck.getCategory());
				assertEquals("<", toCheck.getRanges());
	}
	
	@Given("a call is made to the service class to get a threshold by id")
	public void a_call_is_made_to_the_service_class_to_get_a_threshold_by_the_id() {
	    svc = new AdminTableService();
	}

	@When("the request is made to get a threshold by its ID")
	public void the_request_is_made_to_get_a_threshold_by_its_id() {
		toCheckByID = svc.getThresholdsById(7);
	}

	@Then("The threshold returned is the correct one")
	public void the_threshold_returned_is_the_correct_one() {
	    assertEquals(7, toCheckByID.getAdminThresholdId());
	    assertEquals("ifr", toCheckByID.getGroup());
	    assertEquals("Gust Increment", toCheckByID.getName());
	    assertEquals("5", toCheckByID.getLow());
	    assertEquals("8", toCheckByID.getMed());
	    assertEquals("10", toCheckByID.getHigh());
	    assertEquals("departure", toCheckByID.getCategory());
	    assertEquals("<", toCheckByID.getRanges());
	}
	
	@Given("a call is made to the service class to create a threshold")
	public void a_call_is_made_to_the_service_class_to_create_a_threshold() {
	    svc = new AdminTableService();
	}

	@When("the request is made to create a threshold")
	public void the_request_is_made_to_create_a_threshold() {
		AdminTable toSave = new AdminTable();
		toSave.setCategory("testDeparture");
		toSave.setGroup("testIfr");
		toSave.setHigh("test123");
		toSave.setMed("test213");
		toSave.setLow("test321");
		toSave.setName("testThreshold");
		toSave.setRanges("<");
		svc.save(toSave);
	}

	@Then("the threshold is created, validated, updated, and deleted")
	public void the_threshold_is_created_validated_and_deleted() {
		
		toCheckByName = svc.getThresholdByGroupNameCategory("testIfr", "testThreshold", "testDeparture");
		assertEquals("testDeparture", toCheckByName.getCategory());
		assertEquals("testIfr", toCheckByName.getGroup());
		assertEquals("test123", toCheckByName.getHigh());
		assertEquals("test213", toCheckByName.getMed());
		assertEquals("test321", toCheckByName.getLow());
		assertEquals("testThreshold", toCheckByName.getName());
		assertEquals("<", toCheckByName.getRanges());
		AdminTable toUpdate = new AdminTable();
		toUpdate.setCategory("testUpdatedDeparture");
		toUpdate.setGroup("testUpdatedIfr");
		toUpdate.setName("testUpdatedThreshold");
		toUpdate.setLow("987");
		toUpdate.setMed("654");
		toUpdate.setHigh("789");
		toUpdate.setRanges(">");
		svc.updateById(toUpdate, toCheckByName.getAdminThresholdId());
		localCheck = svc.getThresholdByGroupNameCategory("testUpdatedIfr", "testUpdatedThreshold", "testUpdatedDeparture");
		assertEquals("testUpdatedThreshold", localCheck.getName());
		assertEquals("testUpdatedIfr", localCheck.getGroup());
		assertEquals("testUpdatedDeparture", localCheck.getCategory());
		assertEquals("987", localCheck.getLow());
		assertEquals("654", localCheck.getMed());
		assertEquals("789", localCheck.getHigh());
		assertEquals(">", localCheck.getRanges());
		svc.deleteById(localCheck.getAdminThresholdId());
		assertNull(svc.getThresholdsById(localCheck.getAdminThresholdId()));
	}
}
