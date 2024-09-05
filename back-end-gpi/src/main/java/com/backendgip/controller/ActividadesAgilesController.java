package com.backendgip.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.ActividadesAgiles;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.Subfuncion;
import com.backendgip.repository.ActividadesAgilesRepository;
import com.backendgip.service.ActividadesAgilesService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.service.SubFuncionService;

@RestController
@Transactional
@RequestMapping({ "/api/actividadesagiles" })
public class ActividadesAgilesController {

    
    @Autowired
    private ActividadesAgilesService actividadesAgilesService;
    @Autowired
    private ActividadesAgilesRepository actividadesAgilesRepository;
    @Autowired
	private LogSistemaService logService;


    @GetMapping
    public List<ActividadesAgiles> getActividades() {
        return actividadesAgilesService.getActividades();
    }

    @PostMapping
    public ResponseEntity<?> guardarActividad(@RequestBody ActividadesAgiles actividad) {
            if(actividad.getNombre() != null && actividad.getSubMenuFasesAgiles() != null){
                ActividadesAgiles createActividad = this.actividadesAgilesService.saveActividad(actividad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(ActividadesAgiles.class.toString());
			log.setIdAccion(createActividad.getId());
			log.setDescripcion(createActividad.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createActividad);
            }else{
                return ResponseEntity.badRequest().body("No se puede crear la actividad, hay valores vacios");
            }
    }

    @PutMapping
    public ResponseEntity<?> editarUnidad(@RequestBody ActividadesAgiles actividad) {
        if (actividad.getNombre() != null && actividad.getSubMenuFasesAgiles() != null) {
            ActividadesAgiles editActividad = this.actividadesAgilesRepository.findById(actividad.getId()).orElseThrow(null);
            if (editActividad != null) {
                LogSistema log = new LogSistema();
                log.setAccion("UPDATE");
                log.setFechaHora(new Date());
                log.setTabla(ActividadesAgiles.class.toString());
                log.setIdAccion(editActividad.getId());
                log.setDescripcion(editActividad.toString());
                this.logService.saveLog(log);
                editActividad.setNombre(actividad.getNombre());
                editActividad.setSubMenuFasesAgiles(actividad.getSubMenuFasesAgiles());
                ActividadesAgiles editado = this.actividadesAgilesService.saveActividad(editActividad);
                return ResponseEntity.ok(editado); 
            }else{
                return ResponseEntity.badRequest().body("No se actualizo correctamente, no se encontro la actividad");
            }
        }else{
            return ResponseEntity.badRequest().body("No se actualizo correctamente, hay campos vacios");
        }
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUnidad(@PathVariable Integer id) {
        ActividadesAgiles actividad = actividadesAgilesRepository.findById(id).orElseThrow(null);
        if(actividad != null){
            this.actividadesAgilesService.deleteActividad(actividad);
            Map<String, Boolean> response = new HashMap();
		    response.put("deleted", Boolean.TRUE);
		    return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body("No se puede eliminar la actividad, no se encontro la actividad");
        }
    }
}
