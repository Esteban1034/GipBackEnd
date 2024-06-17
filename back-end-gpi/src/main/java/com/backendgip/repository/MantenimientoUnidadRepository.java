package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;

@Repository
public interface MantenimientoUnidadRepository extends CrudRepository<MantenimientoUnidad, Integer> {
        MantenimientoPesoHora findByPeso(Integer peso);

}
