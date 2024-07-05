package com.backendgip.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.LogSistema;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.repository.UnidadFuncionalRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.UnidadFuncionalService;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class UnidadFuncionalController {

	@Autowired
	private UnidadFuncionalService ufsService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private UnidadFuncionalRepository ufsRepository;
	@Autowired
	private EstimacionesUfsRepository estimacionesUfsRepository;

	public UnidadFuncionalController() {
	}

	@GetMapping({ "/unidad-funcional" })
	public List<UnidadFuncional> getUfs() {
		return this.ufsService.getUfs();
	}

	@GetMapping({ "/unidad-funcional/estimacionufs/{idEstimacion}" })
	public List<UnidadFuncional> findByEstimacionUfs(@PathVariable Integer idEstimacion) {
		EstimacionUfs estimacion = estimacionesUfsRepository.findById(idEstimacion)
				.orElseThrow(
						() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + idEstimacion));

		return ufsService.findByEstimacionUfs(estimacion);
	}

	@PostMapping({ "/unidad-funcional" })
	public ResponseEntity<?> saveUfs(@RequestBody UnidadFuncional ufs) {
		if (this.ufsRepository.existsByNombre(ufs.getNombre()) /* || this.ufsRepository.existsById(ufs.getId()) */) {
			return ResponseEntity.badRequest().body("Esta Unidad funcional ya existe");
		} else {
			LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
			UnidadFuncional createdUfs = this.ufsService.saveUfs(ufs);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(UnidadFuncional.class.toString());
			log.setIdAccion(createdUfs.getId());
			log.setDescripcion(createdUfs.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdUfs);
		}
	}

	@PutMapping({ "/unidad-funcional/{id}" })
	public ResponseEntity<?> updateUfs(@PathVariable Integer id, @RequestBody UnidadFuncional UfsDetails) {
		UnidadFuncional ufs = (UnidadFuncional) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la unidad funcional con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(ufs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(ufs.getId());
		log.setTabla(ufs.getClass().toString());
		this.logService.saveLog(log);
		ufs.setNombre(UfsDetails.getNombre());
		UnidadFuncional updatedUfs = this.ufsService.saveUfs(ufs);
		return ResponseEntity.ok(updatedUfs);
	}

	@GetMapping({ "/unidad-funcional/{id}" })
	public ResponseEntity<UnidadFuncional> getUfsById(@PathVariable Integer id) {
		UnidadFuncional ufs = (UnidadFuncional) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(ufs);
	}

	@DeleteMapping({ "/unidad-funcional/{id}" })
	public ResponseEntity<?> deleteContenidoUfs(@PathVariable Integer id) {
		UnidadFuncional ufs = (UnidadFuncional) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("La función no exite con el id:" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(ufs.getId());
		log.setDescripcion(ufs.toString());
		log.setTabla(UnidadFuncional.class.toString());
		this.logService.saveLog(log);
		this.ufsRepository.deleteById(ufs.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
