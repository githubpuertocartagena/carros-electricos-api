package com.gpc.carros.electricos.model;


import com.gpc.carros.electricos.model.enums.TypeForm;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "GPC_FORM")
@Data
public class Form {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @ManyToOne()
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private TypeForm tipo;

    @Column(name = "CINTURONES")
    private String cinturones;

    @Column(name = "LUCES")
    private String luces;

    @Column(name = "BANDERIN")
    private String banderin;

    @Column(name = "LIMPIABRISAS")
    private String limpiabrisas;

    @Column(name = "LLANTAS")
    private String llantas;

    @Column(name = "BOCINAS")
    private String bocinas;

    @Column(name = "RETROVISORES")
    private String retrovisores;

    @Column(name = "ALARMA_DE_REVERSA")
    private String alarmaDeReversa;

    @Column(name = "EXTINTOR")
    private String extintor;

    @Column(name = "CONOS")
    private String conos;

    @Column(name = "BATERIA")
    private String bateria;

    @Column(name = "OBSERVACIONES")
    private String observaciones;



}
