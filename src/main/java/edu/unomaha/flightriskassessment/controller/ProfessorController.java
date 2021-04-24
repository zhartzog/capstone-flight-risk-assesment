package edu.unomaha.flightriskassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.flightriskassessment.models.Professor;
import edu.unomaha.flightriskassessment.services.ProfessorService;

@RestController
public class ProfessorController {
	@Autowired
	ProfessorService svc;
	
	@RequestMapping(value = "/professors/save")
	private void saveThreshold(@RequestBody Professor professor) {
	    svc.save(professor);
	}
	
	@RequestMapping(value = "/professors/getByUsername")
	@ResponseBody
	private Professor getByUsername(@RequestParam String username) {
		return svc.getProfessorByUsername(username);
	}
	
	
}
