package com.gpc.carros.electricos.controllers;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.dto.CarDto;
import com.gpc.carros.electricos.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @Value("${spring.application.version}")
    private String version;


    /*
     * getStatus
     *
     * Usado para averiguar el proximo paso para el carro despues del escaneo del codigo QR
     * O si es estacion, dirigir el usuario a escanear el carro
     *
     * Retorna:
     *   Si el usuario no tiene carro asignado y el carro no tiene usuario asignado: "take"
     *   Si el carro tiene un usuario asignado y el usuario no tiene carro asignado: "occupied"
     *   Si el carro y usuario estan asignados igual: "return"
     *   Si el carro esta cargando: "charging"
     *   Si es una estacion de carga: "scancar" para escanear el carro.
     *
     * */
    @GetMapping("status/{code}/{username}")
    public String getStatus(@PathVariable("code") final String code, @PathVariable("username") final String username) {
        return carService.solveStatus(code, username);
    }

    /* Get app versión*/
    @GetMapping("app-version")
    public String getStatus() {
        return version;
    }

    /*
     * getChargeStatus
     *
     * Retorna:
     *      si el carro esta cargando: "stopCharge"
     *      si el carro no esta cargando: "charge"
     * */
    @GetMapping("charge-status/{code}")
    public String getChargeStatus(@PathVariable("code") final String carCode) {
        return carService.scanChargeCarStatus(carCode);
    }

    /* Asignación y Entrega de Carros*/
    @PutMapping("assign-car/{code}/{username}")
    public void assignUserToCar(@PathVariable("code") final String code, @PathVariable("username") final String username) {
        carService.assignUserToCar(code, username);
    }

    @PutMapping("return-car/{code}")
    public void returnCar(@PathVariable("code") final String carcode) {
        carService.assignUserToCar(carcode, null);
    }

    @PutMapping("assign-station/{carCode}/{stationCode}/{username}")
    public void chargeCar(@PathVariable("carCode") final String carCode, @PathVariable("stationCode") final String stationCode, @PathVariable("username") final String username) {
        carService.assignStationToCar(carCode, stationCode, username);
    }

    @PutMapping("remove-station/{code}/{username}")
    public void removeStationFromCar(@PathVariable("code") final String code, @PathVariable("username") final String username) {
        carService.removeStationFromCar(code, username);
    }

    //Encontrar datos del carro como la placa
    @GetMapping("placa/{code}")
    public String findPlacaByCar(@PathVariable("code") final String carCode) {
        return carService.findPlacaByCar(carCode);
    }

    @GetMapping("find-car-by-code/{code}")
    public CarDto searchCar(@PathVariable("code") final String code) {
        return carService.searchCar(code);
    }

    @GetMapping("find-car-by-user/{user}")
    public CarDto searchCarByUsername(@PathVariable("user") final String username) {
        return carService.findByUsername(username);
    }

    @PostMapping("create-car")
    public void createCar(@RequestBody final Car car) {
        carService.createCar(car);
    }


}
