//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;


import com.backendgip.model.EstimacionUfs;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface EstimacionesUfsRepository extends CrudRepository<EstimacionUfs, Integer> {
    @Query("SELECT MAX(e.id) FROM EstimacionUfs e")
    Long findMaxId();

}
