package com.backendgip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.model.TipoActividadComplementariaComplemento;

@Repository
public interface TipoActividadComplementariaComplementoRepository extends CrudRepository<TipoActividadComplementariaComplemento ,Integer> {

    List<TipoActividadComplementariaComplemento> findByActividad(TipoActividadComplementaria actividad);
    
} 
