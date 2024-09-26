package com.backendgip.service;

import java.util.List;
import com.backendgip.model.EsfuerzoAgil;
import com.backendgip.model.EstimacionUfs;

public interface EsfuerzoAgilService {

    EsfuerzoAgil save(EsfuerzoAgil esfuerzoAgil);

    List<EsfuerzoAgil> findAll();

    EsfuerzoAgil findById(Integer id);

    List<EsfuerzoAgil> findByEstimacion(EstimacionUfs estimacion);

    EsfuerzoAgil update(EsfuerzoAgil esfuerzoAgil);

    void deleteById(Integer id);
}
