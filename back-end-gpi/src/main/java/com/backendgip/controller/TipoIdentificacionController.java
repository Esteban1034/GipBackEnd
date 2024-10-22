//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.TipoIdentificacion;
import com.backendgip.repository.TipoIdentificacionRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.TipoIdentificacionService;
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
public class TipoIdentificacionController {
	@Autowired
	private TipoIdentificacionService tipoService;
	@Autowired
	private TipoIdentificacionRepository tipoRepository;
	@Autowired
	private LogSistemaService logService;

	public TipoIdentificacionController() {
	}

	@GetMapping({ "/tipo-identificacion" })
	public List<TipoIdentificacion> getAllTipos() {
		return this.tipoService.getTipos();
	}

	@PostMapping({ "/tipo-identificacion" })
	public ResponseEntity<?> saveTipo(@RequestBody TipoIdentificacion tipo) {
		TipoIdentificacion existingTipo = this.tipoService.findByTipoAndAbreviatura(tipo.getTipo(),
				tipo.getAbreviatura());
		if (existingTipo != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El tipo de documento ya existe: " + tipo.getTipo() + " (" + tipo.getAbreviatura() + ")");
		}

		TipoIdentificacion createdTipoIdentificacion = this.tipoService.saveTipo(tipo);

		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(TipoIdentificacion.class.toString());
		log.setIdAccion(createdTipoIdentificacion.getId());
		log.setDescripcion(createdTipoIdentificacion.toString());
		this.logService.saveLog(log);

		return ResponseEntity.ok(createdTipoIdentificacion);
	}

	@PutMapping({ "/tipo-identificacion/{id}" })
	public ResponseEntity<?> updateTipo(@PathVariable Integer id,
			@RequestBody TipoIdentificacion tipoDetails) {
		TipoIdentificacion tipo = this.tipoRepository.findById(id).orElse(null);

		if (tipo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		TipoIdentificacion existingTipo = this.tipoService.findByTipoAndAbreviatura(tipoDetails.getTipo(),
				tipoDetails.getAbreviatura());
		if (existingTipo != null && !existingTipo.getId().equals(tipo.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El tipo de documento ya existe: " + tipoDetails.getTipo() + " ("
							+ tipoDetails.getAbreviatura() + ")");
		}

		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(tipo.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(tipo.getId());
		log.setTabla(tipo.getClass().toString());
		this.logService.saveLog(log);
		tipo.setTipo(tipoDetails.getTipo());
		tipo.setAbreviatura(tipoDetails.getAbreviatura());
		TipoIdentificacion updatedTipo = this.tipoService.saveTipo(tipo);

		return ResponseEntity.ok(updatedTipo);
	}

	@GetMapping({ "/tipo-identificacion/{id}" })
	public ResponseEntity<TipoIdentificacion> getTipoById(@PathVariable Integer id) {
		TipoIdentificacion tipo = (TipoIdentificacion) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(tipo); 
	}

	@DeleteMapping({ "/tipo-identificacion/{id}" })
	public ResponseEntity<?> deleteTipo(@PathVariable Integer id) {
		TipoIdentificacion tipo = this.tipoRepository.findById(id).orElse(null);

		if (tipo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(tipo.getId());
		log.setDescripcion(tipo.toString());
		log.setTabla(TipoIdentificacion.class.toString());
		this.logService.saveLog(log);

		this.tipoRepository.deleteById(tipo.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
