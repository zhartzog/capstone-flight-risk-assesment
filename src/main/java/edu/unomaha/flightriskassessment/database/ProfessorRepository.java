package edu.unomaha.flightriskassessment.database;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.flightriskassessment.models.Professor;

// This is a spring class that provides basic CRUD operations for professor objects in the database

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {}
