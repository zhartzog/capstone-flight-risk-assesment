package edu.unomaha.flightriskassessment.database;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.flightriskassessment.models.FormData;

//This is a spring class that provides basic CRUD operations for form data objects in the database

public interface FormDataRepository extends CrudRepository<FormData, Integer> {}
