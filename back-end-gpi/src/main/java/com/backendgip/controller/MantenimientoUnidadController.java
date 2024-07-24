package com.backendgip.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.Esfuerzo;
import com.backendgip.model.Funcion;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.Novedad;
import com.backendgip.model.Subfuncion;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.service.SubFuncionService;
import com.backendgip.service.EsfuerzoService;
import com.backendgip.service.FuncionService;
import com.backendgip.service.LogSistemaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class MantenimientoUnidadController {

    @Autowired
    private MantenimientoUnidadService mantenimientoUnidadService;
    @Autowired
    private SubFuncionService subFuncionService;
    @Autowired
	private LogSistemaService logService;


    @GetMapping({ "/mantenimiento-unidad" })
    public List<MantenimientoUnidad> getMantenimientos() {
        return mantenimientoUnidadService.getMantenimientos();
    }

    @PostMapping("/crear-unidad")
    public ResponseEntity<?> guardarUnidad(@RequestBody MantenimientoUnidad mantenimientoUnidad) {
        if(this.mantenimientoUnidadService.validarNombre(mantenimientoUnidad.getNombre())){
            return ResponseEntity.badRequest().body("No se puede crear la unidad, el nombre ya existe");
        }else{
            if(mantenimientoUnidad.getNombre()!= null && mantenimientoUnidad.getPeso() != null){
                MantenimientoUnidad createUnidad = this.mantenimientoUnidadService.saveMantenimientoUnd(mantenimientoUnidad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(MantenimientoUnidad.class.toString());
			log.setIdAccion(createUnidad.getId());
			log.setDescripcion(createUnidad.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createUnidad);
            }else{
                return ResponseEntity.badRequest().body("No se puede crear la unidad, hay valores vacios");
            }
           
        }
    }

    @PostMapping("/editar-unidad")
    public ResponseEntity<?> editarUnidad(@RequestBody MantenimientoUnidad mantenimiento) {
        if (mantenimiento.getNombre() != null && mantenimiento.getPeso() != null) {
            MantenimientoUnidad unidad = this.mantenimientoUnidadService.getMantenimientoUndById(mantenimiento.getId());
            LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setFechaHora(new Date());
			log.setTabla(MantenimientoUnidad.class.toString());
			log.setIdAccion(unidad.getId());
			log.setDescripcion(unidad.toString());
			this.logService.saveLog(log);
        unidad.setNombre(mantenimiento.getNombre());
        unidad.setPeso(mantenimiento.getPeso());
        MantenimientoUnidad editUnidad = this.mantenimientoUnidadService.saveMantenimientoUnd(unidad);
        return ResponseEntity.ok(editUnidad);
        }else{
            return ResponseEntity.badRequest().body("No se actualizo correctamente, hay campos vacios");
        }
        
    }
    
    @PostMapping("/eliminar-unidad/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUnidad(@PathVariable Integer id) {
        MantenimientoUnidad unidad = mantenimientoUnidadService.getMantenimientoUndById(id);
        this.mantenimientoUnidadService.deleteById(id);
        Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
    }
    

}
