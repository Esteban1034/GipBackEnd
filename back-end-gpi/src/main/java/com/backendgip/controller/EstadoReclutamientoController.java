//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.EstadoReclutamiento;
import com.backendgip.repository.EstadoReclutamientoRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.EstadoReclutamientoService;
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
public class EstadoReclutamientoController {
	@Autowired
	private EstadoReclutamientoService estadoReclutamientoService;
	@Autowired
	private EstadoReclutamientoRepository estadoReclutamientoRepository;
	@Autowired
	private LogSistemaService logService;

	public EstadoReclutamientoController() {
	}

	@GetMapping({ "/estados" })
	public List<EstadoReclutamiento> getAllEstadoReclutamientoes() {
		return this.estadoReclutamientoService.getEstadoReclutamientoes();
	}

	@PostMapping({ "/estados" })
	public ResponseEntity<?> saveEstadoReclutamiento(@RequestBody EstadoReclutamiento estadoReclutamiento) {
		EstadoReclutamiento existingEstadoReclutamiento = this.estadoReclutamientoService.findByEstado(estadoReclutamiento.getEstado());
		if (existingEstadoReclutamiento != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El estado ya existe: " + estadoReclutamiento.getEstado());
		}

		EstadoReclutamiento createdEstadoReclutamiento = this.estadoReclutamientoService.saveEstadoReclutamiento(estadoReclutamiento);

		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(EstadoReclutamiento.class.toString());
		log.setIdAccion(createdEstadoReclutamiento.getId());
		log.setDescripcion(createdEstadoReclutamiento.toString());
		this.logService.saveLog(log);

		return ResponseEntity.ok(createdEstadoReclutamiento);
	}

	@PutMapping({ "/estados/{id}" })
	public ResponseEntity<?> updateEstadoReclutamiento(@PathVariable Integer id,
			@RequestBody EstadoReclutamiento estadoReclutamientoDetails) {
		EstadoReclutamiento estadoReclutamiento = this.estadoReclutamientoRepository.findById(id).orElse(null);

		if (estadoReclutamiento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		EstadoReclutamiento existingEstadoReclutamiento = this.estadoReclutamientoService.findByEstado(estadoReclutamientoDetails.getEstado());
		if (existingEstadoReclutamiento != null && !existingEstadoReclutamiento.getId().equals(estadoReclutamiento.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El estado ya existe: " + estadoReclutamientoDetails.getEstado());
		}

		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(estadoReclutamiento.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(estadoReclutamiento.getId());
		log.setTabla(estadoReclutamiento.getClass().toString());
		this.logService.saveLog(log);
		estadoReclutamiento.setEstado(estadoReclutamientoDetails.getEstado());
		estadoReclutamiento.setDescripcion(estadoReclutamientoDetails.getDescripcion());
		EstadoReclutamiento updatedEstadoReclutamiento = this.estadoReclutamientoService.saveEstadoReclutamiento(estadoReclutamiento);

		return ResponseEntity.ok(updatedEstadoReclutamiento);
	}

	@GetMapping({ "/estados/{id}" })
	public ResponseEntity<EstadoReclutamiento> getEstadoReclutamientoById(@PathVariable Integer id) {
		EstadoReclutamiento estadoReclutamiento = (EstadoReclutamiento) this.estadoReclutamientoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(estadoReclutamiento); 
	}

	@DeleteMapping({ "/estados/{id}" })
	public ResponseEntity<?> deleteEstadoReclutamiento(@PathVariable Integer id) {
		EstadoReclutamiento estadoReclutamiento = this.estadoReclutamientoRepository.findById(id).orElse(null);

		if (estadoReclutamiento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(estadoReclutamiento.getId());
		log.setDescripcion(estadoReclutamiento.toString());
		log.setTabla(EstadoReclutamiento.class.toString());
		this.logService.saveLog(log);

		this.estadoReclutamientoRepository.deleteById(estadoReclutamiento.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
