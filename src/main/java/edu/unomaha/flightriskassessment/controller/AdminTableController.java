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
	@Autowired
	AdminTableService adminThresholds_Service;
	
	
	@RequestMapping(value = "/adminThresholds/save")
	private int saveThreshold(@RequestBody AdminTable adminThresholds) {
	    adminThresholds_Service.save(adminThresholds);
	    return adminThresholds.getAdminThresholdId();
	}

	@RequestMapping(value = "/adminThresholds/getAll")
	@ResponseBody
	private ArrayList<AdminTable> getAllThresholds() { 
		return adminThresholds_Service.getAllThresholds(); 
	}
	
	@RequestMapping(value = "/adminThresholds/getById")
	@ResponseBody
	private AdminTable getAdminThresholds(@RequestParam int adminThresholdId) {
		return adminThresholds_Service.getThresholdsById(adminThresholdId);
	}
	
	@RequestMapping(value = "/adminThresholds/getByGroupNameCategory")
	@ResponseBody
	private AdminTable getAdminThresholds(@RequestParam String group, @RequestParam String category, @RequestParam String name) {
		return adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	}
	
	@RequestMapping(value = "/adminThresholds/updateById")
	private AdminTable update(@RequestBody AdminTable adminThresholds, @RequestParam int id) {
	    adminThresholds_Service.updateById(adminThresholds, id);
	    return adminThresholds;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByIdLow")
	private AdminTable updateLow(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setLow(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByIdMed")
	private AdminTable updateMed(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setMed(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByIdHigh")
	private AdminTable updateHigh(@RequestParam String val, @RequestParam int id) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdsById(id);
		toUpdate.setHigh(val);
	    adminThresholds_Service.updateById(toUpdate, id);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategory")
	private AdminTable updateGNC(@RequestBody AdminTable toUpdate, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
	    adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategoryLow")
	private AdminTable updateGNCLow(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
	    AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setLow(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategoryMed")
	private AdminTable updateGNCMed(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setMed(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/updateByGroupNameCategoryHigh")
	private AdminTable updateGNCHigh(@RequestParam String val, @RequestParam String group, @RequestParam String name, @RequestParam String category) {
		AdminTable toUpdate = adminThresholds_Service.getThresholdByGroupNameCategory(group, name, category);
	    toUpdate.setHigh(val);
		adminThresholds_Service.updateByGroupNameCategory(toUpdate, group, name, category);
	    return toUpdate;
	}
	
	@RequestMapping(value = "/adminThresholds/deleteById")
	private void deleteThreshold(@RequestParam int id) {
	    adminThresholds_Service.deleteById(id);
	}
}