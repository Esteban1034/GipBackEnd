package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.service.ClienteService;
import com.backendgip.service.EstimacionUfsService;
import com.backendgip.service.ProyectoService;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Ufs;
import com.backendgip.service.LogSistemaService;
import com.backendgip.repository.EstimacionUfsRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class EstimacionUfsController {
    @Autowired
    private EstimacionUfsService estimacionUfsService;
    @Autowired
	private LogSistemaService logService;
    @Autowired
    private EstimacionUfsRepository estimacionUfsRepository;

    public EstimacionUfsController() {
	}

    @GetMapping("/estimacionufs")
    public List<EstimacionUfs> getEstimacioneUfs() {
        // Obtener la lista de todas los datos de las estimaciones
        return this.estimacionUfsService.getEstimacionUfs();
    }

    @PostMapping("/estimacionUfs")
    public ResponseEntity<?> saveEstimacionUfs(@RequestBody EstimacionUfs estimacionUfs) {
			EstimacionUfs createdEstimacionUfs = this.estimacionUfsService.saveEstimacionUfs(estimacionUfs);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setTabla(EstimacionUfs.class.toString());
			log.setIdAccion(createdEstimacionUfs.getId());
			log.setDescripcion(createdEstimacionUfs.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdEstimacionUfs);
    }

    @PutMapping("/estimacionUfs/{id}")
    public ResponseEntity<?> updateEstimacionUfs(@PathVariable Integer id, @RequestBody EstimacionUfs estimacionUfsDetails) {
     EstimacionUfs estimacionUfs = (EstimacionUfs) this.estimacionUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estimacion no existe con id: " + id);
		});
		LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
		log.setDescripcion(estimacionUfs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(estimacionUfs.getId());
		log.setTabla(estimacionUfs.getClass().toString());
		this.logService.saveLog(log);
		estimacionUfs.setId(estimacionUfs.getId());
		EstimacionUfs updatedEstimacionUfs = this.estimacionUfsService.saveEstimacionUfs(estimacionUfs);
		return ResponseEntity.ok(updatedEstimacionUfs);
    }

    @GetMapping({ "/estimacionUfs/{id}" })
	public ResponseEntity<EstimacionUfs> getEstimacionUfsById(@PathVariable Integer id) {
		EstimacionUfs estimacionUfs = (EstimacionUfs) this.estimacionUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(estimacionUfs);
	}

    @DeleteMapping({ "/estimacionUfs/{id}" })
	public ResponseEntity<?> deleteEstimacionUfs(@PathVariable Integer id) {
		EstimacionUfs estimacionUfs = (EstimacionUfs) this.estimacionUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estimacion no existe con el id:" + id);
		});
			LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
			log.setIdAccion(estimacionUfs.getId());
			log.setDescripcion(estimacionUfs.toString());
			log.setTabla(EstimacionUfs.class.toString());
			this.logService.saveLog(log);
			this.estimacionUfsService.deleteEstimacionUfs(estimacionUfs);
			Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
	}

}
