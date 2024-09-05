package com.backendgip.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendgip.model.FasesAgiles;
import com.backendgip.model.SubMenuFasesAgiles;

@Repository
public interface SubMenuFasesAgilesRepository extends CrudRepository<SubMenuFasesAgiles, Integer> {
    
    List<SubMenuFasesAgiles> findByFasesAgiles_Id(Integer fasesAgilesId);

    Optional<SubMenuFasesAgiles> findByNombre(String nombre);

    List<SubMenuFasesAgiles> findByNombreAndFasesAgiles(String nombre, FasesAgiles fasesAgiles);

    boolean existsByNombre(String nombre);

    void deleteByNombre(String nombre);
}
