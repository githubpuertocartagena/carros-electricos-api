package com.gpc.carros.electricos.services.impl;

import com.gpc.carros.electricos.mappers.FormMapper;
import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.Form;
import com.gpc.carros.electricos.model.request.FormRequest;
import com.gpc.carros.electricos.repositories.CarRepository;
import com.gpc.carros.electricos.repositories.FormRepository;
import java.time.LocalDateTime;

import com.gpc.carros.electricos.services.FormService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;

    private final CarRepository carRepository;

    private final FormMapper mapper = Mappers.getMapper(FormMapper.class);

    public FormServiceImpl(final FormRepository formRepository, final CarRepository carRepository) {
        this.formRepository = formRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void createForm(final FormRequest formRequest) {
        log.info("Formulario + " + formRequest);
        final Car car = carRepository.findByCode(formRequest.getCarCode())
                .orElseThrow(() -> new RuntimeException("No exista carro"));

        final Form form = mapper.toForm(formRequest);
        form.setCar(car);
        form.setCreatedDate(LocalDateTime.now());
        formRepository.save(form);
    }

    @Override
    public List<Form> getForms() {
        return formRepository.findAll();
    }

}
