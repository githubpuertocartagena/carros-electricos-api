package com.gpc.carros.electricos.model.dto;

import com.gpc.carros.electricos.model.Station;
import lombok.Builder;
import lombok.Data;


@Data
@Builder()
public class CarDto {

    private long id;

    private String code;

    private String placa;

    private Station station;

    private String username;
}
