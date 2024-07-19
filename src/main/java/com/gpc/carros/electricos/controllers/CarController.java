package com.gpc.carros.electricos.controllers;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CarController {
    @Autowired
    private CarService carService;


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
    public String getStatus(@PathVariable("code") String code, @PathVariable("username") String username) {
        return carService.solveStatus(code, username);
    }

    /*
     * getChargeStatus
     *
     * Retorna:
     *      si el carro esta cargando: "stopCharge"
     *      si el carro no esta cargando: "charge"
     * */
    @GetMapping("charge-status/{code}")
    public String getChargeStatus(@PathVariable("code") String carCode) {
        return carService.scanChargeCarStatus(carCode);
    }

    /* Asignación y Entrega de Carros*/
    @PutMapping("assign-car/{code}/{username}")
    public void assignUserToCar(@PathVariable("code") String code, @PathVariable("username") String username) {
        carService.assignUserToCar(code, username);
    }

    @PutMapping("return-car/{code}")
    public void returnCar(@PathVariable("code") String carcode) {
        carService.returnCar(carcode);
    }

    @PutMapping("assign-station/{carCode}/{stationCode}")
    public void chargeCar(@PathVariable("carCode") String carCode, @PathVariable("stationCode") String stationCode) {
        carService.assignStationToCar(carCode, stationCode);
    }

    @PutMapping("remove-station/{code}")
    public void removeStationFromCar(@PathVariable("code") String code) {
        carService.removeStationFromCar(code);
    }

    //Encontrar datos del carro como la placa
    @GetMapping("placa/{code}")
    public String getPlaca(@PathVariable("code") String carCode) {
        return carService.getPlaca(carCode);
    }

    @GetMapping("find-car-by-code/{code}")
    public Car searchCar(@PathVariable("code") String code) {
        return carService.searchCar(code);
    }

    @GetMapping("find-car-by-user/{user}")
    public Car searchCarByUsername(@PathVariable("user") String username) {
        return carService.findByUsername(username);
    }

    @PostMapping("create-car")
    public void createCar(@RequestBody Car car) {
        carService.createCar(car);
    }


}
