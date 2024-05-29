//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceAlreadyExistsException;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Ufs;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.UfsRepository;
import com.backendgip.service.UfsService;
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
public class UfsController {
	@Autowired
	private UfsService ufsService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private UfsRepository ufsRepository;

	public UfsController() {
	}

	@GetMapping({ "/Ufs" })
	public List<Ufs> getUfs() {
		return this.ufsService.getUfs();
	}

	@PostMapping({ "/Ufs" })
	public ResponseEntity<?> saveUfs(@RequestBody Ufs ufs) {
		Ufs createdUfs = this.ufsService.saveUfs(ufs);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setTabla(Ufs.class.toString());
		log.setIdAccion(createdUfs.getId());
		log.setDescripcion(createdUfs.toString());
		this.logService.saveLog(log);
		return ResponseEntity.ok(createdUfs);
	}

	@PutMapping({ "/ufs/{id}" })
	public ResponseEntity<?> updateUfs(@PathVariable Integer id, @RequestBody Ufs ufsDetails) {
		Ufs ufs = (Ufs) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Unidad funcional no existe con id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(ufs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(ufs.getId());
		log.setTabla(ufs.getClass().toString());
		this.logService.saveLog(log);
		ufs.setId(ufsDetails.getId());
		Ufs updatedUfs = this.ufsService.saveUfs(ufs);
		return ResponseEntity.ok(updatedUfs);
	}

	@GetMapping({ "/ufs/{id}" })
	public ResponseEntity<Ufs> getUfsById(@PathVariable Integer id) {
		Ufs ufs = (Ufs) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID: " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(ufs);
	}

	@DeleteMapping({ "/ufs/{id}" })
	public ResponseEntity<?> deleteComplejidad(@PathVariable Integer id) {
		Ufs ufs = (Ufs) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Unidad funcional no existe con el id:" + id);
		});
		/*
		 * if (this.proyectoRepository.existsByCliente(cliente)) {
		 * return ResponseEntity.badRequest()
		 * .body("Cliente no se puede eliminar, Ya se encuentra asignado a uno o mas proyectos."
		 * );
		 * } else {
		 */
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(ufs.getId());
		log.setDescripcion(ufs.toString());
		log.setTabla(Ufs.class.toString());
		this.logService.saveLog(log);
		this.ufsService.deleteUfs(ufs);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		// }
	}
}
