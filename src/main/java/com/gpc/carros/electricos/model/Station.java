package com.gpc.carros.electricos.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GPC_STATION")
@Data
public class Station {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CODE")
    private String code;


}

