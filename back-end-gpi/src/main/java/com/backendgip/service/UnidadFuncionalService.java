package com.backendgip.service;

import java.util.List;

import com.backendgip.model.UnidadFuncional;

public interface UnidadFuncionalService {

    List<UnidadFuncional> getUfs();

    UnidadFuncional saveUfs(UnidadFuncional Ufs);

    void deleteUfs(UnidadFuncional Ufs);

    UnidadFuncional getUfsById(Integer idUfs);
}
