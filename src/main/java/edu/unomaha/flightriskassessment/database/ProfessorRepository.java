package edu.unomaha.flightriskassessment.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.unomaha.flightriskassessment.models.Professor;

public class professorRepository {
	protected String url = "jdbc:mysql://ec2-3-139-100-26.us-east-2.compute.amazonaws.com/RiskAssessmentForm";
	protected String user = "root";
	protected String pass = "rootpass";
	protected Connection connection;
	protected Statement statement;
	
	/**
	 * Default Constructor
	 */
	public professorRepository() {}
	
	/**
	 * Intializes the database connection
	 * @throws SQLException
	 */
	public void initialize() throws SQLException {
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
	 * Returns the full list of all professors
	 * @return - A list of all professors in the database
	 */
	public ArrayList<Professor> getAll() {
		try {
			initialize();
			ResultSet rs = statement.executeQuery("SELECT * FROM professorInfo");
			if (!rs.next()) {
				connection.close();
				return null;
			}
			else {
				ArrayList<Professor> toRet = new ArrayList<Professor>();
				do {
					Professor toAdd = new Professor();
					toAdd.setId(rs.getInt(1));
					toAdd.setLastName(rs.getString(2));
					toAdd.setFirstName(rs.getString(3));
					toAdd.setUserName(rs.getString(4));
					toAdd.setPassword(rs.getString(5));
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
	 * Gets a professor from the database by ID
	 * @param id - the ID of the professor to be gotten
	 * @return - a professor object populated with data from the database
	 */
	public Professor getById(int id) {
		try {
			initialize();
			ResultSet rs = statement.executeQuery("SELECT * FROM professorInfo WHERE ID = " + id);
			if (!rs.next()) {
				connection.close();
				return null;
			}
			else {
				Professor toRet = new Professor();
				toRet.setId(rs.getInt(1));
				toRet.setLastName(rs.getString(2));
				toRet.setFirstName(rs.getString(3));
				toRet.setUserName(rs.getString(4));
				toRet.setPassword(rs.getString(5));
				connection.close();
				return toRet;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
