package edu.unomaha.flightriskassessment.database;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.flightriskassessment.models.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {}
