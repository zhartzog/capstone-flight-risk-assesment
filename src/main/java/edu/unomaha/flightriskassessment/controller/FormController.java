package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormController
{
    @PostMapping( value="/basicFormInfo", consumes = "application/json", produces = "application/json")
    public @ResponseBody BasicFormInput basicFormInput(@RequestBody BasicFormInput input)
    {
        System.out.println(input.getDeparture_airport());
        return input;
    }


}
