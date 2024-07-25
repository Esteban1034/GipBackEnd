package com.backendgip.service;


import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;

import java.util.List;


public interface SubFuncionService {

 Subfuncion createSubfuncion(Subfuncion subfuncion);

    List<Subfuncion> findByFuncionId(Integer funcionId);
    List<Subfuncion> getAllSubfunciones();
    List<Subfuncion> findByFuncion(Funcion funcion);
    List<Subfuncion> findByEstimacionUfs(EstimacionUfs estimacionUfs);
    void deleteSubfuncion(Subfuncion subfuncion);
}
