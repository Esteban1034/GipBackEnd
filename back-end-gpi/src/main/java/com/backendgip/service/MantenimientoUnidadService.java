package com.backendgip.service;

import com.backendgip.model.Cliente;
import com.backendgip.model.EstadoCliente;
import com.backendgip.model.MantenimientoUnidad;
import java.util.List;


public interface MantenimientoUnidadService {

    MantenimientoUnidad getMantenimientoUndById(Integer idMantenimientoUnd);
    
    List<MantenimientoUnidad> getMantenimientos();

    MantenimientoUnidad saveMantenimientoUnd(MantenimientoUnidad mantenimientoUnidad);

    boolean validarNombre(String nombre);

    void deleteById(Integer id);


}
