//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Profesion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionRepository extends CrudRepository<Profesion, Integer> {
    Profesion findByProfesion (String profesion);
}
