package edu.unomaha.flightriskassessment.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    //autowire the adminThresholdsService class
@Autowired
AdminTableService adminThresholds_Service;

    //creating a mapping that retrieves all thresholds from the table
@GetMapping("/adminThresholds")
private ArrayList<AdminTable> getAllThresholds() throws SQLException
{
    return adminThresholds_Service.getAllThresholds();
}
    //create a get mapping that retrieves a threshold associated with a given id
@GetMapping("/adminThresholds/{adminThresholdId}")
private AdminTable getAdminThresholds(@PathVariable("adminThresholdId") int adminThresholdId) throws SQLException
{
    return adminThresholds_Service.getThresholdsById(adminThresholdId);
}
    //create a delete mapping that deletes by ID.
@DeleteMapping("/adminThresholds/{adminThresholdId}")
private void deleteThreshold(@PathVariable("adminThresoldId") int adminThresholdId) throws SQLException
{
    adminThresholds_Service.deleteById(adminThresholdId);
}
    //create a post mapping that post the admin threshold into the database
@PostMapping("/adminThresholds")
private int saveThreshold(@RequestBody AdminTable adminThresholds) throws SQLException
{
    adminThresholds_Service.save(adminThresholds);
    return adminThresholds.getAdminThresholdId();
}
    //create a put mapping for udpating the threshold
@PutMapping("/adminThresholds")
private AdminTable update(@RequestBody AdminTable adminThresholds, @RequestBody int id) throws SQLException
{
    adminThresholds_Service.updateById(adminThresholds, id);
    return adminThresholds;

}
}
