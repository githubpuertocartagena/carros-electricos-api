package com.gpc.carros.electricos.model.response;

import com.gpc.carros.electricos.model.enums.TypeEnum;
import lombok.Data;

@Data
public class DeviceResponse {

    private String code;

    private TypeEnum type;

    public DeviceResponse(String code, TypeEnum type) {
        this.code = code;
        this.type = type;
    }
}
