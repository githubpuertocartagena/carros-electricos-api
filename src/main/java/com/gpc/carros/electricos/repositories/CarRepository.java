package com.gpc.carros.electricos.repositories;

import com.gpc.carros.electricos.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByUser(@Param("username") String username);

    Optional<Car> findByCode(String code);

}
