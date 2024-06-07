//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.MantenimientoUnidad;
import java.time.LocalDate;
import java.util.List;

public interface MantenimientoUnidadService {
	List<MantenimientoUnidad> obtenerMantenimientoUnidad();

	MantenimientoUnidad guardarMantenimientoUnidad(MantenimientoUnidad mantenimientounidad);

	// void eliminarMantenimientoUnidad(MantenimientoUnidad mantenimientounidad);

	// MantenimientoUnidadService obtenerMantenimientoUnidadById(Integer idMantenimientoUnidad);
}
