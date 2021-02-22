package com.capstone.flightRiskAssessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capstone.flightRiskAssessment.database.Professor;
import com.capstone.flightRiskAssessment.database.ProfessorRepository;

@Controller
@RequestMapping(path="/")
public class MainController {
	@Autowired
	private ProfessorRepository professorRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String firstName, @RequestParam String lastName, 
			@RequestParam String userName, @RequestParam String password) { 
		Professor professor = new Professor();
		professor.setFirstName(firstName);
		professor.setLastName(lastName);
		professor.setUserName(userName);
		professor.setPassword(password);
		professorRepository.save(professor);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Professor> getAllProfessors() {
		return professorRepository.findAll();
	}
}
