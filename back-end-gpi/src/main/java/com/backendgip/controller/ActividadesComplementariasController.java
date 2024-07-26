package com.backendgip.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ActividadesComplementarias;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.ActividadesComplementariasService;
import com.backendgip.service.LogSistemaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Transactional
@RequestMapping({ "/api/actividades-complementarias" })
public class ActividadesComplementariasController {
    
    @Autowired
    private ActividadesComplementariasService actividadesComplementariasService;
    @Autowired
	private LogSistemaService logService;
    @Autowired
    private EstimacionesUfsRepository estimacionesUfsRepository;

    @GetMapping("/obtener-actividades")
    public List<ActividadesComplementarias> obtenerActividades() {
        return this.actividadesComplementariasService.getActividades();
    }

    @GetMapping("/obtener-actividades/estimacion/{id}")
    public ResponseEntity<?> obtenerActividadesEstimacion(@PathVariable Integer id){
        EstimacionUfs estimacion = this.estimacionesUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Estimacion no encontrada con id:" + id);
		});
        List<ActividadesComplementarias> actividades = this.actividadesComplementariasService.getActividadesEstimacion(estimacion);
        if(actividades != null){
            return ResponseEntity.ok(actividades);
        }else{
            return ResponseEntity.badRequest().body("No se encontro actividades con esta estimacion"+ estimacion.getProyecto().getNombre());
        }
    }
    @PostMapping("/crear-actividades")
    public ResponseEntity<?> guardaractividades(@RequestBody List<ActividadesComplementarias> actividades) {
        List<ActividadesComplementarias> listaActividades = new ArrayList<>();
        for (ActividadesComplementarias actividad : actividades) {
            ActividadesComplementarias actividadCreada = this.actividadesComplementariasService.saveActividad(actividad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(ActividadesComplementarias.class.toString());
			log.setIdAccion(actividadCreada.getId());
			log.setDescripcion(actividadCreada.toString());
			this.logService.saveLog(log);
            listaActividades.add(actividadCreada);
        }
			return ResponseEntity.ok(listaActividades);
        
    }

    @PostMapping("/editar-actividades")
    public ResponseEntity<List<ActividadesComplementarias>> editarUnidad(@RequestBody List<ActividadesComplementarias> actividades) {
        List<ActividadesComplementarias> listaActividades = new ArrayList<>();
        for (ActividadesComplementarias actividad : actividades) {
            ActividadesComplementarias actividadEditar = this.actividadesComplementariasService.getById(actividad.getId());
            LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setFechaHora(new Date());
			log.setTabla(ActividadesComplementarias.class.toString());
			log.setIdAccion(actividadEditar.getId());
			log.setDescripcion(actividadEditar.toString());
			this.logService.saveLog(log);
            actividadEditar.setEstimacion(actividad.getEstimacion());
            actividadEditar.setHoras(actividad.getHoras());
            actividadEditar.setNombre(actividad.getNombre());
            actividadEditar.setPorcentaje(actividad.getPorcentaje());
            actividadEditar.setTipoActividad(actividad.getTipoActividad());
            listaActividades.add(this.actividadesComplementariasService.saveActividad(actividadEditar));
        }
        return ResponseEntity.ok(listaActividades);
    }
    
    @PostMapping("/eliminar-actividades/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUnidad(@PathVariable Integer id) {
        this.actividadesComplementariasService.deleteActividad(id);
        Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
    }

}
