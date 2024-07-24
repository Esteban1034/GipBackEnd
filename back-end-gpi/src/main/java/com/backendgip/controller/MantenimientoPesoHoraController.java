package com.backendgip.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.service.LogSistemaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class MantenimientoPesoHoraController {

    @Autowired
    private MantenimientoPesoHoraService mantenimientoPesoHoraService;
    @Autowired
    private MantenimientoUnidadService mantenimientoUnidadService;
    @Autowired
    private LogSistemaService logService;

    @GetMapping({ "/peso-hora" })
    public List<MantenimientoPesoHora> getPesoHora() {
        return this.mantenimientoPesoHoraService.getPesoHora();
    }

    @GetMapping("/mantenimiento-peso-hora/obtener-hora/{peso}")
    public ResponseEntity<?> getHoraByPeso(@PathVariable Integer peso) {
        try {
            MantenimientoPesoHora pesoMantenimiento = mantenimientoPesoHoraService.buscarPeso(peso);
            if (pesoMantenimiento != null) {
                return ResponseEntity.ok(pesoMantenimiento);
            } else {
                return ResponseEntity.badRequest().body("No se encontró el peso a buscar");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el peso: " + e.getMessage());
        }
    }

    @PostMapping("/crear-peso-hora")
    public ResponseEntity<?> guardarUnidad(@RequestBody MantenimientoPesoHora mantenimientoPeso) {
        if (this.mantenimientoPesoHoraService.validarPeso(mantenimientoPeso.getPeso())) {
            return ResponseEntity.badRequest()
                    .body("No se puede crear el peso y hora, el peso asiganado ya es existente");
        } else {
            if(mantenimientoPeso.getHora() != null && mantenimientoPeso.getPeso() != null){
                MantenimientoPesoHora createPesoHora = this.mantenimientoPesoHoraService
                    .saveMantenimientoPesoHora(mantenimientoPeso);
                LogSistema log = new LogSistema();
                log.setAccion("CREATE");
                log.setFechaHora(new Date());
                log.setTabla(MantenimientoUnidad.class.toString());
                log.setIdAccion(createPesoHora.getId());
                log.setDescripcion(createPesoHora.toString());
                this.logService.saveLog(log);
                return ResponseEntity.ok(createPesoHora);
            }else{
                return ResponseEntity.badRequest()
                    .body("No se puede crear el peso y hora, hay valores vacios");
            }
            
        }
    }

    @PutMapping("/editar-peso-hora")
    public ResponseEntity<?> editarUnidad(@RequestBody MantenimientoPesoHora mantenimiento) {
        if (mantenimiento.getHora() != null && mantenimiento.getPeso() != null) {
            MantenimientoPesoHora peso = this.mantenimientoPesoHoraService.findById(mantenimiento.getId());
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
        }else{
            return ResponseEntity.badRequest().body("No se actualizo correctamente, hay campos vacios");
        }
        
    }

    @PostMapping("/eliminar-peso-hora/{id}")
    public ResponseEntity<?> deletePesoHora(@PathVariable Integer id) {
        MantenimientoPesoHora pesoyhora = mantenimientoPesoHoraService.findById(id);
        MantenimientoUnidad unidad = mantenimientoUnidadService.findByPeso(pesoyhora.getPeso());
        Map<String, Boolean> response = new HashMap();
        if (unidad != null) {
            return ResponseEntity.badRequest()
            .body("Hay una unidad de programacion asignada");
        }else{
            this.mantenimientoPesoHoraService.deleteById(id);
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
    }

}
