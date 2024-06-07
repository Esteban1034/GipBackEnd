package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.repository.MantenimientoUnidadRepository;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.model.MantenimientoUnidad;



@Service
public class MantenimientoUnidadServImp implements MantenimientoUnidadService {

    @Autowired
    private MantenimientoUnidadRepository mantenimientounidadRepository;

    public List<MantenimientoUnidad> obtenerMantenimientoUnidad(){
        return (List<MantenimientoUnidad>) this.mantenimientounidadRepository.findAll();
    }

    public MantenimientoUnidad guardarMantenimientoUnidad(MantenimientoUnidad mantenimientoUnidad) {
		return (MantenimientoUnidad) this.mantenimientounidadRepository.save(mantenimientoUnidad);
	}

  // public MantenimientoUnidad obtenerMantenimientoUnidadById(Integer idMantenimientoUnd) {
	//	return (MantenimientoUnidad) this.mantenimientounidadRepository.findById(idMantenimientoUnd).orElseThrow(() -> {
	//		return new ResourceNotFoundException("Mo se encontro la complejidad estipulada con el id:" + idMantenimientoUnd);
	//	});
	//}
}
