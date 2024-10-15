package com.gpc.carros.electricos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SuppressWarnings("all")
@SpringBootApplication
@AutoConfiguration
@EnableJpaRepositories(basePackages = "com.gpc.carros.electricos.repositories")
public class CarrosElectricosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrosElectricosApplication.class, args);
    }

}
