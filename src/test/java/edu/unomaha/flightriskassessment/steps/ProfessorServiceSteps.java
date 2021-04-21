package edu.unomaha.flightriskassessment.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import edu.unomaha.flightriskassessment.models.Professor;
import edu.unomaha.flightriskassessment.services.ProfessorService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProfessorServiceSteps {
	private ProfessorService svc;
	private ArrayList<Professor> list;
	private Professor toVerify = null;
	private Professor toCheck = null;
	
	@Given("a call is made to the Professor Service to get all professors")
	public void a_call_is_made_to_the_professor_service_to_get_all_professors() {
		svc = new ProfessorService();
	}

	@When("the call to get all professors is made")
	public void the_call_to_get_all_professors_is_made() {
	    list = svc.getAll();
	}

	@Then("all of the professors are returned")
	public void all_of_the_professors_are_returned() {
		assertEquals("test", list.get(0).getFirstName());
		assertEquals("test@test.com", list.get(0).getUserName());
		assertEquals("test3", list.get(2).getFirstName());
		assertEquals("test3@test3.com", list.get(2).getUserName());
	}
	
	@Given("a call is made to the Professor Service to get a professor by their first and last name")
	public void a_call_is_made_to_the_professor_service_to_get_a_professor_by_their_first_and_last_name() {
	    svc = new ProfessorService();
	}

	@When("the call to get the specific profess is made for the names {string} and {string}")
	public void the_call_to_get_the_specific_profess_is_made_for_the_names_and(String string, String string2) {
	    toVerify = svc.getProfessorByFirstNameLastName(string, string2);
	}

	@Then("the specific professor is returned")
	public void the_specific_professor_is_returned() {
	    assertEquals("uniqueFirstName", toVerify.getFirstName());
	    assertEquals("uniqueLastName", toVerify.getLastName());
	    assertEquals("uniquePassword", toVerify.getPassword());
	    assertEquals("uniqueUsername", toVerify.getUserName());
	    assertEquals(4, toVerify.getId());
	}
	
	@Given("a call is made to the Professor Service to create a professor")
	public void a_call_is_made_to_the_professor_service_to_create_a_professor() {
		svc = new ProfessorService();
	}

	@When("the call is made to create a profesor")
	public void the_call_is_made_to_create_a_profesor() {
	    Professor toCreate = new Professor();
	    toCreate.setFirstName("createTest");
	    toCreate.setLastName("testCreate");
	    toCreate.setPassword("createTestPass");
	    toCreate.setUserName("testCreation");
	    svc.save(toCreate);
	}

	@Then("the data is validated")
	public void the_data_is_validated() {
	    Professor toCheck = svc.getProfessorByFirstNameLastName("createTest", "testCreate");
	    assertEquals("createTest", toCheck.getFirstName());
	    assertEquals("testCreate", toCheck.getLastName());
	    assertEquals("createTestPass", toCheck.getPassword());
	    assertEquals("testCreation", toCheck.getUserName());
	    this.toCheck = toCheck;
	}

	@Then("the data is updated and the updated is validated")
	public void the_data_is_updated_and_the_updated_is_validated() {
	    Professor toSet = new Professor();
	    toSet.setFirstName("updateTest");
	    toSet.setLastName("testUpdate");
	    toSet.setPassword("updateTestPass");
	    toSet.setUserName("testUpdating");
	    svc.updateByFirstNameLastName("createTest", "testCreate", toSet);
	    Professor toValidate = svc.getProfessorByFirstNameLastName("updateTest", "testUpdate");
	    assertNotNull(toValidate);
	    assertEquals("updateTest", toValidate.getFirstName());
	    assertEquals("testUpdate", toValidate.getLastName());
	    assertEquals("updateTestPass", toValidate.getPassword());
	    assertEquals("testUpdating", toValidate.getUserName());
	}

	@Then("the data is deleted and validated to be deleted")
	public void the_data_is_deleted_and_validated_to_be_deleted() {
	    svc.deleteByFirstNameLastName("updateTest", "testUpdate");
	    assertNull(svc.getProfessorByFirstNameLastName("updateTest", "testUpdate"));
	}
}
