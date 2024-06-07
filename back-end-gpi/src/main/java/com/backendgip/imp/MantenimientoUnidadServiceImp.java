//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.repository.MantenimientoUnidadRepository;
import com.backendgip.service.MantenimientoUnidadService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MantenimientoUnidadServiceImp implements MantenimientoUnidadService {
	@Autowired
	private MantenimientoUnidadRepository mantenimientounidadRepository;
	


	public List<MantenimientoUnidad> obtenerMantenimientoUnidad() {
		return (List) this.mantenimientounidadRepository.findAll();
	}

	public MantenimientoUnidad guardarMantenimientoUnidad(MantenimientoUnidad mantenimientounidad) {
		return (MantenimientoUnidad) this.mantenimientounidadRepository.save(mantenimientounidad);
	}
/* 
	public void eliminarMantenimientoUnidad(MantenimientoUnidad mantenimientoUnidad) {
		this.mantenimientounidadRepository.delete(mantenimientoUnidad);
	}

	public MantenimientoUnidad obtenerMantenimientoUnidadById(Integer idMantenimientoUnidad) {
		return (MantenimientoUnidad) this.mantenimientounidadRepository.findById(idMantenimientoUnidad).orElseThrow(() -> {
			return new ResourceNotFoundException("La unidad de complejidad no existe con el ID" + idMantenimientoUnidad);
		});
	}*/

	

}
