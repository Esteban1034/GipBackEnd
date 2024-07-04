

package com.backendgip.repository;

import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.EstimacionUfs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoUfsRepository extends CrudRepository<ContenidoUfs, Integer> {
    @Query(value = "SELECT max(c.id) FROM ContenidoUfs c")
    Long findMaxId();

    @Query("SELECT c FROM ContenidoUfs c WHERE c.estimacionUfs.id = (SELECT MAX(e.id) FROM EstimacionUfs e)")
    List<ContenidoUfs> findAllByUltimoIdEstimacion();
}