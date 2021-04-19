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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/adminThresholds/getById")
	private AdminTable getAdminThresholds(@RequestParam int adminThresholdId) {
		return adminThresholds_Service.getThresholdsById(adminThresholdId);
	}
	
	@GetMapping("/adminThresholds/getByGroupNameCategory")
	private AdminTable getAdminThresholds(@RequestParam String group, @RequestParam String category, @RequestParam String name) {
		return adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	}
	
	@PutMapping("/adminThresholds/updateById")
	private AdminTable update(@RequestBody AdminTable adminThresholds, @RequestParam int id) {
	    adminThresholds_Service.updateById(adminThresholds, id);
	    return adminThresholds;
	}
	
	@PutMapping("/adminThresholds/updateByIdLow")
	private AdminTable updateLow(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setLow(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	@PutMapping("/adminThresholds/updateByIdMed")
	private AdminTable updateMed(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setMed(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	@PutMapping("/adminThresholds/updateByIdHigh")
	private AdminTable updateHigh(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setHigh(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	@PutMapping("/adminThresholds/updateByGroupNameCategory")
	private AdminTable updateGNC(@RequestBody AdminTable toUpdate, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
	    adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@PutMapping("/adminThresholds/updateByGroupNameCategoryLow")
	private AdminTable updateGNCLow(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
	    AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setLow(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@PutMapping("/adminThresholds/updateByGroupNameCategoryMed")
	private AdminTable updateGNCMed(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setMed(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@PutMapping("/adminThresholds/updateByGroupNameCategoryHigh")
	private AdminTable updateGNCHigh(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setHigh(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@DeleteMapping("/adminThresholds/deleteById")
	private void deleteThreshold(@RequestParam int id) {
	    adminThresholds_Service.deleteById(id);
	}
}