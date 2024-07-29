package com.backendgip.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.PutMapping;
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
import com.backendgip.repository.SubfuncionRepository;
import com.backendgip.repository.UnidadFuncionalRepository;
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
    private UnidadFuncionalRepository unidadFuncionalRepository;
    @Autowired
    private SubfuncionRepository subfuncionRepository;
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
            Integer temporalUnidad = unidad.getId();
            unidad.setId(null);
            unidad.setEstimacion_ufs(savEstimacion);
            UnidadFuncional unidadCreada = this.unidadFuncionalService.saveUfs(unidad);
            log.setAccion("CREATE");
            log.setTabla(UnidadFuncional.class.toString());
            log.setIdAccion(unidadCreada.getId());
            log.setDescripcion(unidadCreada.toString());
            this.logService.saveLog(log);

            List<Funcion> funcionesDeUnidad = estimacion.getFuncion().stream()
                    .filter(funcion -> funcion.getUnidadFuncional().getId().equals(temporalUnidad))
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

    @PostMapping("/estimaciones/editar")
    public ResponseEntity<?> updateEstimaciones(@RequestBody EstimacionContenido estimacion) {

        LogSistema log = new LogSistema();

        EstimacionUfs estimaciones = (EstimacionUfs) this.estimacionesUfsRepository
                .findById(estimacion.estimacion.getId())
                .orElseThrow(() -> {
                    return new ResourceNotFoundException(
                            " No existe la estimacion con el id: " + estimacion.estimacion.getId());
                });

        log.setAccion("UPDATE");
        log.setDescripcion(estimaciones.toString());
        log.setIdAccion(estimaciones.getId());
        log.setTabla(estimaciones.getClass().toString());
        this.logService.saveLog(log);
        estimaciones.setEmpleado(estimacion.estimacion.getEmpleado());
        estimaciones.setFechaCreacion(estimacion.estimacion.getFechaCreacion());
        estimaciones.setModelo(estimacion.estimacion.getModelo());
        estimaciones.setProyecto(estimacion.estimacion.getProyecto());
        EstimacionUfs updatedestimacion = this.estimacionesUfsService.saveEstimacionIn(estimaciones);

        EstimacionContenido estimacionesBD = findContenidoEstimacion(estimaciones.getId());

        Iterator<Subfuncion> iteratorSubFuncion = estimacionesBD.subFuncion.iterator();
        while (iteratorSubFuncion.hasNext()) {
            Subfuncion obj1 = iteratorSubFuncion.next();
            boolean found = false;
            for (Subfuncion obj2 : estimacion.subFuncion) {
                if (obj1.getId() == obj2.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                log.setAccion("DELETE");
                log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
                log.setTabla(Funcion.class.toString());
                log.setIdAccion(obj1.getId());
                log.setDescripcion(obj1.toString());
                logService.saveLog(log);
                subFuncionService.deleteSubfuncion(obj1);
            }
        }

        Iterator<Funcion> iteratorFuncion = estimacionesBD.funcion.iterator();
        while (iteratorFuncion.hasNext()) {
            Funcion obj1 = iteratorFuncion.next();
            boolean found = false;
            for (Funcion obj2 : estimacion.funcion) {
                if (obj1.getId() == obj2.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                log.setAccion("DELETE");
                log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
                log.setTabla(Funcion.class.toString());
                log.setIdAccion(obj1.getId());
                log.setDescripcion(obj1.toString());
                logService.saveLog(log);
                funcionService.deleteFuncion(obj1);
            }
        }

        Iterator<ActividadesComplementarias> iteratorActividades = estimacionesBD.actividades.iterator();

        while (iteratorActividades.hasNext()) {
            ActividadesComplementarias obj1 = iteratorActividades.next();
            boolean found = false;
            for (ActividadesComplementarias obj2 : estimacion.actividades) {
                if (obj1.getId() == obj2.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                log.setAccion("DELETE");
                log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
                log.setTabla(UnidadFuncional.class.toString());
                log.setIdAccion(obj1.getId());
                log.setDescripcion(obj1.toString());
                logService.saveLog(log);

                actividadesComplementariasService.deleteActividad(obj1.getId());
            }
        }

        Iterator<UnidadFuncional> iteratorUnidad = estimacionesBD.unidadFuncional.iterator();
        while (iteratorUnidad.hasNext()) {
            UnidadFuncional obj1 = iteratorUnidad.next();
            boolean found = false;
            for (UnidadFuncional obj2 : estimacion.unidadFuncional) {
                if (obj1.getId() == obj2.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                log.setAccion("DELETE");
                log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
                log.setTabla(ActividadesComplementarias.class.toString());
                log.setIdAccion(obj1.getId());
                log.setDescripcion(obj1.toString());
                logService.saveLog(log);
                unidadFuncionalService.deleteUfs(obj1);
            }
        }

        Iterator<UnidadFuncional> iteratorUnidadUpdate = estimacion.unidadFuncional.iterator();
        while (iteratorUnidadUpdate.hasNext()) {
            UnidadFuncional obj1 = iteratorUnidadUpdate.next();
            boolean found = false;
            for (UnidadFuncional obj2 : estimacionesBD.unidadFuncional) {
                if (obj1.getId() == obj2.getId()) {
                    UnidadFuncional unidadUpdate = this.unidadFuncionalRepository.findById(obj1.getId())
                            .orElseThrow(null);
                    log.setAccion("UPDATE");
                    log.setDescripcion(unidadUpdate.toString());
                    log.setIdAccion(unidadUpdate.getId());
                    log.setTabla(unidadUpdate.getClass().toString());
                    this.logService.saveLog(log);
                    unidadUpdate.setEstimacion_ufs(updatedestimacion);
                    unidadUpdate.setNombre(obj1.getNombre());
                    UnidadFuncional updatedUnidad = this.unidadFuncionalService.saveUfs(unidadUpdate);
                    updateFuncion(estimacion, updatedUnidad, updatedUnidad.getId());
                    found = true;
                    break;
                }
            }
            if (!found) {
                obj1.setEstimacion_ufs(updatedestimacion);
                UnidadFuncional unidadCreada = this.unidadFuncionalService.saveUfs(obj1);
                log.setAccion("CREATE");
                log.setTabla(ActividadesComplementarias.class.toString());
                log.setIdAccion(obj1.getId());
                log.setDescripcion(obj1.toString());
                this.logService.saveLog(log);
                updateFuncion(estimacion, unidadCreada, obj1.getId());
            }
        }

        estimacion.actividades.forEach(actividad -> {
            if (actividad.getId() != null) {
                ActividadesComplementarias actividadUpdate = this.actividadesComplementariasService
                        .getById(actividad.getId());
                log.setAccion("UPDATE");
                log.setDescripcion(actividadUpdate.toString());
                log.setIdAccion(actividadUpdate.getId());
                log.setTabla(actividadUpdate.getClass().toString());
                this.logService.saveLog(log);
                actividadUpdate.setEstimacion(updatedestimacion);
                actividadUpdate.setHoras(actividad.getHoras());
                actividadUpdate.setNombre(actividad.getNombre());
                actividadUpdate.setPorcentaje(actividad.getPorcentaje());
                actividadUpdate.setTipoActividad(actividad.getTipoActividad());
                ActividadesComplementarias updatedactividad = this.actividadesComplementariasService
                        .saveActividad(actividadUpdate);
            } else {
                actividad.setEstimacion(updatedestimacion);
                ActividadesComplementarias actividadCreada = this.actividadesComplementariasService
                        .saveActividad(actividad);
                log.setAccion("CREATE");
                log.setTabla(ActividadesComplementarias.class.toString());
                log.setIdAccion(actividadCreada.getId());
                log.setDescripcion(actividadCreada.toString());
                this.logService.saveLog(log);

            }
        });
        return ResponseEntity.ok(updatedestimacion);
    }

    public void updateFuncion(EstimacionContenido estimacion, UnidadFuncional unidad, Integer unidadId) {
        LogSistema log = new LogSistema();
        EstimacionContenido estimacionesBD = findContenidoEstimacion(estimacion.estimacion.getId());
        List<Funcion> funcionesDeUnidad = estimacion.getFuncion().stream()
                .filter(funcion -> funcion.getUnidadFuncional().getId().equals(unidadId))
                .collect(Collectors.toList());

        Iterator<Funcion> iteratorFuncionUpdate = funcionesDeUnidad.iterator();
        while (iteratorFuncionUpdate.hasNext()) {
            Funcion obj1 = iteratorFuncionUpdate.next();
            boolean found = false;
            for (Funcion obj2 : estimacionesBD.funcion) {
                if (obj1.getId() == obj2.getId()) {
                    Funcion funcionUpdate = this.funcionService.getFuncionById(obj1.getId());
                    log.setAccion("UPDATE");
                    log.setDescripcion(funcionUpdate.toString());
                    log.setIdAccion(funcionUpdate.getId());
                    log.setTabla(funcionUpdate.getClass().toString());
                    this.logService.saveLog(log);
                    funcionUpdate.setUnidadFuncional(obj1.getUnidadFuncional());
                    funcionUpdate.setNombre(obj1.getNombre());
                    Funcion funcionEditada = this.funcionService.createFuncion(funcionUpdate);
                    updateSubFuncion(estimacion, obj1.getId(), funcionEditada);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Integer temporalFuncion = obj1.getId();
                obj1.setId(null);
                obj1.setUnidadFuncional(unidad);
                Funcion funcionCreada = this.funcionService.createFuncion(obj1);
                log.setAccion("CREATE");
                log.setTabla(Funcion.class.toString());
                log.setIdAccion(funcionCreada.getId());
                log.setDescripcion(funcionCreada.toString());
                this.logService.saveLog(log);
                updateSubFuncion(estimacion, temporalFuncion, funcionCreada);
            }
        }
    }

    public void updateSubFuncion(EstimacionContenido estimacion, Integer id, Funcion funcion) {
        LogSistema log = new LogSistema();
        EstimacionContenido estimacionesBD = findContenidoEstimacion(estimacion.estimacion.getId());
        List<Subfuncion> subfuncionesDeFuncion = estimacion.getSubFuncion().stream()
                .filter(subfuncion -> subfuncion.getFuncion().getId().equals(id))
                .collect(Collectors.toList());

        Iterator<Subfuncion> iteratorSubFuncionUpdate = subfuncionesDeFuncion.iterator();
        while (iteratorSubFuncionUpdate.hasNext()) {
            Subfuncion obj1 = iteratorSubFuncionUpdate.next();
            boolean found = false;
            for (Subfuncion obj2 : estimacionesBD.subFuncion) {
                if (obj1.getId() == obj2.getId()) {
                    Subfuncion subfuncionUpdate = this.subfuncionRepository.findById(obj1.getId()).orElseThrow(null);
                    log.setAccion("UPDATE");
                    log.setDescripcion(subfuncionUpdate.toString());
                    log.setIdAccion(subfuncionUpdate.getId());
                    log.setTabla(subfuncionUpdate.getClass().toString());
                    this.logService.saveLog(log);
                    subfuncionUpdate.setFuncion(funcion);
                    subfuncionUpdate.setMantenimientoUnidad(obj1.getMantenimientoUnidad());
                    subfuncionUpdate.setNombre(obj1.getNombre());
                    subfuncionUpdate.setNombreCasoDeUso(obj1.getNombreCasoDeUso());
                    subfuncionUpdate.setPorcentajePruebas(obj1.getPorcentajePruebas());
                    subfuncionUpdate.setPorcentajeVConstruccion(obj1.getPorcentajeVConstruccion());
                    subfuncionUpdate.setPorcentajeVDiseno(obj1.getPorcentajeVDiseno());
                    Subfuncion editSubfuncion = this.subFuncionService.createSubfuncion(subfuncionUpdate);
                    found = true;
                    break;
                }
            }
            if (!found) {
                obj1.setFuncion(funcion);
                Subfuncion subfuncionCreada = this.subFuncionService.createSubfuncion(obj1);
                log.setAccion("CREATE");
                log.setTabla(Subfuncion.class.toString());
                log.setIdAccion(subfuncionCreada.getId());
                log.setDescripcion(subfuncionCreada.toString());
                this.logService.saveLog(log);
            }
        }
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
    public EstimacionContenido findContenidoEstimacion(@PathVariable Integer idEstimacion) {
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

        return estimacionContenido;
    }

}
