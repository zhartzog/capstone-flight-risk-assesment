package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.database.ProfessorRepository;
import edu.unomaha.flightriskassessment.models.Metar;
import edu.unomaha.flightriskassessment.models.Professor;
import edu.unomaha.flightriskassessment.models.Taf;
import edu.unomaha.flightriskassessment.services.AWCServices;
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

    @Autowired
    private AWCServices awcServices = new AWCServices();

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

    @GetMapping(path="/getMetarData")
    public @ResponseBody Metar getMetarData(String airportID)
    {
        logger.info("Beginning Test request function...");
        Metar metar = awcServices.getMetarData(airportID);
        return metar;
    }

    @GetMapping(path="/getTaf")
    public @ResponseBody
    Taf getTafData(@RequestParam int radius, @RequestParam String latLong)
    {
        logger.info("Beginning getTafData...");
         Taf taf = awcServices.getTafData(radius, latLong);
         return taf;

    }

}
