package edu.unomaha.flightriskassessment.services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unomaha.flightriskassessment.database.adminThresholdsRepository;
import edu.unomaha.flightriskassessment.models.adminThresholds; 
//defining the business logic
@Service
public class adminThresholdsService
{

	adminThresholdsRepository adminThresholds_Repository = new adminThresholdsRepository();
	//get all thresholds recorded using CrudRepository findAll()
	public ArrayList<adminThresholds> getAllThresholds() throws SQLException
	{
	    return adminThresholds_Repository.getAll();
	}
	//getting a specific record by using the method findById()
	public adminThresholds getThresholdsById(int id) throws SQLException
	{
	    return adminThresholds_Repository.getByID(id);
	
	}
	//saving a specific record by using the method save() of CrudRepository
	public void save(adminThresholds adminThresholds) throws SQLException
	{
	    adminThresholds_Repository.save(adminThresholds);
	}
	//deleting a specific record by using the method deleteById()
	public void delete(int id) throws SQLException
	{
	    adminThresholds_Repository.deleteById(id);
	}
	//updating a threshold
	public void update(adminThresholds adminThresholds, int adminThresholdsId) throws SQLException
	{
	    adminThresholds_Repository.updateById(adminThresholdsId, adminThresholds);
	}
	
	public adminThresholds getByName(String name) throws SQLException {
		return adminThresholds_Repository.getByName(name);
	}
}



