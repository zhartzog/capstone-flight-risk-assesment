package edu.unomaha.flightriskassessment.services;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unomaha.flightriskassessment.database.adminThresholdsRepository;
import edu.unomaha.flightriskassessment.models.adminThresholds; 
//defining the business logic
@Service
public class adminThresholdsService
{

adminThresholdsRepository adminThresholds_Repository;
//get all thresholds recorded using CrudRepository findAll()
public ResultSet getAllThresholds() throws SQLException
{
	    return adminThresholds_Repository.getAll();
	}
	//getting a specific record by using the method findById()
	public adminThresholds getThresholdsById(int id) throws SQLException
	{
	    adminThresholds toReturn = new adminThresholds();
	    ResultSet rs = adminThresholds_Repository.getByID(id);
	    toReturn.setAdminThresholdId(id);
	    toReturn.setGroup(rs.getString("groupType"));
	    toReturn.setHigh(rs.getString("high"));
	    toReturn.setMed(rs.getString("med"));
	    toReturn.setLow(rs.getString("low"));
	    toReturn.setName(rs.getString("name"));
	    toReturn.setCategory(rs.getString("category"));
	    return toReturn;
	
	}
	//saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(adminThresholds adminThresholds) throws SQLException
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
}



