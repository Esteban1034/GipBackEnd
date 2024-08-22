package com.backendgip.service;

import java.util.List;

import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.model.TipoActividadComplementariaComplemento;

public interface TipoActividadComplementariaComplementoServide {
    
    List<TipoActividadComplementariaComplemento> getActividadesTipoActividad(TipoActividadComplementaria actividad);

    TipoActividadComplementariaComplemento createActividadComplementariaComplemento(TipoActividadComplementariaComplemento actividad);

    void deleteActividadComplementaria(Integer id);

    List<TipoActividadComplementariaComplemento> getATipoActividadComplementariaComplementos();
}
