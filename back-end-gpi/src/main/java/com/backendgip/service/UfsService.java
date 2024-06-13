package com.backendgip.service;

import java.util.List;

import com.backendgip.model.Ufs;

public interface UfsService {

    List<Ufs> getUfs();

    Ufs saveUfs(Ufs Ufs);

    void deleteUfs(Ufs Ufs);

    Ufs getUfsById(Integer idUfs);
}
