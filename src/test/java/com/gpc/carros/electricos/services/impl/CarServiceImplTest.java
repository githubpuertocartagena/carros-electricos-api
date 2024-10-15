package com.gpc.carros.electricos.services.impl;

import com.gpc.carros.electricos.mappers.CarMapper;
import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.Station;
import com.gpc.carros.electricos.model.dto.CarDto;
import com.gpc.carros.electricos.repositories.CarLogRepository;
import com.gpc.carros.electricos.repositories.CarRepository;
import com.gpc.carros.electricos.repositories.StationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarLogRepository carLogRepository;

    @Mock
    private StationRepository stationRepository;

    private CarMapper mapper;

    @InjectMocks
    private CarServiceImpl carService;

    private final static String CODE = "CODE_TEST";

    private final static String USER_NAME = "USERNAME_TEST";

    private final Car car = new  Car();

    @BeforeEach
    void setup(){
        car.setCode(CODE);
        mapper = Mockito.mock(CarMapper.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void solveStatus_Car_charging(){
        car.setStation(new Station());
        when(carRepository.findByCode(CODE))
                .thenReturn(Optional.of(car));

        final String result = carService.solveStatus(CODE, USER_NAME);

        assertEquals("charging", result);
    }

    @Test
    void solveStatus_Car_take(){
        final Car car = new  Car();
        car.setCode(CODE);
        when(carRepository.findByCode(CODE))
                .thenReturn(Optional.of(car));

        final String result = carService.solveStatus(CODE, USER_NAME);

        assertEquals("take", result);
    }

    @Test
    void solveStatus_Car_return(){
        car.setUsername(USER_NAME);
        when(carRepository.findByCode(CODE))
                .thenReturn(Optional.of(car));

        final String result = carService.solveStatus(CODE, USER_NAME);

        assertEquals("return", result);
    }

    @Test
    void solveStatus_Car_occupied(){
        car.setUsername("DACARDENAS");
        when(carRepository.findByCode(CODE))
                .thenReturn(Optional.of(car));

        final String result = carService.solveStatus(CODE, USER_NAME);

        assertEquals("occupied", result);
    }

    @Test
    void solveStatus_scan_car(){
        final Station station = new Station();
        station.setCode(CODE);
        when(stationRepository.findByCode(CODE))
                .thenReturn(Optional.of(station));

        final String result = carService.solveStatus(CODE, USER_NAME);

        assertEquals("scan car", result);
    }

    @Test
    void solveStatus_Exception(){
        assertThrows(RuntimeException.class, ()-> carService.solveStatus(CODE, USER_NAME));
    }

    @Test
    void scanChargeCarStatus_stopCharge(){
        car.setStation(new Station());
        when(carRepository.findByCode(CODE)).thenReturn(Optional.of(car));

        final String result = carService.scanChargeCarStatus(CODE);

        assertEquals("stopCharge", result);

    }
    @Test
    void scanChargeCarStatus_charge(){
        when(carRepository.findByCode(CODE)).thenReturn(Optional.of(car));

        final String result = carService.scanChargeCarStatus(CODE);

        assertEquals("charge", result);

    }

    @Test
    void findPlacaByCar(){
        final String placa = "PLACA_TEST";
        car.setPlaca(placa);

        when(carRepository.findByCode(CODE)).thenReturn(Optional.of(car));

        final String result = carService.findPlacaByCar(CODE);

        assertEquals(placa, result);
    }

    @Test
    void assignStationToCar(){
        when(carRepository.findByCode(CODE)).thenReturn(Optional.of(car));
        when(stationRepository.findByCode(CODE)).thenReturn(Optional.of(new Station()));
        when(mapper.toDatabaseEntity(any())).thenReturn(car);

        carService.assignStationToCar(CODE, CODE, USER_NAME);

        verify(carRepository).findByCode(CODE);
        verify(carLogRepository).save(any());
        verify(stationRepository).findByCode(CODE);
        verify(carRepository).findByCode(any());
    }

    @Test
    void removeStationFromCar(){
        when(carRepository.findByCode(CODE)).thenReturn(Optional.of(car));
        when(mapper.toDatabaseEntity(any())).thenReturn(car);

        carService.removeStationFromCar(CODE, USER_NAME);

        verify(carRepository).findByCode(CODE);
        verify(carRepository).findByCode(any());
        verify(carLogRepository).save(any());
    }

    @Test
    void findByUsername(){
        final CarDto response = CarDto.builder().code(CODE).build();

        when(carRepository.findByUser(USER_NAME)).thenReturn(Optional.of(car));
        when(mapper.toCarDto(car)).thenReturn(response);

        final CarDto result = carService.findByUsername(USER_NAME);

        assertEquals(response, result);
    }

    @Test
    void assignUserToCar(){
        when(carRepository.findByCode(CODE)).thenReturn(Optional.of(car));
        when(mapper.toDatabaseEntity(any())).thenReturn(car);

        carService.assignUserToCar(CODE, USER_NAME);

        verify(carRepository).findByCode(CODE);
        verify(carRepository).save(any());
    }

    @Test
    void isUserAssigned(){
        final boolean response = true;

        when(carRepository.findByUser(USER_NAME)).thenReturn(Optional.of(new Car()));

        final boolean result = carService.isUserAssigned(USER_NAME);

        assertEquals(response, result);
    }


}