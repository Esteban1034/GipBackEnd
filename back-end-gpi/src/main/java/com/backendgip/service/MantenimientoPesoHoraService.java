package com.backendgip.service;

import java.util.List;

import com.backendgip.model.MantenimientoPesoHora;

public interface MantenimientoPesoHoraService {
    
    List<MantenimientoPesoHora> getPesoHora();
    
    MantenimientoPesoHora buscarPeso(Integer peso);

    MantenimientoPesoHora saveMantenimientoPesoHora(MantenimientoPesoHora mantenimientoPesoHora);
}
