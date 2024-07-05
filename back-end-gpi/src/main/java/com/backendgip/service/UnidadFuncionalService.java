package com.backendgip.service;

import java.util.List;

import com.backendgip.model.UnidadFuncional;
import com.backendgip.model.EstimacionUfs;
public interface UnidadFuncionalService {

    List<UnidadFuncional> getUfs();

    List<UnidadFuncional> findByEstimacionUfs (EstimacionUfs estimacionUfs);

    UnidadFuncional saveUfs(UnidadFuncional Ufs);

    void deleteUfs(UnidadFuncional Ufs);

    UnidadFuncional getUfsById(Integer idUfs);
}
