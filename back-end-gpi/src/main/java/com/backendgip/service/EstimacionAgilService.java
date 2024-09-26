package com.backendgip.service;

import java.util.List;
import com.backendgip.model.EstimacionAgil;
import com.backendgip.model.EstimacionUfs;

public interface EstimacionAgilService {

    EstimacionAgil save(EstimacionAgil estimacionAgil);

    List<EstimacionAgil> findAll();

    EstimacionAgil findById(Integer id);

    List<EstimacionAgil> findByEstimacion(EstimacionUfs estimacion);

    EstimacionAgil update(EstimacionAgil estimacionAgil);

    void deleteById(Integer id);
}
