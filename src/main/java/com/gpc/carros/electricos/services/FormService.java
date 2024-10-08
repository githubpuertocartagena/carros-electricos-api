package com.gpc.carros.electricos.services;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.Form;
import com.gpc.carros.electricos.model.request.FormRequest;
import com.gpc.carros.electricos.repositories.CarRepository;
import com.gpc.carros.electricos.repositories.FormRepository;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FormService {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private CarRepository carRepository;

    public void createForm(FormRequest formRequest) {
        log.info("Formulario + " + formRequest);
        Car car = carRepository.findByCode(formRequest.getCarCode())
                .orElseThrow(() -> new RuntimeException("No exista carro"));
       

        Form form = new Form();
        form.setCar(car);
        form.setUsername(formRequest.getUsername());
        form.setTipo(formRequest.getTipo());
        form.setCinturones(formRequest.getCinturones());
        form.setLuces(formRequest.getLuces());
        form.setBanderin(formRequest.getBanderin());
        form.setLimpiabrisas(formRequest.getLimpiabrisas());
        form.setLlantas(formRequest.getLlantas());
        form.setBocinas(formRequest.getBocinas());
        form.setRetrovisores(formRequest.getRetrovisores());
        form.setAlarmaDeReversa(formRequest.getAlarmaDeReversa());
        form.setExtintor(formRequest.getExtintor());
        form.setConos(formRequest.getConos());
        form.setBateria(formRequest.getBateria());
        form.setCreatedDate(LocalDateTime.now());
        form.setObservaciones(formRequest.getObservaciones());
        formRepository.save(form);
    }

    public List<Form> getForms() {
        return formRepository.findAll();
    }

}
