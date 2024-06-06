package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.EtapaProyecto;
import com.backendgip.model.ContenidoUfs;

@Repository
public interface ContenidoUfsRepository extends CrudRepository<ContenidoUfs,Integer> {
    
    boolean existsByNombreCaso(String nombreCaso);
}
