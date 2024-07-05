package com.backendgip.service;

import com.backendgip.model.Funcion;

import java.util.List;

public interface FuncionService {

    List<Funcion> geFuncions();

    Funcion sFuncion(Funcion funcion);

    Funcion getFuncionById(Integer id);

    void deleteFuncion(Funcion funcion);

}
