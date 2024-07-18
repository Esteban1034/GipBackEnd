package com.backendgip.service;

import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;

import java.util.List;

public interface FuncionService {

    List<Funcion> findByUnidadFuncionalId(Integer UnidadFuncionalId);

    List<Funcion> geFuncions();

    Funcion sFuncion(Funcion funcion);

    Funcion getFuncionById(Integer id);

    void deleteFuncion(Funcion funcion);
    List<Funcion> findByEstimacionUfs(EstimacionUfs estimacionUfs);

}
