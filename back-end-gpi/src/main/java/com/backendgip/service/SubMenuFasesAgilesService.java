package com.backendgip.service;

import java.util.List;
import com.backendgip.model.FasesAgiles;
import com.backendgip.model.SubMenuFasesAgiles;

public interface SubMenuFasesAgilesService {

    SubMenuFasesAgiles save(SubMenuFasesAgiles subMenuFasesAgiles);

    SubMenuFasesAgiles findById(Integer id);

    List<SubMenuFasesAgiles> findByFasesAgilesId(Integer fasesAgilesId);

    SubMenuFasesAgiles findByNombre(String nombre);

    List<SubMenuFasesAgiles> findByNombreAndFasesAgiles(String nombre, FasesAgiles fasesAgiles);

    void deleteByNombre(String nombre);

    SubMenuFasesAgiles updateNombre(Integer id, String nuevoNombre);

    void deleteById(Integer id);

    List<SubMenuFasesAgiles> findAll();
}
