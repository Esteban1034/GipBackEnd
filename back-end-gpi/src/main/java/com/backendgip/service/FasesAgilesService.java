package com.backendgip.service;

import com.backendgip.model.FasesAgiles;
import java.util.List;

public interface FasesAgilesService {

    FasesAgiles getFasesAgilesById(Integer id);

    List<FasesAgiles> getFasesAgiles();

    FasesAgiles saveFasesAgiles(FasesAgiles fasesAgiles);

    void deleteFases(Integer id);

    FasesAgiles findByNombre(String nombre);
}
