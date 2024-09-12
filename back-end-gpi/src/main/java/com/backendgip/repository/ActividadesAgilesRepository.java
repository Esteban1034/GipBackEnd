package com.backendgip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.ActividadesAgiles;
import com.backendgip.model.SubMenuFasesAgiles;

@Repository
public interface ActividadesAgilesRepository extends CrudRepository<ActividadesAgiles, Integer>{

    List<ActividadesAgiles> findBySubMenuFasesAgiles(SubMenuFasesAgiles subMenuFasesAgiles);
} 
