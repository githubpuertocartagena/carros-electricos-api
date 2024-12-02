package com.gpc.carros.electricos.repositories;

import com.gpc.carros.electricos.model.CarLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarLogRepository extends JpaRepository<CarLog, Long> {

}
