package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.FasesAgiles;

@Repository
public interface FasesAgilesRepository extends CrudRepository<FasesAgiles, Integer>{
    FasesAgiles findByNombre(String nombre);
}
