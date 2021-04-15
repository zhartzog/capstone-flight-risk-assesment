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
	
	/**
	 * Constructor for the repository
	 */
	public adminThresholdsRepository() {}
	
	/**
	 * Initializes the database connection
	 * @throws SQLException
	 */
	private void initialize() {
		try {
			connection = DriverManager.getConnection(url, user, pass);
			if (connection.isValid(0)) {
				statement = connection.createStatement();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the full list of all thresholds in the database
	 * @return - The full list of all thresholds in the database
	 * @throws SQLException
	 */
	public ArrayList<adminThresholds> getAll() {
		try {
			initialize();
			ResultSet rs = statement.executeQuery("SELECT * FROM adminThresholds");
			if (!rs.next()) {
				connection.close();
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
				connection.close();
				return toRet;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns a result set of the adminThreshold related to the ID passed
	 * @param id - the ID of the adminThreshold to get
	 * @return - the adminThreshold whose ID was passed
	 * @throws SQLException
	 */
	public adminThresholds getByID(int id) throws SQLException {
		initialize();
		ResultSet rs = statement.executeQuery("SELECT * FROM adminThresholds WHERE adminThresholdId = " + Integer.toString(id));
		adminThresholds toRet = new adminThresholds();
		if (!rs.next()) {
			connection.close();
			return null;
		}
		toRet.setAdminThresholdId(id);
		toRet.setGroup(rs.getString(2));
		toRet.setName(rs.getString(3));
		toRet.setLow(rs.getString(4));
		toRet.setMed(rs.getString(5));
		toRet.setHigh(rs.getString(6));
		toRet.setCategory(rs.getString(7));
		connection.close();
		return toRet;
	}
	
	/**
	 * Saves a new threshold to the table
	 * @param toSave - The threshold to save
	 * @throws SQLException 
	 */
	public void save(adminThresholds toSave) throws SQLException {
		initialize();
		statement.execute("INSERT INTO adminThresholds (groupType, name, low, med, high, category) VALUES ('"
				+ toSave.getGroup() + "', '" + toSave.getName() + "', '" + toSave.getLow() + "', '" + toSave.getMed() + "', '"
				+toSave.getHigh() + "', '" + toSave.getCategory() + "')");
		connection.close();
	}
	
	/**
	 * Deletes a threshold by ID value
	 * @param id - the admin threshold ID for the threshold to be deleted
	 * @throws SQLException
	 */
	public void deleteById(int id) throws SQLException {
		initialize();
		statement.execute("DELETE FROM adminThresholds WHERE adminThresholdId = '" + id + "'");
		connection.close();
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
		connection.close();
	}
	
	/**
	 * Gets a threshold by its group, name, and category
	 * @param group - The group of the threshold
	 * @param name - The name of the threshold
	 * @param category - The category of the threshold
	 * @return - The specific threshold to be gotten
	 * @throws SQLException
	 */
	public adminThresholds getByGroupNameCategory(String group, String name, String category) throws SQLException {
		initialize();
		ResultSet rs = statement.executeQuery("SELECT * FROM adminThresholds WHERE groupType = '" + group + "' AND name = '" + name + "' AND "
				+ "category = '" + category + "'");
		if (!rs.next()) {
			connection.close();
			return null;
		}
		adminThresholds toRet = new adminThresholds();
		toRet.setAdminThresholdId(rs.getInt(1));
		toRet.setGroup(rs.getString(2));
		toRet.setName(rs.getString(3));
		toRet.setLow(rs.getString(4));
		toRet.setMed(rs.getString(5));
		toRet.setHigh(rs.getString(6));
		toRet.setCategory(rs.getString(7));
		return toRet;
	}
	
	/**
	 * Updates a threshold by its group, name, and category
	 * @param group - the group to search for
	 * @param name - the name to search for
	 * @param category - the category to search for
	 * @param toSet - The adminThreshold object to update the threshold with
	 * @throws SQLException
	 */
	public void setByGroupNameCategory(String group, String name, String category, adminThresholds toSet) throws SQLException {
		initialize();
		statement.execute("UPDATE adminThresholds at SET groupType = '" + toSet.getGroup() + "', name = '" + toSet.getName() +
				"', low = '" + toSet.getLow() + "', med = '" + toSet.getMed() + "', high = '" + toSet.getHigh() + "', category = '" +
				toSet.getCategory() + "' WHERE at.groupType = '" + group + "' AND at.name = '" + name + "' AND "
						+ "at.category = '" + category + "'");
	}
	
	public adminThresholds getByName(String name) throws SQLException {
		initialize();
		ResultSet rs = statement.executeQuery("SELECT * FROM adminThresholds WHERE name = '" + name + "'");
		if (!rs.next()) {
			connection.close();
			return null;
		}
		adminThresholds toRet = new adminThresholds();
		toRet.setAdminThresholdId(rs.getInt(1));
		toRet.setGroup(rs.getString(2));
		toRet.setName(rs.getString(3));
		toRet.setLow(rs.getString(4));
		toRet.setMed(rs.getString(5));
		toRet.setHigh(rs.getString(6));
		toRet.setCategory(rs.getString(7));
		return toRet;
	}
}