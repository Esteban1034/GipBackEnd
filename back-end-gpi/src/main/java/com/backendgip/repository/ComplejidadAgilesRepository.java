package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.ComplejidadAgiles;

@Repository
public interface ComplejidadAgilesRepository extends CrudRepository<ComplejidadAgiles, Integer>{

    //ComplejidadAgiles findByComplejidad(String complejidadActividad);
    //ComplejidadAgiles findByHora(Integer horaActividad);
}
