package com.backendgip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.Subfuncion;

@Repository
public interface SubfuncionRepository  extends CrudRepository<Subfuncion, Integer> {
    
}
