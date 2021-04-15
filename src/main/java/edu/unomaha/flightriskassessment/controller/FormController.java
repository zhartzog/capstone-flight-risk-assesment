
package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.models.Form.AdditionalQuestions;
import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import edu.unomaha.flightriskassessment.services.FormServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormController
{
    @Autowired
    FormServices formServices;

    @PostMapping( value="/basicFormInfo", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    AdditionalQuestions basicFormInput(@RequestBody BasicFormInput input)
    {
        return formServices.getDynamicQuestions(input);
    }
}
