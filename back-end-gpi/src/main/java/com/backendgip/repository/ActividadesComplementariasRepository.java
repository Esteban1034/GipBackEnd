package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.ActividadesComplementarias;
import com.backendgip.model.EstimacionUfs;
import java.util.List;


@Repository
public interface ActividadesComplementariasRepository extends CrudRepository<ActividadesComplementarias, Integer> {
    List<ActividadesComplementarias> findByEstimacion(EstimacionUfs estimacion);
}
