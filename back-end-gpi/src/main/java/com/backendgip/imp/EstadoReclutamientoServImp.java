//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoReclutamiento;
import com.backendgip.repository.EstadoReclutamientoRepository;
import com.backendgip.service.EstadoReclutamientoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoReclutamientoServImp implements EstadoReclutamientoService {
	@Autowired
	private EstadoReclutamientoRepository estadoReclutamientoRepository;

	public EstadoReclutamientoServImp() {
	}

	public List<EstadoReclutamiento> getEstadoReclutamientoes() {
		return (List) this.estadoReclutamientoRepository.findAll();
	}

	public EstadoReclutamiento saveEstadoReclutamiento(EstadoReclutamiento estadoReclutamiento) {
		return (EstadoReclutamiento) this.estadoReclutamientoRepository.save(estadoReclutamiento);
	}

	public void deleteEstadoReclutamiento(EstadoReclutamiento estadoReclutamiento) {
		this.estadoReclutamientoRepository.delete(estadoReclutamiento);
	}

	public EstadoReclutamiento getEstadoReclutamientoById(Integer id) {
		return (EstadoReclutamiento) this.estadoReclutamientoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	@Override
	public EstadoReclutamiento findByEstado(String estadoReclutamiento) {
		return this.estadoReclutamientoRepository.findByEstado(estadoReclutamiento);
	}
}
