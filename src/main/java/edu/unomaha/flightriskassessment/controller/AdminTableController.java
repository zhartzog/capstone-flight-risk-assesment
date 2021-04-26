package edu.unomaha.flightriskassessment.controller;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	/**
	 * The service class for the admin table
	 */
	@Autowired
	AdminTableService adminThresholds_Service;
	
	/**
	 * Request mapping to save an object to the database
	 * @param adminThresholds - The threshold to save
	 * @return - the ID of the new threshold
	 */
	@RequestMapping(value = "/adminThresholds/save")
	private void saveThreshold(@RequestBody AdminTable adminThresholds) {
	    adminThresholds_Service.save(adminThresholds);
	}

	/**
	 * Request mapping to get all thresholds
	 * @return - all thresholds in the database currently
	 */
	@RequestMapping(value = "/adminThresholds/getAll")
	@ResponseBody
	private ArrayList<AdminTable> getAllThresholds() { 
		return adminThresholds_Service.getAllThresholds(); 
	}
	
	/**
	 * Mapping to get an admin threshold by ID
	 * @param adminThresholdId - The id of the threshold
	 * @return - The threshold object
	 */
	@RequestMapping(value = "/adminThresholds/getById")
	@ResponseBody
	private AdminTable getAdminThresholds(@RequestParam int adminThresholdId) {
		return adminThresholds_Service.getThresholdsById(adminThresholdId);
	}
	
	/**
	 * Request mapping to get a threshold by its group, category, and name
	 * @param group - Group of the threshold
	 * @param category - Category of the threshold
	 * @param name - Name of the threshold
	 * @return - The threshold to be gotten
	 */
	@RequestMapping(value = "/adminThresholds/getByGroupNameCategory")
	@ResponseBody
	private AdminTable getAdminThresholds(@RequestParam String group, @RequestParam String category, @RequestParam String name) {
		return adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	}
	
	/**
	 * Updates a threshold in the database
	 * @param adminThresholds - The updated values of the threshold
	 * @param id - The id of the threshold to be updated
	 * @return - The updated object
	 */
	@RequestMapping(value = "/adminThresholds/updateById")
	private AdminTable update(@RequestBody AdminTable adminThresholds, @RequestParam int id) {
	    adminThresholds_Service.updateById(adminThresholds, id);
	    return adminThresholds;
	}
	
	/**
	 * Mapping to update only the low value of a threshold by ID
	 * @param val - The value to be set
	 * @param id - The ID of the threshold being updated
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByIdLow")
	private AdminTable updateLow(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setLow(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	/**
	 * Mapping to update only the med value of a threshold by ID
	 * @param val - The value to be set
	 * @param id - The ID of the threshold being updated
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByIdMed")
	private AdminTable updateMed(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setMed(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	/**
	 * Mapping to update only the high value of a threshold by ID
	 * @param val - The value to be set
	 * @param id - The ID of the threshold being updated
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByIdHigh")
	private AdminTable updateHigh(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setHigh(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	/**
	 * Mapping to update by group name and category
	 * @param toUpdate - The updated threshold values to be put into the database
	 * @param group
	 * @param name
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategory")
	private AdminTable updateGNC(@RequestBody AdminTable toUpdate, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
	    adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	/**
	 * Mapping to update only the low threshold value of an object by group, name, and category
	 * @param val - The new value of the threshold
	 * @param group
	 * @param name
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategoryLow")
	private AdminTable updateGNCLow(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
	    AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setLow(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	/**
	 * Mapping to update only the med threshold value of an object by group, name, and category
	 * @param val - The new value of the threshold
	 * @param group
	 * @param name
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategoryMed")
	private AdminTable updateGNCMed(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setMed(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	/**
	 * Mapping to update only the high threshold value of an object by group, name, and category
	 * @param val - The new value of the threshold
	 * @param group
	 * @param name
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategoryHigh")
	private AdminTable updateGNCHigh(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setHigh(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	/**
	 * Deletes a threshold by its ID
	 * @param id
	 */
	@RequestMapping(value = "/adminThresholds/deleteById")
	private void deleteThreshold(@RequestParam int id) {
	    adminThresholds_Service.deleteById(id);
	}
}