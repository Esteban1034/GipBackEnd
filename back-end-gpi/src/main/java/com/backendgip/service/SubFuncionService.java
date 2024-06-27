package com.backendgip.service;


import com.backendgip.model.Subfuncion;

import java.util.List;


public interface SubFuncionService {

 Subfuncion createSubfuncion(Subfuncion subfuncion);
    List<Subfuncion> getAllSubfunciones();
}
