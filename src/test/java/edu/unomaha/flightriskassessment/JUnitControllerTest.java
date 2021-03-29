package edu.unomaha.flightriskassessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.unomaha.flightriskassessment.controller.AwcController;
import edu.unomaha.flightriskassessment.models.awc.Metar;

public class JUnitControllerTest {
	@Test
	public void testAwcController() {
		AwcController awcController = new AwcController();
		Metar result = awcController.getMetarData("KOMA");
		assertEquals("KOMA", result.getAirportID());
		assertNotNull(result.getTime());
		assertNotNull(result.getTemperature());
		assertNotNull(result.getDewPoint());
		assertNotNull(result.getWindDirection());
		assertNotNull(result.getWindSpeed());
		assertNotNull(result.getWindGust());
		assertNotNull(result.getFlightCategory());
	}
}
