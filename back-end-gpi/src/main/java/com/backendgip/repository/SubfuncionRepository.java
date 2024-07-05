package com.backendgip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;

@Repository
public interface SubfuncionRepository  extends CrudRepository<Subfuncion, Integer> {
    List<Subfuncion> findByFuncion(Funcion funcion);
    List<Subfuncion> findByFuncion_UnidadFuncional_EstimacionUfs(EstimacionUfs estimacionUfs);
}
