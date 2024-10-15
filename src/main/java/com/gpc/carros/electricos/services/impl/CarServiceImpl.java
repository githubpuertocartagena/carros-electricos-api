package com.gpc.carros.electricos.services.impl;

import com.gpc.carros.electricos.mappers.CarMapper;
import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.CarLog;
import com.gpc.carros.electricos.model.dto.CarDto;
import com.gpc.carros.electricos.model.enums.BatteryStatus;
import com.gpc.carros.electricos.model.enums.TypeEnum;
import com.gpc.carros.electricos.model.response.DeviceResponse;
import com.gpc.carros.electricos.repositories.CarLogRepository;
import com.gpc.carros.electricos.repositories.CarRepository;
import com.gpc.carros.electricos.repositories.StationRepository;
import com.gpc.carros.electricos.services.CarService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CarLogRepository carLogRepository;

    private final StationRepository stationRepository;

    private final CarMapper mapper = Mappers.getMapper(CarMapper.class);

    public CarServiceImpl(final CarRepository carRepository, final CarLogRepository carLogRepository, final StationRepository stationRepository) {
        this.carRepository = carRepository;
        this.carLogRepository = carLogRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public void createCar(final Car car) {
        carRepository.save(car);
    }

    // Return what type of action to make for the user and car
    // by finding the car and user assign status.
    @Override
    public String solveStatus(final String code, final String username) {
        final DeviceResponse scanned = scan(code);
        if (scanned.getType() == TypeEnum.CAR) {
            final CarDto car = scanned.getCar();
            if (car.getStation() == null) {
                return carStatus(car, username);
            } else {
                return "charging";
            }
        } else if (scanned.getType() == TypeEnum.STATION) {
            return "scan car";
        } else {
            throw new RuntimeException("El código no fue identificado");
        }
    }

    private String carStatus(final CarDto car, final String username){
        if (isCarAssigned(car)) {
            if (isCarAndUserAssignedToSameCar(car, username)) {
                return "return";
            } else {
                return "occupied";
            }
        } else {
            return "take";
        }
    }

    @Override
    public String scanChargeCarStatus(final String carCode) {
        final CarDto car = searchCar(carCode);
        if (car.getStation() != null) {
            return "stopCharge";
        } else {
            return "charge";
        }

    }

    @Override
    public String findPlacaByCar(final String carCode) {
        final CarDto car = searchCar(carCode);
        return car.getPlaca();
    }

    @Override
    public void assignStationToCar(final String carCode, final String stationCode, final String username) {
        final CarDto car = searchCar(carCode);
        car.setStation(stationRepository.findByCode(stationCode).get());
        carRepository.save(mapper.toDatabaseEntity(car));
        saveLog(username, BatteryStatus.CHARGING);
    }

    @Override
    public void removeStationFromCar(final String carCode, final String username) {
        final CarDto car = searchCar(carCode);
        car.setStation(null);
        carRepository.save(mapper.toDatabaseEntity(car));
        saveLog(username, BatteryStatus.DISCONNECTED);
    }

    private void saveLog(final String username, final BatteryStatus batteryStatus){
        final CarLog log = CarLog.builder()
                .username(username).build();
        log.setBatteryStatus(batteryStatus);
        carLogRepository.save(log);
    }

    @Override
    public DeviceResponse scan(final String code) {
        return carRepository.findByCode(code)
                .map(car -> new DeviceResponse(car.getCode(), TypeEnum.CAR, mapper.toCarDto(car)))
                .orElse(findStationByCode(code));
    }

    private DeviceResponse findStationByCode(final String code){
        return stationRepository.findByCode(code)
                .map(station -> new DeviceResponse(station.getCode(), TypeEnum.STATION, null))
                .orElse(new DeviceResponse(null, TypeEnum.UNKNOWN, null));
    }


    @Override
    public CarDto findByUsername(final String username) {
        final Car car = carRepository.findByUser(username)
                .orElseThrow(() -> new RuntimeException("No existe carro con el usuario " + username));
        return mapper.toCarDto(car);
    }

    @Override
    public CarDto searchCar(final String carCode) {
        final Car car = carRepository.findByCode(carCode).orElseThrow(() -> new RuntimeException("No existe carro"));
        return mapper.toCarDto(car);
    }

    @Override
    public boolean isUserAssigned(final String username) {
        return carRepository.findByUser(username).isPresent();
    }

    @Override
    public boolean isCarAssigned(final CarDto car) {
        return car.getUsername()!= null;
    }

    @Override
    public boolean isCarAndUserAssignedToSameCar(final CarDto car, final String username) {
        return car.getUsername().equals(username);
    }

    @Override
    public void assignUserToCar(final String carCode, final String username) {
        final CarDto car = searchCar(carCode);
        car.setUsername(username);
        carRepository.save(mapper.toDatabaseEntity(car));
    }
}
