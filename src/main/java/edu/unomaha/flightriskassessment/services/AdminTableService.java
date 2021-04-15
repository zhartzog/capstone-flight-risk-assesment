package edu.unomaha.flightriskassessment.services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unomaha.flightriskassessment.database.AdminTableRepository;
import edu.unomaha.flightriskassessment.models.AdminTable; 
//defining the business logic
@Service
public class AdminTableService
{

	AdminTableRepository adminThresholds_Repository = new AdminTableRepository();
	
	/**
	 * In between method that calls the repository to save a threshold value
	 * @param adminThresholds - The threshold to save
	 */
	public void save(AdminTable adminThresholds)
	{
	    adminThresholds_Repository.save(adminThresholds);
	}

	/**
	 * In between method that calls the repository to get all thresholds
	 * @return - A list of all thresholds in the database
	 */
	public ArrayList<AdminTable> getAllThresholds()
	{
	    return adminThresholds_Repository.getAll();
	}

	/**
	 * In between method that calls the repository to get a threshold by ID
	 * @param id - the ID of the row to be gotten
	 * @return - the corresponding row data
	 */
	public AdminTable getThresholdsById(int id) throws SQLException
	{
	    return adminThresholds_Repository.getByID(id);
	}
	
	/**
	 * Gets a row by the group, name, and category values
	 * @param group - The group of the row
	 * @param name - The name of the row
	 * @param category - The category of the row
	 * @return - A populated threshold object
	 */
	public AdminTable getThresholdByGroupNameCategory(String group, String name, String category) {
		return adminThresholds_Repository.getByGroupNameCategory(group, name, category);
	}
	
	/**
	 * Updates a row by its unique id value
	 * @param AdminTable - The threshold to update with
	 * @param adminThresholdsId - The ID of the threshold in the database to be updated
	 */
	public void updateById(AdminTable toSet, int adminThresholdsId)
	{
	    adminThresholds_Repository.updateById(adminThresholdsId, toSet);
	}
	
	/**
	 * Updates a row by the unique grouping of the 3 string parameters
	 * @param toSet - The threshold values to use to update
	 * @param group - The group of the threshold to update
	 * @param name - The name of the threshold to update
	 * @param category - The category of the threshold to update
	 */
	public void updateByGroupNameCategory(AdminTable toSet, String group, String name, String category) {
		adminThresholds_Repository.updateByGroupNameCategory(group, name, category, toSet);
	}
	
	//deleting a specific record by using the method deleteById()
	public void deleteById(int id) throws SQLException
	{
	    adminThresholds_Repository.deleteById(id);
	}
}



