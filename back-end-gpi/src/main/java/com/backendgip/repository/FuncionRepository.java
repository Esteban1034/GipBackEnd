package com.backendgip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.Funcion;

@Repository
public interface FuncionRepository extends CrudRepository<Funcion, Integer> {

    List<Funcion> findByUnidadFuncionalId(Integer UnidadFuncionalId);

}
