package com.gpc.carros.electricos.model.response;

import com.gpc.carros.electricos.model.dto.CarDto;
import com.gpc.carros.electricos.model.enums.TypeEnum;
import lombok.Data;

@Data
public class DeviceResponse {

    private String code;

    private TypeEnum type;

    private CarDto car;

    public DeviceResponse(final String code, final TypeEnum type, final CarDto car) {
        this.code = code;
        this.type = type;
        this.car = car;
    }
}
