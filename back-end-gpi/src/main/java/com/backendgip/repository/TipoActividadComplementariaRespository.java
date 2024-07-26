package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.TipoActividadComplementaria;
import java.util.List;


@Repository
public interface TipoActividadComplementariaRespository extends CrudRepository<TipoActividadComplementaria, Integer>{
    TipoActividadComplementaria findByNombre(String nombre);
}
