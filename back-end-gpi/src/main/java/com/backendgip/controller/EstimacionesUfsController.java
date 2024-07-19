package com.backendgip.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ActividadesComplementarias;
import com.backendgip.model.Empleado;
import com.backendgip.model.EstimacionContenido;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.ActividadesComplementariasService;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.FuncionService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.SubFuncionService;
import com.backendgip.service.TipoActividadComplementariaService;
import com.backendgip.service.UnidadFuncionalService;

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
    private ActividadesComplementariasService actividadesComplementariasService;
    @Autowired
    private TipoActividadComplementariaService tipoActividadComplementariaService;
    @Autowired
    private UnidadFuncionalService unidadFuncionalService;
    @Autowired
    private FuncionService funcionService;
    @Autowired
    private SubFuncionService subFuncionService;

    @GetMapping("/estimaciones")
    public ResponseEntity<List<EstimacionUfs>> getEstimaciones() {
        List<EstimacionUfs> estimaciones = estimacionesUfsService.getEstimaciones();
        return ResponseEntity.ok(estimaciones);
    }

    @PostMapping("/estimaciones")
    public ResponseEntity<?> saveEstimacionIn(@RequestBody EstimacionContenido estimacion) {

        if(estimacionesUfsRepository.existsByProyectoId(estimacion.getEstimacion().getProyecto().getId()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este proyecto ya tiene una estimación asignada");
        }

        EstimacionUfs savEstimacion = estimacionesUfsService.saveEstimacionIn(estimacion.getEstimacion());
        LogSistema log = new LogSistema();
        log.setAccion("CREATE");
        log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        log.setTabla(Empleado.class.toString());
        log.setIdAccion(savEstimacion.getId());
        log.setDescripcion(savEstimacion.toString());
        this.logService.saveLog(log);

        estimacion.getActividades().forEach(actividad -> {
            actividad.setEstimacion(savEstimacion);
            ActividadesComplementarias actividadCreada = this.actividadesComplementariasService
                    .saveActividad(actividad);
            log.setAccion("CREATE");
            log.setTabla(ActividadesComplementarias.class.toString());
            log.setIdAccion(actividadCreada.getId());
            log.setDescripcion(actividadCreada.toString());
            this.logService.saveLog(log);
        });

        estimacion.getUnidadFuncional().forEach(unidad -> {
            unidad.setEstimacion_ufs(savEstimacion);
            UnidadFuncional unidadCreada = this.unidadFuncionalService.saveUfs(unidad);
            log.setAccion("CREATE");
            log.setTabla(UnidadFuncional.class.toString());
            log.setIdAccion(unidadCreada.getId());
            log.setDescripcion(unidadCreada.toString());
            this.logService.saveLog(log);

            List<Funcion> funcionesDeUnidad = estimacion.getFuncion().stream()
                    .filter(funcion -> funcion.getUnidadFuncional().getId().equals(unidad.getId()))
                    .collect(Collectors.toList());

            funcionesDeUnidad.forEach(funcion -> {
                Integer temporalFuncion = funcion.getId();
                funcion.setId(null);
                funcion.setUnidadFuncional(unidadCreada);
                Funcion funcionCreada = this.funcionService.createFuncion(funcion);
                log.setAccion("CREATE");
                log.setTabla(Funcion.class.toString());
                log.setIdAccion(funcionCreada.getId());
                log.setDescripcion(funcionCreada.toString());
                this.logService.saveLog(log);

                List<Subfuncion> subfuncionesDeFuncion = estimacion.getSubFuncion().stream()
                        .filter(subfuncion -> subfuncion.getFuncion().getId().equals(temporalFuncion))
                        .collect(Collectors.toList());

                subfuncionesDeFuncion.forEach(subFuncion -> {
                    subFuncion.setFuncion(funcionCreada);
                    Subfuncion subfuncionCreada = this.subFuncionService.createSubfuncion(subFuncion);
                    log.setAccion("CREATE");
                    log.setTabla(Subfuncion.class.toString());
                    log.setIdAccion(subfuncionCreada.getId());
                    log.setDescripcion(subfuncionCreada.toString());
                    this.logService.saveLog(log);
                });
            });
        });

        return ResponseEntity.ok(savEstimacion);
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
