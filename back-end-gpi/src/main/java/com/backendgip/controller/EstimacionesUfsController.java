package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.UnidadFuncionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EstimacionesUfsController {

    @Autowired
    private EstimacionesUfsService estimacionesUfsService;
    @Autowired
    private LogSistemaService logService;
    @Autowired
    private EstimacionesUfsRepository estimacionesUfsRepository;

    @GetMapping("/estimaciones")
    public ResponseEntity<List<EstimacionUfs>> getEstimaciones() {
        List<EstimacionUfs> estimaciones = estimacionesUfsService.getEstimaciones();
        return ResponseEntity.ok(estimaciones);
    }

    @DeleteMapping("/estimaciones/{id}")
    public ResponseEntity<?> deleteEstimaciones(@PathVariable Integer id) {
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));

        LogSistema log = new LogSistema();
        log.setAccion("DELETE");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(EstimacionUfs.class.toString());
        log.setIdAccion(estimacion.getId());
        log.setDescripcion(estimacion.toString());
        logService.saveLog(log);

        estimacionesUfsService.deleteEstimaciones(estimacion);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/estimaciones/{id}")
    public ResponseEntity<EstimacionUfs> getEstimacionesById(@PathVariable Integer id) {
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));
        return ResponseEntity.ok(estimacion);
    }
    
}

