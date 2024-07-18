package com.backendgip.service;


import com.backendgip.model.ActividadesComplementarias;
import com.backendgip.model.EstimacionUfs;

import java.util.List;

public interface ActividadesComplementariasService {

    ActividadesComplementarias saveActividad(ActividadesComplementarias actividad);

    List<ActividadesComplementarias> getActividades();

    void deleteActividad(Integer id);

    ActividadesComplementarias getById(Integer id);

    List<ActividadesComplementarias> getActividadesEstimacion(EstimacionUfs estimacion);

}
