package com.gpc.carros.electricos.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GPC_USER")
@Data
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USERNAME")
    private String username;
}
