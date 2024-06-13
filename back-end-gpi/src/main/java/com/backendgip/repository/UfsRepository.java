package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.Ufs;

@Repository
public interface UfsRepository extends CrudRepository<Ufs, Integer> {

    boolean existsByNombre(String nombre);
    
}
