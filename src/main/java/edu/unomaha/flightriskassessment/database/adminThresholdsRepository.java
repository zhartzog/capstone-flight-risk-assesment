package edu.unomaha.flightriskassessment.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.unomaha.flightriskassessment.models.adminThresholds;

public class adminThresholdsRepository {
	
	protected String url = "jdbc:mysql://ec2-3-139-100-26.us-east-2.compute.amazonaws.com/RiskAssessmentForm";
	protected String user = "root";
	protected String pass = "rootpass";
	protected Connection connection;
	protected Statement statement;
	
	public adminThresholdsRepository() {
		super();
	}
	
	public void initialize() throws SQLException {
		if (!connection.isValid(0)) {
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
		}
	}
	
	public ArrayList<adminThresholds> getAll() throws SQLException {
		initialize();
		ResultSet rs = statement.executeQuery("SELECT * FROM adminThresholds");
		if (!rs.next()) {
			return null;
		}
		else {
			ArrayList<adminThresholds> toRet = new ArrayList<adminThresholds>();
			do {
				adminThresholds toAdd = new adminThresholds();
				toAdd.setAdminThresholdId(rs.getInt(1));
				toAdd.setGroup(rs.getString(2));
				toAdd.setName(rs.getString(3));
				toAdd.setLow(rs.getString(4));
				toAdd.setMed(rs.getString(5));
				toAdd.setHigh(rs.getString(6));
				toAdd.setCategory(rs.getString(7));
				toRet.add(toAdd);
			}
			while (rs.next());
			return toRet;
		}
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