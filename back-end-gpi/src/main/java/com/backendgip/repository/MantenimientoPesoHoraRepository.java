package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.MantenimientoPesoHora;
import java.util.List;


@Repository
public interface MantenimientoPesoHoraRepository extends CrudRepository<MantenimientoPesoHora,Integer> {
    MantenimientoPesoHora findByPeso(Integer peso);

}
