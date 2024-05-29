package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.Estimaciones;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstimacionesRepository;
import com.backendgip.service.EstimacionesService;
import com.backendgip.service.LogSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EstimacionesController {

    @Autowired
    private EstimacionesService estimacionesService;

    @Autowired
    private LogSistemaService logService;

    @Autowired
    private EstimacionesRepository estimacionesRepository;

    @GetMapping("/estimaciones")
    public ResponseEntity<List<Estimaciones>> getEstimaciones() {
        List<Estimaciones> estimaciones = estimacionesService.getEstimaciones();
        return ResponseEntity.ok(estimaciones);
    }
    
    @PostMapping("/estimaciones")
    public ResponseEntity<?> createEstimaciones(@RequestBody Estimaciones estimaciones) {
        Estimaciones createdEstimaciones = estimacionesService.saveEstimaciones(estimaciones);

        // Registro de la creación
        LogSistema log = new LogSistema();
        log.setAccion("CREATE");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(Estimaciones.class.toString());
        log.setIdAccion(createdEstimaciones.getId());
        log.setDescripcion(createdEstimaciones.toString());
        logService.saveLog(log);

        return ResponseEntity.ok(createdEstimaciones);
    }

    @DeleteMapping("/estimaciones/{id}")
    public ResponseEntity<?> deleteEstimaciones(@PathVariable Integer id) {
        Estimaciones estimacion = estimacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));

        LogSistema log = new LogSistema();
        log.setAccion("DELETE");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(Estimaciones.class.toString());
        log.setIdAccion(estimacion.getId());
        log.setDescripcion(estimacion.toString());
        logService.saveLog(log);

        estimacionesService.deleteEstimaciones(estimacion);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/estimaciones/{id}")
    public ResponseEntity<Estimaciones> getEstimacionesById(@PathVariable Integer id) {
        Estimaciones estimacion = estimacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));
        return ResponseEntity.ok(estimacion);
    }

    @PutMapping("/estimaciones/{id}")
    public ResponseEntity<Estimaciones> updateEstimaciones(@PathVariable Integer id, @RequestBody Estimaciones estimacionesDetails) {
        Estimaciones estimaciones = this.estimacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no existe con id: " + id));
        
        LogSistema log = new LogSistema();
        log.setAccion("PUT");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(Estimaciones.class.toString());
        log.setIdAccion(estimaciones.getId());
        log.setDescripcion(estimaciones.toString());
        logService.saveLog(log);
        
        estimaciones.setProyecto(estimacionesDetails.getProyecto());
        estimaciones.setCliente(estimacionesDetails.getCliente());
        estimaciones.setEstadoPropuesta(estimacionesDetails.getEstadoPropuesta());
        
        estimacionesService.saveEstimaciones(estimaciones);
        
        return ResponseEntity.ok(estimaciones);
    }

}

