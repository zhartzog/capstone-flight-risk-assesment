package edu.unomaha.flightriskassessment.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.database.ProfessorRepository;
import edu.unomaha.flightriskassessment.models.Professor;

@Service
public class ProfessorService {
	ProfessorRepository repo = new ProfessorRepository();
	
	/**
	 * Saves a professor object to the database
	 * @param toSave - The professor to be saved
	 */
	public void save(Professor toSave) {
		repo.save(toSave);
	}
	
	/**
	 * Gets all professors from the database
	 * @return - All professors in the database currently
	 */
	public ArrayList<Professor> getAll() {
		return repo.getAll();
	}
	
	/**
	 * Gets a professor by their ID in the database
	 * @param id - The ID of the professor to be gotten
	 * @return - A professor object mapped by the database information
	 */
	public Professor getProfessorById(int id) {
		return repo.getById(id);
	}
	
	/**
	 * Gets a professor by their first name and last name
	 * @param firstname - Firstname of the professor to be gotten
	 * @param lastname - Lastname of the professor to be gotten
	 * @return - A professor object mapped by the database info
	 */
	public Professor getProfessorByFirstNameLastName(String firstname, String lastname) {
		return repo.getByFirstNameLastName(firstname, lastname);
	}
	
	/**
	 * Gets a professor by their username
	 * @param username - The username of the professor to be gotten
	 * @return - A mapped professor object with the info from the database
	 */
	public Professor getProfessorByUsername(String username) {
		return repo.getByUserName(username);
	}
	
	/**
	 * Updates a row in the database by ID
	 * @param id - the ID of the professor row to be updated
	 * @param toSet - The updated values to be set
	 */
	public void updateById(int id, Professor toSet) {
		repo.updateById(id, toSet);
	}
	
	/**
	 * Updates a row in the database by username
	 * @param username - The username of the professor row to be updated
	 * @param toSet - The updated values to be set
	 */
	public void updateByUsername(String username, Professor toSet) {
		repo.updateByUsername(username, toSet);
	}
	
	/**
	 * Updates a row in the database by first name and last name
	 * @param firstname - The firstname of the professor in the database
	 * @param lastname - The last name of the professor in the database
	 * @param toSet - The updated values to set
	 */
	public void updateByFirstNameLastName(String firstname, String lastname, Professor toSet) {
		repo.updateByFirstnameLastname(firstname, lastname, toSet);
	}
	
	/**
	 * Deletes a row by its ID
	 * @param id - the id of the row to be deleted
	 */
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	/**
	 * Deletes a row by its username
	 * @param username - The username of the row to be deleted
	 */
	public void deleteByUsername(String username) {
		repo.deleteByUsername(username);
	}
	
	/**
	 * deletes a row by its first and last name
	 * @param firstname - The first name of the row to be deleted
	 * @param lastname - The last name of the row to be deleted
	 */
	public void deleteByFirstNameLastName(String firstname, String lastname) {
		repo.deleteByFirstnameLastname(firstname, lastname);
	}
}
