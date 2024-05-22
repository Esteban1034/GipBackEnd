package com.backendgip.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.NotasSemanaPSR;


@Repository
public interface NotasSemanaPSRRepository extends CrudRepository<NotasSemanaPSR, Integer> {

    List<NotasSemanaPSR> findByFechaCreacionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    NotasSemanaPSR findByFechaCreacion(LocalDate fechaC);

}




