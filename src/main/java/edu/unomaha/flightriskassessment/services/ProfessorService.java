package edu.unomaha.flightriskassessment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.database.ProfessorRepository;
import edu.unomaha.flightriskassessment.models.Professor;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;
	
	public ProfessorService() {}
	
	public List<Professor> findAll() {
		var it = professorRepository.findAll();
		var professors = new ArrayList<Professor>();
		it.forEach(e -> professors.add(e));
		return professors;
	}
}
