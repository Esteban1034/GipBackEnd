package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.ActividadesComplementarias;


@Repository
public interface ActividadesComplementariasRepository extends CrudRepository<ActividadesComplementarias, Integer> {
    
}
