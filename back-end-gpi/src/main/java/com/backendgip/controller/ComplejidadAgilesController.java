package com.backendgip.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.ComplejidadAgiles;
import com.backendgip.model.LogSistema;
import com.backendgip.service.ComplejidadAgilesService;
import com.backendgip.service.LogSistemaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class ComplejidadAgilesController {
    
    @Autowired
    private ComplejidadAgilesService complejidadAgilesService;
    @Autowired
    private LogSistemaService logService;

    @GetMapping({ "/complejidades-metodologia-agil" })
    public List<ComplejidadAgiles> getAllComplejidadAgiles() {
        return complejidadAgilesService.getAllComplejidadAgiles();
    };

    @PostMapping( "/crear-complejidad" )
    public ResponseEntity<?> createComplejidad(@RequestBody ComplejidadAgiles complejidadAgiles) {

        // Casteto del 'getHoraActividad' ya que es de tipo Int (tipo primitivo) y siempre tienen un valor, no permitia comparar con null
        Integer horaActividad = complejidadAgiles.getHoraActividad();

        if (complejidadAgiles.getComplejidadActividad() != null && horaActividad != null) {
            
            ComplejidadAgiles createComplejidad = this.complejidadAgilesService.saveComplejidadAgiles(complejidadAgiles);
            LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(ComplejidadAgiles.class.toString());
			log.setIdAccion(createComplejidad.getId());
			log.setDescripcion(createComplejidad.toString());
			this.logService.saveLog(log);
            return  ResponseEntity.ok(createComplejidad);
        }else {
            return ResponseEntity.badRequest().body("No se puede crear la Complejidad, hay valores vacios");
        }
    };

    @PutMapping( "/editar-complejidad" )
    public ResponseEntity<?> updateComplejidad(@RequestBody ComplejidadAgiles updateComplejidadAgiles) {

        // Casteto del 'getHoraActividad' ya que es de tipo Int (tipo primitivo) y siempre tienen un valor, no permitia comparar con null
        Integer horaActividad = updateComplejidadAgiles.getHoraActividad();
        
        if (updateComplejidadAgiles.getComplejidadActividad() != null && horaActividad != null) {
            
            ComplejidadAgiles complejidadId = this.complejidadAgilesService.getComplejidadesById(updateComplejidadAgiles.getId());
            LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setFechaHora(new Date());
			log.setTabla(ComplejidadAgiles.class.toString());
			log.setIdAccion(complejidadId.getId());
			log.setDescripcion(complejidadId.toString());
			this.logService.saveLog(log);
            complejidadId.setComplejidadActividad(updateComplejidadAgiles.getComplejidadActividad());
            complejidadId.setHoraActividad(updateComplejidadAgiles.getHoraActividad());
            ComplejidadAgiles updateComplejidad = this.complejidadAgilesService.saveComplejidadAgiles(complejidadId);

            return ResponseEntity.ok(updateComplejidad);
        }else {
            return ResponseEntity.badRequest().body("No se actualizo correctamente, hay campos vacios");
        }
    };

    @DeleteMapping( "/eliminar-complejidad/{id}" )
    public ResponseEntity<Map<String, Boolean>> deleteComplejidad(@PathVariable Integer id) {
        this.complejidadAgilesService.deleteComplejidades(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    };
}
