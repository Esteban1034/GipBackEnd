package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.ActividadesAgiles;

@Repository
public interface ActividadesAgilesRepository extends CrudRepository<ActividadesAgiles, Integer>{

    
} 
