package com.backendgip.repository;

import com.backendgip.model.Modelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends CrudRepository<Modelo, Integer> {   
}

