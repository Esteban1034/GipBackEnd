package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.LogSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
    
    @PostMapping("/estimaciones")
    public ResponseEntity<?> saveEstimaciones(@RequestBody EstimacionUfs estimaciones) {        
        EstimacionUfs createdEstimaciones = estimacionesUfsService.saveEstimaciones(estimaciones);
        if (createdEstimaciones == null) {
            return ResponseEntity.badRequest().body("Nomenclatura existente");
        } else {        
            LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
            estimaciones.setFechaCreacion(fechaCreacion);
            LogSistema log = new LogSistema();
            log.setAccion("CREATE");
            log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
            log.setTabla(EstimacionUfs.class.toString());
            log.setIdAccion(createdEstimaciones.getId());
            log.setDescripcion(createdEstimaciones.toString());
            logService.saveLog(log);
            return ResponseEntity.ok(createdEstimaciones);
        }
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

    @PutMapping("/estimaciones/{id}")
    public ResponseEntity<EstimacionUfs> updateEstimaciones(@PathVariable Integer id, @RequestBody EstimacionUfs estimacionesDetails) {
        EstimacionUfs estimaciones = this.estimacionesUfsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no existe con id: " + id));
        
        LogSistema log = new LogSistema();
        log.setAccion("UPDATE");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(EstimacionUfs.class.toString());
        log.setIdAccion(estimaciones.getId());
        log.setDescripcion(estimaciones.toString());
        logService.saveLog(log);
        
        estimaciones.setProyecto(estimacionesDetails.getProyecto());
        estimaciones.setActividadesComplementarias(estimacionesDetails.getActividadesComplementarias());
        estimaciones.setRecurso(estimacionesDetails.getRecurso());
        estimaciones.setUfs(estimacionesDetails.getUfs());
        estimaciones.setFechaCreacion(estimacionesDetails.getFechaCreacion());
        estimacionesUfsService.saveEstimaciones(estimaciones);
        
        return ResponseEntity.ok(estimaciones);
    }

    
}