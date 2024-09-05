package com.backendgip.service;

import java.util.List;

import com.backendgip.model.ActividadesAgiles;

public interface ActividadesAgilesService {

    List<ActividadesAgiles> getActividades();

	ActividadesAgiles saveActividad(ActividadesAgiles actividad);

	void deleteActividad(ActividadesAgiles actividad);

	ActividadesAgiles getActividadById(Integer idActividad);
}
