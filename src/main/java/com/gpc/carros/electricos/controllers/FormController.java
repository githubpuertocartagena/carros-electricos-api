package com.gpc.carros.electricos.controllers;

import com.gpc.carros.electricos.model.Form;
import com.gpc.carros.electricos.model.request.FormRequest;
import com.gpc.carros.electricos.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class FormController {
    @Autowired
    private FormService formService;

    @PostMapping("form")
    public void saveForm(@RequestBody final FormRequest form) {
        formService.createForm(form);
    }

    @GetMapping("forms")
    public List<Form> getForms() {
        return formService.getForms();
    }


}
