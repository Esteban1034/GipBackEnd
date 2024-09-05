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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.FasesAgiles;
import com.backendgip.model.LogSistema;
import com.backendgip.service.FasesAgilesService;
import com.backendgip.service.LogSistemaService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class FasesAgilesController {
    
    @Autowired
    private FasesAgilesService fasesAgilesService;
    @Autowired
    private LogSistemaService logService;

    @GetMapping({ "/fases-metodologia-agil" })
    public List<FasesAgiles> getFasesAgiles(){
        return fasesAgilesService.getFasesAgiles();
    }

    @PostMapping( "/crear-fase" )
    public ResponseEntity<?> createFase(@RequestBody FasesAgiles fasesAgiles){
        if (fasesAgiles.getNombre() != null){
            FasesAgiles createFases = this.fasesAgilesService.saveFasesAgiles(fasesAgiles);
            LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(FasesAgiles.class.toString());
			log.setIdAccion(createFases.getId());
			log.setDescripcion(createFases.toString());
			this.logService.saveLog(log);
            return  ResponseEntity.ok(createFases);
        }else{
            return ResponseEntity.badRequest().body("No se puede crear la Fase, hay valores vacios");
        }
    }

    @PutMapping( "/editar-fase" )
    public ResponseEntity<?> updateFase(@RequestBody FasesAgiles updateFasesAgiles) {
        if (updateFasesAgiles.getNombre() != null){
            FasesAgiles getFasesId = this.fasesAgilesService.getFasesAgilesById(updateFasesAgiles.getId());
            LogSistema log = new LogSistema();
			log.setAccion("UPDATE");
			log.setFechaHora(new Date());
			log.setTabla(FasesAgiles.class.toString());
			log.setIdAccion(getFasesId.getId());
			log.setDescripcion(getFasesId.toString());
			this.logService.saveLog(log);
            getFasesId.setNombre(updateFasesAgiles.getNombre());
            FasesAgiles updateFases = this.fasesAgilesService.saveFasesAgiles(getFasesId);
            return ResponseEntity.ok(updateFases);
        }else{
            return ResponseEntity.badRequest().body("No se actualizo correctamente, hay campos vacios");
        }
    }

    @DeleteMapping( "/eliminar-fase/{id}" )
    public ResponseEntity<Map<String, Boolean>> deleteFase(@PathVariable Integer id){
        this.fasesAgilesService.deleteFases(id);
        Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
    }
}
