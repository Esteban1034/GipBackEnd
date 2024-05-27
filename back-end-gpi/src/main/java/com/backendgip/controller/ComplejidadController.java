//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceAlreadyExistsException;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.Complejidad;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstadoCliente;
import com.backendgip.model.LogSistema;
import com.backendgip.model.SectorCliente;
import com.backendgip.repository.ComplejidadRepository;
import com.backendgip.service.ComplejidadService;
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
public class ComplejidadController {
	@Autowired
	private ComplejidadService complejidadService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private ComplejidadRepository complejidadRepository;

	public ComplejidadController() {
	}

	@GetMapping({ "/complejidad" })
	public List<Complejidad> getComplejidads() {
		return this.complejidadService.getComplejidad();
	}

	@PostMapping({ "/complejidad" })
	public ResponseEntity<?> saveComplejidad(@RequestBody Complejidad complejidad) {
		if (this.complejidadRepository.existsByNombre(complejidad.getNombre())) {
			return ResponseEntity.badRequest().body("Complejidad Existe");
		} else {
			Complejidad createdComplejidad = this.complejidadService.saveComplejidad(complejidad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setTabla(Complejidad.class.toString());
			log.setIdAccion(createdComplejidad.getId());
			log.setDescripcion(createdComplejidad.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdComplejidad);
		}
	}

	@PutMapping({ "/complejidad/{id}" })
	public ResponseEntity<?> updateComplejidad(@PathVariable Integer id, @RequestBody Complejidad complejidadDetails) {
		Complejidad complejidad = (Complejidad) this.complejidadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Complejidad no existe con id: " + id);
		});
		if (this.complejidadRepository.existsByNombre(complejidadDetails.getNombre())
				&& complejidad.getId() != complejidadDetails.getId()) {
			return ResponseEntity.badRequest().body(new ResourceAlreadyExistsException("Nombre existente"));
		} else {
			LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setDescripcion(complejidad.toString());
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(complejidad.getId());
			log.setTabla(complejidad.getClass().toString());
			this.logService.saveLog(log);
			complejidad.setNombre(complejidadDetails.getNombre());
			complejidad.setPeso(complejidadDetails.getPeso());
			Complejidad updatedComplejidad = this.complejidadService.saveComplejidad(complejidad);
			return ResponseEntity.ok(updatedComplejidad);
		}
	}

	@GetMapping({ "/complejidad/{id}" })
	public ResponseEntity<Complejidad> getComplejidadById(@PathVariable Integer id) {
		Complejidad complejidad = (Complejidad) this.complejidadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(complejidad);
	}

	@DeleteMapping({ "/complejidad/{id}" })
	public ResponseEntity<?> deleteComplejidad(@PathVariable Integer id) {
		Complejidad complejidad = (Complejidad) this.complejidadRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Complejidad no exite con el id:" + id);
		});
		/* 
		if (this.proyectoRepository.existsByCliente(cliente)) {
			return ResponseEntity.badRequest()
					.body("Cliente no se puede eliminar, Ya se encuentra asignado a uno o mas proyectos.");
		} else {*/
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(complejidad.getId());
			log.setDescripcion(complejidad.toString());
			log.setTabla(Complejidad.class.toString());
			this.logService.saveLog(log);
			this.complejidadService.deleteComplejidad(complejidad);
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		//}
	}
}
