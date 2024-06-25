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
public class MantenimientoPesoHoraController {

    @Autowired
    private MantenimientoPesoHoraService mantenimientoPesoHoraService;
    @Autowired
	private LogSistemaService logService;


    @GetMapping({ "/peso-hora" })
    public List<MantenimientoPesoHora> getPesoHora() {
        return this.mantenimientoPesoHoraService.getPesoHora();
    }

    @PostMapping("/crear-peso-hora")
    public ResponseEntity<?> guardarUnidad(@RequestBody MantenimientoPesoHora mantenimientoPeso) {
        if(this.mantenimientoPesoHoraService.validarPeso(mantenimientoPeso.getPeso())){
            return ResponseEntity.badRequest().body("No se puede crear el peso y hora, el peso asiganado ya es existente");
        }else{
           MantenimientoPesoHora createPesoHora = this.mantenimientoPesoHoraService.saveMantenimientoPesoHora(mantenimientoPeso);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(MantenimientoUnidad.class.toString());
			log.setIdAccion(createPesoHora.getId());
			log.setDescripcion(createPesoHora.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createPesoHora);
        }
    }

    @PostMapping("/editar-peso-hora")
    public ResponseEntity<MantenimientoPesoHora> editarUnidad(@RequestBody MantenimientoPesoHora mantenimiento) {
        MantenimientoPesoHora peso = this.mantenimientoPesoHoraService.buscarPeso(mantenimiento.getPeso());
        LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setFechaHora(new Date());
		log.setTabla(MantenimientoPesoHora.class.toString());
		log.setIdAccion(peso.getId());
		log.setDescripcion(peso.toString());
		this.logService.saveLog(log);
        peso.setHora(mantenimiento.getHora());
        peso.setPeso(mantenimiento.getPeso());
        MantenimientoPesoHora editPesoHora = this.mantenimientoPesoHoraService.saveMantenimientoPesoHora(peso);
        return ResponseEntity.ok(editPesoHora);
    }
    
    @PostMapping("/eliminar-peso-hora")
    public ResponseEntity<?> deletePesoHora(@RequestBody MantenimientoPesoHora mantenimiento) {
        MantenimientoPesoHora peso = this.mantenimientoPesoHoraService.buscarPeso(mantenimiento.getPeso());
        LogSistema log = new LogSistema();
			log.setAccion("DELETE");
			log.setFechaHora(new Date());
			log.setTabla(MantenimientoUnidad.class.toString());
			log.setIdAccion(peso.getId());
			log.setDescripcion(peso.toString());
			this.logService.saveLog(log);
        this.mantenimientoPesoHoraService.deleteById(peso.getId());
        return ResponseEntity.ok("Eliminado correctamente");
    }
    

}
