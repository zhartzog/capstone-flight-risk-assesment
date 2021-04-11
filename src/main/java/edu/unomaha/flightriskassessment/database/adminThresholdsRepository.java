package edu.unomaha.flightriskassessment.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.unomaha.flightriskassessment.models.adminThresholds;

public class adminThresholdsRepository extends Repository {
	
	public adminThresholdsRepository() {
		super();
	}
	
	public void initialize() throws SQLException {
		if (!connection.isValid(0)) {
			connection = DriverManager.getConnection(url, user, pass);
			Statement statement = connection.createStatement();
		}
	}
	
	public ResultSet getAll() throws SQLException {
		initialize();
		return statement.executeQuery("SELECT * FROM adminThresholds");
	}
	
	/**
	 * Returns a result set of the adminThreshold related to the ID passed
	 * @param id - the ID of the adminThreshold to get
	 * @return - the adminThreshold whose ID was passed
	 * @throws SQLException
	 */
	public ResultSet getByID(int id) throws SQLException {
		initialize();
		return statement.executeQuery("SELECT * FROM adminThresholds WHERE adminThresholdId = '" + id + "'");
	}
	
	/**
	 * Saves a new threshold to the table
	 * @param toSave - The threshold to save
	 * @throws SQLException 
	 */
	public void save(adminThresholds toSave) throws SQLException {
		initialize();
		statement.execute("INSERT INTO adminThresholds (groupType, name, low, med, high, category) VALUES ('"
				+ toSave.getGroup() + "' '" + toSave.getName() + "' '" + toSave.getLow() + "' '" + toSave.getMed() + "' '"
				+toSave.getHigh() + "' '" + toSave.getCategory() + "'");
	}
	
	/**
	 * Deletes a threshold by ID value
	 * @param id - the admin threshold ID for the threshold to be deleted
	 * @throws SQLException
	 */
	public void deleteById(int id) throws SQLException {
		initialize();
		statement.execute("DELETE FROM adminThresholds WHERE adminThresholdId = '" + id + "'");
	}
	
	/**
	 * Updates a threshold by ID
	 * @param id - the ID of the threshold to update
	 * @param toSet - the updated values of the threshold to be set
	 * @throws SQLException
	 */
	public void updateById(int id, adminThresholds toSet) throws SQLException {
		initialize();
		statement.execute("UPDATE adminThresholds SET groupType = '" + toSet.getGroup() + "', name = '" + toSet.getName() 
		+ "', low = '" + toSet.getLow() + "', med = '" + toSet.getMed() + "', high = '" + toSet.getHigh() + "', category = '"
		+ toSet.getCategory() + "' WHERE adminThresholdId = " + id);
	}
	
	public void updateByGroupNameCategory(String group, String name, String category, adminThresholds toSet) {
		
	}
}