package com.gpc.carros.electricos.services;

import com.gpc.carros.electricos.model.Form;
import com.gpc.carros.electricos.model.request.FormRequest;

import java.util.List;

public interface FormService {

    void createForm(FormRequest formRequest);

    List<Form> getForms();
}
