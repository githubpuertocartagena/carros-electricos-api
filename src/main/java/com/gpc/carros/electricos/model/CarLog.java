package com.gpc.carros.electricos.model;


import com.gpc.carros.electricos.model.enums.BatteryStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GPC_CAR_LOG")
@Data
@Builder(toBuilder = true)
public class CarLog {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "BATTERY_STATUS")
    private BatteryStatus batteryStatus;

    @Column(name = "USERNAME")
    private String username;

}
