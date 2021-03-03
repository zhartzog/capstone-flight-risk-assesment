package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.database.ProfessorRepository;
import edu.unomaha.flightriskassessment.models.Professor;
import edu.unomaha.flightriskassessment.services.MetarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController
{
    private static final Logger logger = LogManager.getLogger(MainController.class);
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping(path="/addNewProfessor")
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

    @GetMapping(path="/allProfessors")
    public @ResponseBody Iterable<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @GetMapping(path="/test")
    public @ResponseBody String test()
    {
        logger.info("Beginning Test request function...");
        MetarService ms = new MetarService();
        ms.getMetarData("KCBF");
        return "TEST";
    }

}
