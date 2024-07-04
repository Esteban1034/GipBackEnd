package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.UnidadFuncional;

@Repository
public interface UnidadFuncionalRepository extends CrudRepository<UnidadFuncional, Integer> {

    boolean existsByNombre(String nombre);

    
}
