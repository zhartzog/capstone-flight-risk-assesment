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
	 * Saves a professor object to the database
	 * @param toSave - The professor object to be saved
	 */
	public void save(Professor toSave) {
		try {
			initialize();
			statement.execute("INSERT INTO professorInfo (LastName, FirstName, Username, Password) "
					+ "VALUES = ('" + toSave.getLastName() + "', '" + toSave.getFirstName() + 
					"', '" + toSave.getUserName() + "', '" + toSave.getPassword() + "')");
		} catch(SQLException e) {
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
			return mapToListOfProfessors(rs);
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
			return mapToSingleProfessor(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets a professor from the database by their first and last name
	 * @param firstname - The first name of the professor
	 * @param lastname - The last name of the professor
	 * @return - a professor object populated with data from the database
	 */
	public Professor getByFirstNameLastName(String firstname, String lastname) {
		try {
			initialize();
			ResultSet rs = statement.executeQuery("SELECT * FROM professorInfo WHERE LastName = '" 
			+ lastname + "', FirstName = '" + firstname + "'");
			return mapToSingleProfessor(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets a professor from the database by their username
	 * @param username - The username of the professor
	 * @return - a professor object populated with data from the database
	 */
	public Professor getByUserName(String username) {
		try {
			initialize();
			ResultSet rs = statement.executeQuery("SELECT * FROM professorInfo WHERE Username = '" +
			username + "'");
			return mapToSingleProfessor(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Updates a row in the database by ID
	 * @param id - The id of the row to update
	 * @param toSet - The updated professor object to be inserted
	 */
	public void updateById(int id, Professor toSet) {
		try {
			initialize();
			statement.execute("UPDATE professorInfo SET ID = '" + toSet.getId() + "', LastName = '" 
					+ toSet.getLastName() + "', FirstName = '" + toSet.getFirstName() + "', Username = '"
					+ toSet.getUserName() + "', Password = '" + toSet.getPassword() + "' WHERE ID = " + id);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a row in the database by username
	 * @param username - The username corresponding to the row to update
	 * @param toSet - The professor object to update the row with
	 */
	public void updateByUsername(String username, Professor toSet) {
		try {
			initialize();
			statement.execute("UPDATE professorInfo SET ID = '" + toSet.getId() + "', LastName = '" 
					+ toSet.getLastName() + "', FirstName = '" + toSet.getFirstName() + "', Username = '"
					+ toSet.getUserName() + "', Password = '" + toSet.getPassword() + "' WHERE Username = '" 
					+ username + "'");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a row in the database by firstname and lastname
	 * @param firstname - The first name to update by
	 * @param lastname - The last name to update by
	 * @param toSet - The professor object to update the row with
	 */
	public void updateByFirstnameLastname(String firstname, String lastname, Professor toSet) {
		try {
			initialize();
			statement.execute("UPDATE professorInfo SET ID = '" + toSet.getId() + "', LastName = '" 
					+ toSet.getLastName() + "', FirstName = '" + toSet.getFirstName() + "', Username = '"
					+ toSet.getUserName() + "', Password = '" + toSet.getPassword() + "' WHERE LastName = '" 
					+ lastname + "' AND FirstName = '" + firstname + "'");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a database row by ID
	 * @param id - the id of the row to be deleted
	 */
	public void deleteById(int id) {
		try {
			initialize();
			statement.execute("DELETE FROM professorInfo WHERE ID = " + id);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a row by the username
	 * @param username - The username corresponding to the row to be deleted
	 */
	public void deleteByUsername(String username) {
		try {
			initialize();
			statement.execute("DELETE FROM professorInfo WHERE Username = '" + username + "'");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a database row by the first and last name
	 * @param firstname - The first name of the corresponding database row to be deleted
	 * @param lastname - The last name of the corresponding database row to be deleted
	 */
	public void deleteByFirstnameLastname(String firstname, String lastname) {
		try {
			initialize();
			statement.execute("DELETE FROM professorInfo WHERE FirstName = '" + firstname 
					+ "' AND LastName = '" + lastname + "'");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the database connection
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
	 * Given a result set, maps the corresponding row to a professor object
	 * @param rs - The result set to be mapped
	 * @return - The java Professor object mapped with database values
	 */
	private Professor mapToSingleProfessor(ResultSet rs) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Maps a result set to a list of professors
	 * @param rs - The result set to be mapped
	 * @return - a list of professor from the result set from the query this method was called after
	 */
	private ArrayList<Professor> mapToListOfProfessors(ResultSet rs) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
