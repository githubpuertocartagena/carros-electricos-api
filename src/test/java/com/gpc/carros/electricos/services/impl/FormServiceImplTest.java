package com.gpc.carros.electricos.services.impl;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.Form;
import com.gpc.carros.electricos.model.request.FormRequest;
import com.gpc.carros.electricos.repositories.CarRepository;
import com.gpc.carros.electricos.repositories.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FormServiceImplTest {

    @Mock
    private FormRepository formRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private FormServiceImpl formService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createForm(){
        final String carCode = "CAR CODE TEST";
        final FormRequest formRequest = new FormRequest();
        formRequest.setCarCode(carCode);

        when(carRepository.findByCode(carCode))
                .thenReturn(Optional.of(new Car()));

        formService.createForm(formRequest);

        verify(carRepository).findByCode(carCode);
        verify(formRepository).save(any());
    }

    @Test
    void createForm_Exception(){
        final String carCode = "CAR CODE TEST";
        final FormRequest formRequest = new FormRequest();
        formRequest.setCarCode(carCode);

        assertThrows(RuntimeException.class, () -> formService.createForm(formRequest) );
    }

    @Test
    void findForms(){
        final List<Form> response = new ArrayList();
        response.add(new Form());

        when(formRepository.findAll()).thenReturn(response);

        final List<Form> result = formService.getForms();

        assertEquals(response, result);
    }

}