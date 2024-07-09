package com.backendgip.service;


import com.backendgip.model.ActividadesComplementarias;
import java.util.List;

public interface ActividadesComplementariasService {

    ActividadesComplementarias saveActividad(ActividadesComplementarias actividad);

    List<ActividadesComplementarias> getActividades();

    void deleteActividad(Integer id);

    ActividadesComplementarias getById(Integer id);


}
