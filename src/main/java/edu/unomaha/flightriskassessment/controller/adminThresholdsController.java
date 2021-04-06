package edu.unomaha.flightriskassessment.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.flightriskassessment.models.adminThresholds;
import edu.unomaha.flightriskassessment.services.adminThresholdsService;  
/**
 * This class is the CRUD controller that provides mappings to our database
 *
 */
@RestController
public class adminThresholdsController
{
	    //autowire the adminThresholdsService class
	@Autowired
	adminThresholdsService adminThresholds_Service;
	
	/**
	 * Mapping to retrieve all thresholds in the table
	 * @return a list of all thresholds in the database
	 */
	@GetMapping("/adminThresholds")
	private List<adminThresholds> getAllThresholds()
	{
	    return adminThresholds_Service.getAllThresholds();
	}
	
	/**
	 * Mapping to retrieve a threshold by ID
	 * @param adminThresholdId - the ID to retrieve by
	 * @return The threshold for the given ID
	 */
	@GetMapping("/adminThresholds/{adminThresholdId}")
	private adminThresholds getAdminThresholds(@PathVariable("adminThresholdId") int adminThresholdId)
	{
	    return adminThresholds_Service.getThresholdsById(adminThresholdId);
	}
	
	/**
	 * Deletes a threshold from the database by its ID
	 * @param adminThresholdId - the ID for the threshold to be deleted
	 */
	@DeleteMapping("/adminThresholds/{adminThresholdId}")
	private void deleteThreshold(@PathVariable("adminThresoldId") int adminThresholdId)
	{
	    adminThresholds_Service.delete(adminThresholdId);
	}
	
	/**
	 * Mapping to save a threshold to the database
	 * @param adminThresholds - The threshold to be saved
	 * @return - The ID of the new threshold in the database
	 */
	@PostMapping("/adminThresholds")
	private int saveThreshold(@RequestBody adminThresholds adminThresholds)
	{
	    adminThresholds_Service.saveOrUpdate(adminThresholds);
	    return adminThresholds.getAdminThresholdId();
	}
	/**
	 * Mapping to update a threshold
	 * @param adminThresholds - The updated threshold to be saved
	 * @return
	 */
	@PutMapping("/adminThresholds")
	private adminThresholds update(@RequestBody adminThresholds adminThresholds)
	{
	    adminThresholds_Service.saveOrUpdate(adminThresholds);
	    return adminThresholds;
	
	}
}
