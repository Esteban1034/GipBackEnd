package com.backendgip.service;

import java.util.List;

import com.backendgip.model.ComplejidadAgiles;

public interface ComplejidadAgilesService {

    ComplejidadAgiles getComplejidadesById(Integer id);

    List<ComplejidadAgiles> getAllComplejidadAgiles();

    ComplejidadAgiles saveComplejidadAgiles(ComplejidadAgiles complejidadAgiles);

    void deleteComplejidades(Integer id);
}    

