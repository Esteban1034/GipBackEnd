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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.service.ContenidoUfsService;
import com.backendgip.service.EsfuerzoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping({ "/api" })
public class ContenidoUfsController {

	@Autowired
	ContenidoUfsRepository contenidoUfsRepository;
	@Autowired
	ContenidoUfsService contenidoUfsService;
	@Autowired
	MantenimientoUnidadService mantenimientoUnidadService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private EsfuerzoService esfuerzoService;
	@Autowired
	MantenimientoPesoHoraService pesoHoraService;

	@GetMapping({ "/contenido-ufs" })
	public List<ContenidoUfs> getContenidoUfs() {
		return contenidoUfsService.getContenidoUfs();
	}

	@GetMapping({ "/obtenerHoras/{tipo}" })
	public MantenimientoPesoHora obtenerHoras(@RequestBody MantenimientoUnidad tipoMantenimiento) {
		System.out.println(tipoMantenimiento);
		System.out.println( String.valueOf(tipoMantenimiento.getPeso()));
		return this.pesoHoraService.buscarPeso(String.valueOf(tipoMantenimiento.getPeso()));
	}

	@PostMapping({ "/contenido-ufs" })
	public ResponseEntity<?> saveContenidoUfs(@RequestBody ContenidoUfs contenidoUfs) {
		if (this.contenidoUfsRepository.existsById(contenidoUfs.getId())) {
			return ResponseEntity.badRequest().body("No existe ufs con este id ");
		} else {
			LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
			ContenidoUfs createdContenidoUfs = this.contenidoUfsService.saveContenidoUfs(contenidoUfs);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(ContenidoUfs.class.toString());
			log.setIdAccion(createdContenidoUfs.getId());
			log.setDescripcion(createdContenidoUfs.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdContenidoUfs);
		}
	}

	@PutMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<?> updateContenidoUfs(@PathVariable Integer id,
			@RequestBody ContenidoUfs contenidoUfsDetails) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("La unidad Funcional no existe con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(contenidoUfs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(contenidoUfs.getId());
		log.setTabla(contenidoUfs.getClass().toString());
		this.logService.saveLog(log);
		contenidoUfs.setFuncion(contenidoUfsDetails.getFuncion());
		contenidoUfs.setNombreCaso(contenidoUfsDetails.getNombreCaso());
		contenidoUfs.setPorcentajeConstruccion(contenidoUfsDetails.getPorcentajeConstruccion());
		contenidoUfs.setPorcentajeDiseno(contenidoUfsDetails.getPorcentajeDiseno());
		contenidoUfs.setPorcentajePruebas(contenidoUfsDetails.getPorcentajePruebas());
		contenidoUfs.setMantenimientoUnidad(contenidoUfsDetails.getMantenimientoUnidad());
		ContenidoUfs updatedContenidoUfs = this.contenidoUfsService.saveContenidoUfs(contenidoUfs);
		return ResponseEntity.ok(updatedContenidoUfs);
	}

	@DeleteMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<?> deleteContenidoUfs(@PathVariable Integer id) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("La función no exite con el id:" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(contenidoUfs.getId());
		log.setDescripcion(contenidoUfs.toString());
		log.setTabla(ContenidoUfs.class.toString());
		this.logService.saveLog(log);
		this.contenidoUfsRepository.deleteById(contenidoUfs.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<ContenidoUfs> getContenidoUfsById(@PathVariable Integer id) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("el ID " + id + " no existe");
		});
		return ResponseEntity.ok(contenidoUfs);
	}

}