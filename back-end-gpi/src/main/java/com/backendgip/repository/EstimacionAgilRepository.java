package com.backendgip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backendgip.model.EstimacionAgil;
import com.backendgip.model.EstimacionUfs;

import java.util.List;


public interface EstimacionAgilRepository extends JpaRepository<EstimacionAgil, Integer> {
    List<EstimacionAgil> findByEstimacion(EstimacionUfs estimacion);
}
