package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ComponenteDesarrollo;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.EstimacionUfsDTO;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Proyecto;
import com.backendgip.model.Ufs;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.ContenidoUfsService;
import com.backendgip.service.EmpleadoService;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.ProyectoService;
import com.backendgip.service.UfsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private ContenidoUfsRepository contenidoUfsRepository;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private UfsService ufsService;
    @Autowired
    private ContenidoUfsService contenidoUfsService;

    @GetMapping("/estimaciones")
    public ResponseEntity<List<EstimacionUfs>> getEstimaciones() {
        List<EstimacionUfs> estimaciones = estimacionesUfsService.getEstimaciones();
        return ResponseEntity.ok(estimaciones);
    }
    @PostMapping("/estimaciones")
    public ResponseEntity<?> saveEstimaciones(@RequestBody EstimacionUfsDTO estimacionesDto) {
        Integer idProyecto = estimacionesDto.getEstimacionUfs().getProyecto().getId();
        boolean exists = estimacionesUfsService.existsByProyectoId(idProyecto);
        if(exists){
            return ResponseEntity.badRequest().body("Ya esxiste una estimación para este proyecto");
        }
        Integer UfId = estimacionesDto.getUfId()!= null? estimacionesDto.getUfId() : 1;
        Ufs ufs = ufsService.getUfsById(UfId);
        if(ufs == null){
            return ResponseEntity.badRequest().body("No es posible crear la Unidad fincional" + ufs);
        }
        ContenidoUfs contenidoUfs = new ContenidoUfs();
        contenidoUfs.setUfs(ufs);

        ContenidoUfs createdContenidoUfs = contenidoUfsService.saveContenidoUfs(contenidoUfs);
        if(createdContenidoUfs == null){
            return ResponseEntity.badRequest().body("No se pudo asociar el contenido a la Unidad funcional"+ ufs);
        }
        EstimacionUfs estimaciones = estimacionesDto.getEstimacionUfs();
        EstimacionUfs createdEstimaciones = estimacionesUfsService.saveEstimacionIn(estimaciones);
        contenidoUfs.setEstimacionUfs(estimaciones);
        if(createdEstimaciones == null){
            return ResponseEntity.badRequest().body("No se pudo guardar la estimación");
        }else {        
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
                List<ContenidoUfs> contenidos = contenidoUfsRepository.findByEstimacionUfs(estimacion);
                if(!contenidos.isEmpty()){
                    contenidoUfsRepository.deleteAll(contenidos);
                }
                estimacionesUfsService.deleteEstimaciones(estimacion);
        LogSistema log = new LogSistema();
        log.setAccion("DELETE");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(EstimacionUfs.class.toString());
        log.setIdAccion(estimacion.getId());
        log.setDescripcion(estimacion.toString());
        logService.saveLog(log);


        return ResponseEntity.ok().build();
    }

    @GetMapping("/estimaciones/{id}")
    public ResponseEntity<EstimacionUfs> getEstimacionesById(@PathVariable Integer id) {
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));
        return ResponseEntity.ok(estimacion);
    }
    
}

