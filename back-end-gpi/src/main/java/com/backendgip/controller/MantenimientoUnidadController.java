package com.backendgip.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.Esfuerzo;
import com.backendgip.model.Funcion;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.Novedad;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
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
    private MantenimientoPesoHoraService mantenimientoPesoHoraService;
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
           MantenimientoUnidad createUnidad = this.mantenimientoUnidadService.saveMantenimientoUnd(mantenimientoUnidad);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(MantenimientoUnidad.class.toString());
			log.setIdAccion(createUnidad.getId());
			log.setDescripcion(createUnidad.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createUnidad);
        }
    }

    @PostMapping("/editar-unidad")
    public ResponseEntity<MantenimientoUnidad> editarUnidad(@RequestBody MantenimientoUnidad mantenimiento) {
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
    }
    
    @PostMapping("/eliminar-unidad")
    public ResponseEntity<?> deleteUnidad(@RequestBody MantenimientoUnidad mantenimiento) {
        MantenimientoUnidad unidad = this.mantenimientoUnidadService.getMantenimientoUndById(mantenimiento.getId());
        LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setTabla(MantenimientoUnidad.class.toString());
			log.setIdAccion(unidad.getId());
			log.setDescripcion(unidad.toString());
			this.logService.saveLog(log);
        this.mantenimientoUnidadService.deleteById(unidad.getId());
        return ResponseEntity.ok("Eliminado correctamente");
    }
    

}
