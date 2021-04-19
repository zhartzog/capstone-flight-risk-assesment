package edu.unomaha.flightriskassessment.controller;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.flightriskassessment.models.AdminTable;
import edu.unomaha.flightriskassessment.services.AdminTableService;  
/**
 * This class is the CRUD controller that provides mappings to our database
 *
 */
@RestController
public class AdminTableController
{
	@Autowired
	AdminTableService adminThresholds_Service;
	
	
	@PostMapping("/adminThresholds/save")
	private int saveThreshold(@RequestBody AdminTable adminThresholds) throws SQLException {
	    adminThresholds_Service.save(adminThresholds);
	    return adminThresholds.getAdminThresholdId();
	}

	@GetMapping("/adminThresholds/getAll")
	private ArrayList<AdminTable> getAllThresholds() { 
		return adminThresholds_Service.getAllThresholds(); 
	}
	
	@GetMapping("/adminThresholds/getById/{adminThresholdId}")
	private AdminTable getAdminThresholds(@PathVariable("adminThresholdId") int adminThresholdId) {
		return adminThresholds_Service.getThresholdsById(adminThresholdId);
	}
	
	@PutMapping("/adminThresholds/updateById/{adminThresholdId}")
	private AdminTable update(@RequestBody AdminTable adminThresholds, @PathVariable int id) {
	    adminThresholds_Service.updateById(adminThresholds, id);
	    return adminThresholds;
	}
	
	@DeleteMapping("/adminThresholds/deleteById/{adminThresholdId}")
	private void deleteThreshold(@PathVariable("adminThresoldId") int adminThresholdId) {
	    adminThresholds_Service.deleteById(adminThresholdId);
	}
}