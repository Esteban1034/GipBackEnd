package com.backendgip.service;

import java.util.List;

import com.backendgip.model.TipoActividadComplementaria;

public interface TipoActividadComplementariaService {
 

    TipoActividadComplementaria getTipoActividadNombre(String nombre);

    List<TipoActividadComplementaria> getActividades();
    

    TipoActividadComplementaria saveActividad(TipoActividadComplementaria tipoActividadComplementaria);


    TipoActividadComplementaria getActividadById(Integer id);

    void deleteActividad(Integer id);
}
