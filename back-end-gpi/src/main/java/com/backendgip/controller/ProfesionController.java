//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Profesion;
import com.backendgip.repository.ProfesionRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.ProfesionService;
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
public class ProfesionController {
	@Autowired
	private ProfesionService profesionService;
	@Autowired
	private ProfesionRepository profesionRepository;
	@Autowired
	private LogSistemaService logService;

	public ProfesionController() {
	}

	@GetMapping({ "/profesiones" })
	public List<Profesion> getAllProfesiones() {
		return this.profesionService.getProfesiones();
	}

	@PostMapping({ "/profesiones" })
	public ResponseEntity<?> saveProfesion(@RequestBody Profesion profesion) {
		Profesion existingProfesion = this.profesionService.findByProfesion(profesion.getProfesion());
		if (existingProfesion != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La profesión ya existe: " + profesion.getProfesion());
		}

		Profesion createdProfesion = this.profesionService.saveProfesion(profesion);

		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(Profesion.class.toString());
		log.setIdAccion(createdProfesion.getId());
		log.setDescripcion(createdProfesion.toString());
		this.logService.saveLog(log);

		return ResponseEntity.ok(createdProfesion);
	}

	@PutMapping({ "/profesiones/{id}" })
	public ResponseEntity<?> updateProfesion(@PathVariable Integer id,
			@RequestBody Profesion profesionDetails) {
		Profesion profesion = this.profesionRepository.findById(id).orElse(null);

		if (profesion == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		Profesion existingProfesion = this.profesionService.findByProfesion(profesionDetails.getProfesion());
		if (existingProfesion != null && !existingProfesion.getId().equals(profesion.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La profesión ya existe: " + profesionDetails.getProfesion());
		}

		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(profesion.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(profesion.getId());
		log.setTabla(profesion.getClass().toString());
		this.logService.saveLog(log);
		profesion.setProfesion(profesionDetails.getProfesion());
		profesion.setDescripcion(profesionDetails.getDescripcion());
		Profesion updatedProfesion = this.profesionService.saveProfesion(profesion);

		return ResponseEntity.ok(updatedProfesion);
	}

	@GetMapping({ "/profesiones/{id}" })
	public ResponseEntity<Profesion> getProfesionById(@PathVariable Integer id) {
		Profesion profesion = (Profesion) this.profesionRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(profesion); 
	}

	@DeleteMapping({ "/profesiones/{id}" })
	public ResponseEntity<?> deleteProfesion(@PathVariable Integer id) {
		Profesion profesion = this.profesionRepository.findById(id).orElse(null);

		if (profesion == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(profesion.getId());
		log.setDescripcion(profesion.toString());
		log.setTabla(Profesion.class.toString());
		this.logService.saveLog(log);

		this.profesionRepository.deleteById(profesion.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
