
package edu.unomaha.flightriskassessment.controller;

import edu.unomaha.flightriskassessment.models.Form.*;
import edu.unomaha.flightriskassessment.services.FormServices;
import edu.unomaha.flightriskassessment.services.IFRRiskService;
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

    @Autowired
    IFRRiskService ifrRiskService;

    @PostMapping( value="/basicFormInfo", produces = "application/json")
    public @ResponseBody
    AdditionalQuestions basicFormInput(@RequestBody BasicFormInput input)
    {
        return formServices.getDynamicQuestions(input);
    }

    @PostMapping( value = "/VFRRiskCalculation", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    RiskResponse VFRRiskCalculation(@RequestBody VFRRiskModel vfrRiskModel)
    {
        System.out.println("Beginning VFRRiskCalculation...");
        return vfrRiskService.VFRRiskCalc(vfrRiskModel);
    }

    @PostMapping( value = "/IFRRiskCalculation", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    RiskResponse IFRRiskCalculation(@RequestBody IFRRiskModel ifrRiskModel)
    {
        return ifrRiskService.IFRRiskCalc(ifrRiskModel);
    }
}
