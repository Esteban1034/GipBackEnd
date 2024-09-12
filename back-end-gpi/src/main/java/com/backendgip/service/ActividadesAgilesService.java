package com.backendgip.service;

import java.util.List;

import com.backendgip.model.ActividadesAgiles;
import com.backendgip.model.SubMenuFasesAgiles;

public interface ActividadesAgilesService {

    List<ActividadesAgiles> getActividades();

	ActividadesAgiles saveActividad(ActividadesAgiles actividad);

	void deleteActividad(ActividadesAgiles actividad);

	ActividadesAgiles getActividadById(Integer idActividad);

	List<ActividadesAgiles> findBySubFase(SubMenuFasesAgiles subMenuFasesAgiles);
}
