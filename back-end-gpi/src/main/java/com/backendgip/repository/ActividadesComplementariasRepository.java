package com.backendgip.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.ActividadesComplementarias;
import com.backendgip.model.EstimacionUfs;


@Repository
public interface ActividadesComplementariasRepository extends CrudRepository<ActividadesComplementarias, Integer> {
    List<ActividadesComplementarias> findByEstimacion(EstimacionUfs estimacion);
}
