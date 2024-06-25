package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.MantenimientoUnidad;
import java.util.List;


@Repository
public interface MantenimientoUnidadRepository extends CrudRepository<MantenimientoUnidad, Integer> {
    Boolean findByNombre(String nombre);
}
