//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.FuenteReclutamiento;
import com.backendgip.repository.FuenteReclutamientoRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.FuenteReclutamientoService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/reclutamiento" })
public class FuenteReclutamientoController {
	@Autowired
	private FuenteReclutamientoService fuenteReclutamientoService;
	@Autowired
	private FuenteReclutamientoRepository fuenteReclutamientoRepository;
	@Autowired
	private LogSistemaService logService;

	public FuenteReclutamientoController() {
	}

	@GetMapping({ "/fuentes" })
	public List<FuenteReclutamiento> getAllFuenteReclutamientoes() {
		return this.fuenteReclutamientoService.getFuenteReclutamientoes();
	}

	@PostMapping({ "/fuentes" })
	public ResponseEntity<?> saveFuenteReclutamiento(@RequestBody FuenteReclutamiento fuenteReclutamiento) {
		FuenteReclutamiento existingFuenteReclutamiento = this.fuenteReclutamientoService.findByFuente(fuenteReclutamiento.getFuente());
		if (existingFuenteReclutamiento != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La fuente de reclutamiento ya existe: " + fuenteReclutamiento.getFuente());
		}

		FuenteReclutamiento createdFuenteReclutamiento = this.fuenteReclutamientoService.saveFuenteReclutamiento(fuenteReclutamiento);

		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(FuenteReclutamiento.class.toString());
		log.setIdAccion(createdFuenteReclutamiento.getId());
		log.setDescripcion(createdFuenteReclutamiento.toString());
		this.logService.saveLog(log);

		return ResponseEntity.ok(createdFuenteReclutamiento);
	}

	@PutMapping({ "/fuentes/{id}" })
	public ResponseEntity<?> updateFuenteReclutamiento(@PathVariable Integer id,
			@RequestBody FuenteReclutamiento fuenteReclutamientoDetails) {
		FuenteReclutamiento fuenteReclutamiento = this.fuenteReclutamientoRepository.findById(id).orElse(null);

		if (fuenteReclutamiento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		FuenteReclutamiento existingFuenteReclutamiento = this.fuenteReclutamientoService.findByFuente(fuenteReclutamientoDetails.getFuente());
		if (existingFuenteReclutamiento != null && !existingFuenteReclutamiento.getId().equals(fuenteReclutamiento.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La fuente de reclutamiento ya existe: " + fuenteReclutamientoDetails.getFuente());
		}

		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(fuenteReclutamiento.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(fuenteReclutamiento.getId());
		log.setTabla(fuenteReclutamiento.getClass().toString());
		this.logService.saveLog(log);
		fuenteReclutamiento.setFuente(fuenteReclutamientoDetails.getFuente());
		fuenteReclutamiento.setDescripcion(fuenteReclutamientoDetails.getDescripcion());
		FuenteReclutamiento updatedFuenteReclutamiento = this.fuenteReclutamientoService.saveFuenteReclutamiento(fuenteReclutamiento);

		return ResponseEntity.ok(updatedFuenteReclutamiento);
	}

	@GetMapping({ "/fuentes/{id}" })
	public ResponseEntity<FuenteReclutamiento> getFuenteReclutamientoById(@PathVariable Integer id) {
		FuenteReclutamiento fuenteReclutamiento = (FuenteReclutamiento) this.fuenteReclutamientoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(fuenteReclutamiento); 
	}

	@DeleteMapping({ "/fuentes/{id}" })
	public ResponseEntity<?> deleteFuenteReclutamiento(@PathVariable Integer id) {
		FuenteReclutamiento fuenteReclutamiento = this.fuenteReclutamientoRepository.findById(id).orElse(null);

		if (fuenteReclutamiento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(fuenteReclutamiento.getId());
		log.setDescripcion(fuenteReclutamiento.toString());
		log.setTabla(FuenteReclutamiento.class.toString());
		this.logService.saveLog(log);

		this.fuenteReclutamientoRepository.deleteById(fuenteReclutamiento.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
