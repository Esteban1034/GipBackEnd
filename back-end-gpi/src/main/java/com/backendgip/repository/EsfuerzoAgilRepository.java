package com.backendgip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backendgip.model.EsfuerzoAgil;
import com.backendgip.model.EstimacionUfs;

import java.util.List;


public interface EsfuerzoAgilRepository extends JpaRepository<EsfuerzoAgil, Integer> {
   List<EsfuerzoAgil> findByEstimacion(EstimacionUfs estimacion);
}
