//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.TipoIdentificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIdentificacionRepository extends CrudRepository<TipoIdentificacion, Integer> {
    TipoIdentificacion findByTipoAndAbreviatura (String tipo, String abreviatura);
}
