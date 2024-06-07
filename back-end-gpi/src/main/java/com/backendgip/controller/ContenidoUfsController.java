//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.service.ContenidoUfsService;
import com.backendgip.service.LogSistemaService;

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

@RestController
@Transactional
@RequestMapping({ "/api" })
public class ContenidoUfsController {
	@Autowired
	private ContenidoUfsService contenidoUfsService;
	@Autowired
	private ContenidoUfsRepository contenidoUfsRepository;
	@Autowired
	private LogSistemaService logService;

	public ContenidoUfsController() {
	}

	@GetMapping({ "/contenido-ufs" })
	public List<ContenidoUfs> getContenidoUfs() {
		return this.contenidoUfsService.getContenidoUfs();
	}

	@PostMapping({ "/contenido-ufs" })
	public ResponseEntity<?> saveContenidoUfs(@RequestBody ContenidoUfs contenidoUfs) {
		LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
		ContenidoUfs createdContenidoUfs = this.contenidoUfsService.saveContenidoUfs(contenidoUfs);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setTabla(ContenidoUfs.class.toString());
		log.setIdAccion(createdContenidoUfs.getId());
		log.setDescripcion(createdContenidoUfs.toString());
		this.logService.saveLog(log);
		return ResponseEntity.ok(createdContenidoUfs);
	}

	@GetMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<ContenidoUfs> getContenidoUfsById(@PathVariable Integer id) {
		ContenidoUfs contenidoufs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID: " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(contenidoufs);
	}

	@DeleteMapping({ "/contenidoufs/{id}" })
	public ResponseEntity<?> deleteContenidoUfs(@PathVariable Integer id) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Cliente no exite con el id:" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(contenidoUfs.getId());
		log.setDescripcion(contenidoUfs.toString());
		log.setTabla(ContenidoUfs.class.toString());
		this.logService.saveLog(log);
		this.contenidoUfsService.deleteContenidoUfs(contenidoUfs);
		Map<String, Boolean> response = new HashMap();
		response.put("Borrado Correctamente", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PutMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<?> updateContenidoUfs(@PathVariable Integer id,
			@RequestBody ContenidoUfs contenidoufsDetails) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No hay contenido para este Ufs con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(contenidoUfs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(contenidoUfs.getId());
		log.setTabla(contenidoUfs.getClass().toString());
		this.logService.saveLog(log);
		contenidoUfs.setMantenimientoUnidad(contenidoufsDetails.getMantenimientoUnidad());
		contenidoUfs.setNombreCaso(contenidoufsDetails.getNombreCaso());
		contenidoUfs.setPorcentajeConstruccion(contenidoufsDetails.getPorcentajeConstruccion());
		contenidoUfs.setPorcentajeDiseno(contenidoufsDetails.getPorcentajeDiseno());
		contenidoUfs.setPorcentajePruebas(contenidoufsDetails.getPorcentajePruebas());
		contenidoUfs.setSubfuncion(contenidoufsDetails.getSubfuncion());
		ContenidoUfs updatedContenidoUfs = this.contenidoUfsService.saveContenidoUfs(contenidoUfs);
		return ResponseEntity.ok(updatedContenidoUfs);
	}
}
