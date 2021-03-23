package edu.unomaha.flightriskassessment.services;

import edu.unomaha.flightriskassessment.models.Form.BasicFormInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormServices
{
    @Autowired
    private AWCServices awcServices;

    public String getDynamicQuestion(BasicFormInput input)
    {
        return "Not done";
    }
}
