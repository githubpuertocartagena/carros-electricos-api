package com.gpc.carros.electricos.model.request;

import com.gpc.carros.electricos.model.enums.TypeEnum;
import com.gpc.carros.electricos.model.enums.TypeForm;
import lombok.Data;

import javax.persistence.Column;

@Data
public class FormRequest {


    private String carCode;

    private String username;

    private TypeForm tipo;

    private String cinturones;

    private String luces;

    private String banderin;

    private String limpiabrisas;

    private String llantas;

    private String bocinas;

    private String retrovisores;

    private String alarmaDeReversa;

    private String extintor;
    
    private String conos;

    private String bateria;

    private String observaciones;
}
