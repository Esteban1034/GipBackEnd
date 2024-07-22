package com.backendgip.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.backendgip.model.EstimacionMantenimiento;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.ActividadesComplementariasService;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.FuncionService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
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

        if (estimacionesUfsRepository.existsByProyectoId(estimacion.getEstimacion().getProyecto().getId())) {
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

        List<ActividadesComplementarias> actividades = actividadesComplementariasService.findByEstimacion(estimacion);
        actividades.forEach(actividad -> {
            LogSistema log = new LogSistema();
            log.setAccion("DELETE");
            log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
            log.setTabla(ActividadesComplementarias.class.toString());
            log.setIdAccion(actividad.getId());
            log.setDescripcion(actividad.toString());
            logService.saveLog(log);

            actividadesComplementariasService.deleteActividad(actividad.getId());
        });

        List<Subfuncion> subfunciones = subFuncionService.findByEstimacionUfs(estimacion);

        subfunciones.forEach(subfuncion -> {
            LogSistema log = new LogSistema();
            log.setAccion("DELETE");
            log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
            log.setTabla(Subfuncion.class.toString());
            log.setIdAccion(subfuncion.getId());
            log.setDescripcion(subfuncion.toString());
            logService.saveLog(log);

            this.subFuncionService.deleteSubfuncion(subfuncion);
        });

        List<Funcion> funciones = funcionService.findByEstimacionUfs(estimacion);
        funciones.forEach(funcion -> {
            LogSistema logFuncion = new LogSistema();
            logFuncion.setAccion("DELETE");
            logFuncion.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
            logFuncion.setTabla(Funcion.class.toString());
            logFuncion.setIdAccion(funcion.getId());
            logFuncion.setDescripcion(funcion.toString());
            logService.saveLog(logFuncion);
            this.funcionService.deleteFuncion(funcion);
        });

        List<UnidadFuncional> unidades = unidadFuncionalService.findByEstimacionUfs(estimacion);
        unidades.forEach(unidad -> {
            LogSistema log = new LogSistema();
            log.setAccion("DELETE");
            log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
            log.setTabla(UnidadFuncional.class.toString());
            log.setIdAccion(unidad.getId());
            log.setDescripcion(unidad.toString());
            logService.saveLog(log);
            this.unidadFuncionalService.deleteUfs(unidad);
        });

        LogSistema logEstimacion = new LogSistema();
        logEstimacion.setAccion("DELETE");
        logEstimacion.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
        logEstimacion.setTabla(EstimacionUfs.class.toString());
        logEstimacion.setIdAccion(estimacion.getId());
        logEstimacion.setDescripcion(estimacion.toString());
        logService.saveLog(logEstimacion);

        estimacionesUfsService.deleteEstimaciones(estimacion);
Map<String, Boolean> response = new HashMap();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
    }

    @GetMapping("/estimaciones/{id}")
    public ResponseEntity<EstimacionUfs> getEstimacionesById(@PathVariable Integer id) {
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));
        return ResponseEntity.ok(estimacion);
    }
    
    @GetMapping("/estimaciones/contenidoestimacion/{idEstimacion}")
    public ResponseEntity<EstimacionContenido> findContenidoEstimacion(@PathVariable Integer idEstimacion) {
        EstimacionContenido estimacionContenido = new EstimacionContenido();
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(idEstimacion)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Estimación no encontrada con el id: " + idEstimacion));

        List<UnidadFuncional> unidadFuncional = this.unidadFuncionalService.findByEstimacionUfs(estimacion);
        List<Subfuncion> subfunciones = this.subFuncionService.findByEstimacionUfs(estimacion);
        List<Funcion> funcion = this.funcionService.findByEstimacionUfs(estimacion);
        List<ActividadesComplementarias> actividad = actividadesComplementariasService.findByEstimacion(estimacion);

        estimacionContenido.setActividades(actividad);
        estimacionContenido.setEstimacion(estimacion);
        estimacionContenido.setFuncion(funcion);
        estimacionContenido.setSubFuncion(subfunciones);
        estimacionContenido.setUnidadFuncional(unidadFuncional);
        
        return ResponseEntity.ok(estimacionContenido);
    }

}
