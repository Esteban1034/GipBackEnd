//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.FuenteReclutamiento;
import com.backendgip.repository.FuenteReclutamientoRepository;
import com.backendgip.service.FuenteReclutamientoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuenteReclutamientoServImp implements FuenteReclutamientoService {
	@Autowired
	private FuenteReclutamientoRepository fuenteReclutamientoRepository;

	public FuenteReclutamientoServImp() {
	}

	public List<FuenteReclutamiento> getFuenteReclutamientoes() {
		return (List) this.fuenteReclutamientoRepository.findAll();
	}

	public FuenteReclutamiento saveFuenteReclutamiento(FuenteReclutamiento fuenteReclutamiento) {
		return (FuenteReclutamiento) this.fuenteReclutamientoRepository.save(fuenteReclutamiento);
	}

	public void deleteFuenteReclutamiento(FuenteReclutamiento fuenteReclutamiento) {
		this.fuenteReclutamientoRepository.delete(fuenteReclutamiento);
	}

	public FuenteReclutamiento getFuenteReclutamientoById(Integer id) {
		return (FuenteReclutamiento) this.fuenteReclutamientoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	@Override
	public FuenteReclutamiento findByFuente(String fuenteReclutamiento) {
		return this.fuenteReclutamientoRepository.findByFuente(fuenteReclutamiento);
	}
}
