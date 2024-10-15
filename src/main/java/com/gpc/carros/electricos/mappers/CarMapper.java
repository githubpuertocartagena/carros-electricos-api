package com.gpc.carros.electricos.mappers;

import com.gpc.carros.electricos.model.Car;
import com.gpc.carros.electricos.model.dto.CarDto;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {

    CarDto toCarDto(Car car);

    Car toDatabaseEntity(CarDto carDto);
}
