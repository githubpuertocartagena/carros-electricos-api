package com.gpc.carros.electricos.services;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.Station;
import com.gpc.carros.electricos.model.User;
import com.gpc.carros.electricos.model.enums.TypeEnum;
import com.gpc.carros.electricos.model.response.DeviceResponse;
import com.gpc.carros.electricos.repositories.CarRepository;
import com.gpc.carros.electricos.repositories.StationRepository;
import com.gpc.carros.electricos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StationRepository stationRepository;

    public void createCar(Car car) {
        carRepository.save(car);
    }

    // Return what type of action to make for the user and car
    // by finding the car and user assign status.
    public String solveStatus(String code, String username) {
        DeviceResponse scanned = scan(code);
        if (scanned.getType() == TypeEnum.CAR) {
            Car car = carRepository.findByCode(code).get();
            if (car.getStation() == null) {
                if (isCarAssigned(code)) {
                    if (isCarAndUserAssignedToSameCar(code, username)) {
                        return "return";
                    } else {
                        return "occupied";
                    }
                } else {
                    return "take";
                }
            } else {
                return "charging";
            }
        } else if (scanned.getType() == TypeEnum.STATION) {
            return "scancar";
        } else {
            throw new RuntimeException("El código no fue identificado");
        }
    }

    public String scanChargeCarStatus(String carCode) {
        Optional<Car> opCar = carRepository.findByCode(carCode);
        if (opCar.isPresent()) {
            Car car = opCar.get();
            if (car.getStation() != null) {
                return "stopCharge";
            } else {
                return "charge";
            }
        } else {
            throw new RuntimeException("El codigo QR escaneado no es un carro");
        }

    }

    public String getPlaca(String carCode) {
        Car car = searchCar(carCode);
        return car.getPlaca();
    }

    public void assignStationToCar(String carCode, String stationCode) {
        Car car = carRepository.findByCode(carCode).get();
        car.setStation(stationRepository.findByCode(stationCode).get());
        carRepository.save(car);
    }

    public void removeStationFromCar(String carCode) {
        Car car = carRepository.findByCode(carCode).get();
        car.setStation(null);
        carRepository.save(car);
    }

    public DeviceResponse scan(String code) {
        Optional<Car> optionalCar = carRepository.findByCode(code);
        if (optionalCar.isPresent()) {
            return new DeviceResponse(optionalCar.get().getCode(), TypeEnum.CAR);
        } else {
            Optional<Station> optionalStation = stationRepository.findByCode(code);
            if (optionalStation.isPresent()) {
                return new DeviceResponse(optionalStation.get().getCode(), TypeEnum.STATION);
            } else {
                return new DeviceResponse(null, TypeEnum.UNKNOWN);
            }
        }
    }

    public Car findByUsername(String username) {
        return carRepository.findByUser(username)
                .orElseThrow(() -> new RuntimeException("No existe carro con el usuario " + username));
    }

    public Car searchCar(String carCode) {
        return carRepository.findByCode(carCode).orElseThrow(() -> new RuntimeException("No existe carro"));
    }

    public boolean isUserAssigned(String username) {
        Optional<Car> car = carRepository.findByUser(username);
        return car.isPresent();
    }

    public boolean isCarAssigned(String carCode) {
        Car car = carRepository.findByCode(carCode).orElseThrow(() -> new RuntimeException("No existe carro"));
        return car.getUser() != null;
    }

    public boolean isCarAndUserAssignedToSameCar(String carCode, String username) {
        Car car = searchCar(carCode);
        return car.getUser().getUsername().equals(username);
    }

    public void assignUserToCar(String carCode, String username) {
        Optional<Car> opCar = carRepository.findByCode(carCode);
        if (opCar.isPresent()) {
            Car car = opCar.get();
            User user = userRepository.findByUsername(username).get();
            car.setUser(user);
            carRepository.save(car);
        } else {
            throw new RuntimeException("El carro no existe");
        }
    }

    public void returnCar(String carCode) {
        Optional<Car> opCar = carRepository.findByCode(carCode);
        if (opCar.isPresent()) {
            Car car = opCar.get();
            car.setUser(null);
            carRepository.save(car);
        } else {
            throw new RuntimeException("El carro no existe");
        }
    }
}
