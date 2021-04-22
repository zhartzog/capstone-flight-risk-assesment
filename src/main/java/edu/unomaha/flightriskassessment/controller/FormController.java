
package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.models.Form.AdditionalQuestions;
import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import edu.unomaha.flightriskassessment.models.Form.RiskResponse;
import edu.unomaha.flightriskassessment.models.Form.VFRRiskModel;
import edu.unomaha.flightriskassessment.services.FormServices;
import edu.unomaha.flightriskassessment.services.VFRRiskService;
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

    @Autowired
    VFRRiskService vfrRiskService;

    @PostMapping( value="/basicFormInfo", produces = "application/json")
    public @ResponseBody
    AdditionalQuestions basicFormInput(@RequestBody BasicFormInput input)
    {
        return formServices.getDynamicQuestions(input);
    }

    @PostMapping( value = "/vfrRiskCalculation", produces = "application/json")
    public @ResponseBody
    RiskResponse VFRRiskCalculation(@RequestBody VFRRiskModel vfrRiskModel)
    {
        return vfrRiskService.VFRRiskCalc(vfrRiskModel);
    }
}
