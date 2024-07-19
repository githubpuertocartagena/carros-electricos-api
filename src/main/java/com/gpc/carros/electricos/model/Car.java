package com.gpc.carros.electricos.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GPC_CAR")
@Data
public class Car {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "PLACA")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
