package com.gpc.carros.electricos.services;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.dto.CarDto;
import com.gpc.carros.electricos.model.response.DeviceResponse;

public interface CarService {

    void createCar(Car car);

    String solveStatus(String code, String username);

    String scanChargeCarStatus(String carCode);

    String findPlacaByCar(String carCode);

    void assignStationToCar(String carCode, String stationCode, String username);

    void removeStationFromCar(String carCode, String username);

    DeviceResponse scan(String code);

    CarDto findByUsername(String username);

    CarDto searchCar(String carCode);

    boolean isUserAssigned(String username);

    boolean isCarAssigned(CarDto car);

    boolean isCarAndUserAssignedToSameCar(CarDto car, String username);

    void assignUserToCar(String carCode, String username);
}
