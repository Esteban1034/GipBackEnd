    package com.backendgip.controller;

    import java.sql.Date;
    import java.util.Calendar;
    import java.util.HashMap;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Map;

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
    import com.backendgip.model.EsfuerzoAgil;
    import com.backendgip.model.EstimacionAgil;
import com.backendgip.model.EstimacionContenido;
import com.backendgip.model.ContenidoEstimacionAgil;
    import com.backendgip.model.EstimacionUfs;
    import com.backendgip.model.Funcion;
    import com.backendgip.model.LogSistema;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.ActividadesComplementariasService;
import com.backendgip.service.EsfuerzoAgilService;
    import com.backendgip.service.EstimacionAgilService;
    import com.backendgip.service.EstimacionesUfsService;
    import com.backendgip.service.LogSistemaService;

    @RestController
    @RequestMapping("/api/estimaciones-agil")
    public class EstimacionAgilController {
        
        @Autowired
        private EstimacionesUfsService estimacionesUfsService;
        @Autowired
        private LogSistemaService logService;
        @Autowired
        private EstimacionesUfsRepository estimacionesUfsRepository;
        @Autowired
        private EstimacionAgilService estimacionAgilService;
        @Autowired
        private EsfuerzoAgilService esfuerzoAgilService;
        @Autowired
        private ActividadesComplementariasService actividadesComplementariasService;

        @GetMapping
        public ResponseEntity<List<EstimacionUfs>> getEstimaciones() {
            List<EstimacionUfs> estimaciones = estimacionesUfsService.getEstimaciones();
            return ResponseEntity.ok(estimaciones);
        }
        
        @PostMapping("/guardar")
        public ResponseEntity<?> saveEstimacionIn(@RequestBody ContenidoEstimacionAgil estimacion) {
            
            System.out.println("eee"+estimacion);

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

            

            estimacion.getEstimacionAgil().forEach(actividad -> {
                actividad.setEstimacion(savEstimacion);
                EstimacionAgil actividadCreada = estimacionAgilService.save(actividad);
                log.setAccion("CREATE");
                log.setTabla(EstimacionAgilController.class.toString());
                log.setIdAccion(actividadCreada.getId());
                log.setDescripcion(actividadCreada.toString());
                this.logService.saveLog(log);
            });

            estimacion.getEsfuerzoAgil().forEach(esfuerzo -> {
                esfuerzo.setEstimacion(savEstimacion);
                EsfuerzoAgil esfuerzoCreada = esfuerzoAgilService.save(esfuerzo);
                log.setAccion("CREATE");
                log.setTabla(EsfuerzoAgil.class.toString());
                log.setIdAccion(esfuerzoCreada.getId());
                log.setDescripcion(esfuerzoCreada.toString());
                this.logService.saveLog(log);
            });

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

            return ResponseEntity.ok(savEstimacion);
        }

        @PutMapping
        public ResponseEntity<?> updateEstimaciones(@RequestBody ContenidoEstimacionAgil estimacion) {

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

            ContenidoEstimacionAgil estimacionesBD = findContenidoEstimacion(estimaciones.getId());
            
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
            
            Iterator<EsfuerzoAgil> iteratorSubFuncion = estimacionesBD.esfuerzoAgil.iterator();
            while (iteratorSubFuncion.hasNext()) {
                EsfuerzoAgil obj1 = iteratorSubFuncion.next();
                boolean found = false;
                for (EsfuerzoAgil obj2 : estimacion.esfuerzoAgil) {
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
                    esfuerzoAgilService.deleteById(obj1.getId());
                }
            }

            Iterator<EstimacionAgil> iteratorEstimacion = estimacionesBD.estimacionAgil.iterator();
            while (iteratorEstimacion.hasNext()) {
                EstimacionAgil obj1 = iteratorEstimacion.next();
                boolean found = false;
                for (EstimacionAgil obj2 : estimacion.estimacionAgil) {
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
                    estimacionAgilService.deleteById(obj1.getId());
                }
            }

            Iterator<EsfuerzoAgil> iteratorEsfuerzoUpdate = estimacion.esfuerzoAgil.iterator();
            while (iteratorEsfuerzoUpdate.hasNext()) {
                EsfuerzoAgil obj1 = iteratorEsfuerzoUpdate.next();
                boolean found = false;
                for (EsfuerzoAgil obj2 : estimacionesBD.esfuerzoAgil) {
                    if (obj1.getId() == obj2.getId()) {
                        EsfuerzoAgil esfuerzoUpdate = esfuerzoAgilService.findById(obj1.getId());
                        log.setAccion("UPDATE");
                        log.setDescripcion(esfuerzoUpdate.toString());
                        log.setIdAccion(esfuerzoUpdate.getId());
                        log.setTabla(esfuerzoUpdate.getClass().toString());
                        this.logService.saveLog(log);
                        esfuerzoUpdate.setEstimacion(updatedestimacion);
                        esfuerzoUpdate.setFase(obj1.getFase());
                        esfuerzoUpdate.setHoras(obj1.getHoras());
                        esfuerzoUpdate.setPorcentaje(obj1.getPorcentaje());
                    
                        EsfuerzoAgil updatedEsfuerzoAgil = this.esfuerzoAgilService.update(esfuerzoUpdate);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    obj1.setEstimacion(updatedestimacion);
                    EsfuerzoAgil esfuerzoCreada = this.esfuerzoAgilService.save(obj1);
                    log.setAccion("CREATE");
                    log.setTabla(EsfuerzoAgil.class.toString());
                    log.setIdAccion(obj1.getId());
                    log.setDescripcion(obj1.toString());
                    this.logService.saveLog(log);
                }
            }

            estimacion.estimacionAgil.forEach(actividad -> {
                if (actividad.getId() != null) {
                    EstimacionAgil actividadUpdate = this.estimacionAgilService.findById(actividad.getId());
                    log.setAccion("UPDATE");
                    log.setDescripcion(actividadUpdate.toString());
                    log.setIdAccion(actividadUpdate.getId());
                    log.setTabla(actividadUpdate.getClass().toString());
                    this.logService.saveLog(log);
                    actividadUpdate.setEstimacion(updatedestimacion);
                    actividadUpdate.setActividad(actividad.getActividad());
                    actividadUpdate.setComplejidad(actividad.getComplejidad());
                    actividadUpdate.setFase(actividad.getFase());
                    EstimacionAgil updatedactividad = this.estimacionAgilService.update(actividad);
                } else {
                    actividad.setEstimacion(updatedestimacion);
                    EstimacionAgil actividadCreada = this.estimacionAgilService.save(actividad);
                    log.setAccion("CREATE");
                    log.setTabla(EstimacionAgil.class.toString());
                    log.setIdAccion(actividadCreada.getId());
                    log.setDescripcion(actividadCreada.toString());
                    this.logService.saveLog(log);

                }
            });
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


        @DeleteMapping("/{id}")
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

            List<EstimacionAgil> estimacionAgil = estimacionAgilService.findByEstimacion(estimacion);
            estimacionAgil.forEach(actividad -> {
                LogSistema log = new LogSistema();
                log.setAccion("DELETE");
                log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
                log.setTabla(ActividadesComplementarias.class.toString());
                log.setIdAccion(actividad.getId());
                log.setDescripcion(actividad.toString());
                logService.saveLog(log);

                estimacionAgilService.deleteById(actividad.getId());
            });

            List<EsfuerzoAgil> esfuerzo = esfuerzoAgilService.findByEstimacion(estimacion);

            esfuerzo.forEach(esfuerzoAgil -> {
                LogSistema log = new LogSistema();
                log.setAccion("DELETE");
                log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
                log.setTabla(EsfuerzoAgil.class.toString());
                log.setIdAccion(esfuerzoAgil.getId());
                log.setDescripcion(esfuerzoAgil.toString());
                logService.saveLog(log);

                this.esfuerzoAgilService.deleteById(esfuerzoAgil.getId());
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

        @GetMapping("/{id}")
        public ResponseEntity<EstimacionUfs> getEstimacionesById(@PathVariable Integer id) {
            EstimacionUfs estimacion = estimacionesUfsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + id));
            return ResponseEntity.ok(estimacion);
        }

        @GetMapping("/contenidoestimacion/{id}")
        public ContenidoEstimacionAgil findContenidoEstimacion(@PathVariable Integer idEstimacion) {
            ContenidoEstimacionAgil estimacionContenido = new ContenidoEstimacionAgil();
            EstimacionUfs estimacion = estimacionesUfsRepository.findById(idEstimacion)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Estimación no encontrada con el id: " + idEstimacion));

            List<EstimacionAgil> estimacionAgil = estimacionAgilService.findByEstimacion(estimacion);
            List<EsfuerzoAgil> esfuerzoAgil = esfuerzoAgilService.findByEstimacion(estimacion);
            List<ActividadesComplementarias> actividad = actividadesComplementariasService.findByEstimacion(estimacion);
            estimacionContenido.setEstimacionAgil(estimacionAgil);
            estimacionContenido.setEstimacion(estimacion);
            estimacionContenido.setEsfuerzoAgil(esfuerzoAgil);
            estimacionContenido.setActividades(actividad);
            return estimacionContenido;
        } 
    }
