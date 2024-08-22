package com.backendgip.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.LogSistema;
import com.backendgip.model.TipoActividadComplementariaComplemento;
import com.backendgip.repository.TipoActividadComplementariaComplementoRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.TipoActividadComplementariaComplementoServide;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/actividades-complementarias-complemento")
public class TipoActividadComplementariaComplementoController {

    @Autowired
    private TipoActividadComplementariaComplementoServide ComplementoServide;
    @Autowired
	private LogSistemaService logService;
    @Autowired
    private TipoActividadComplementariaComplementoRepository ComplementariaRepository;

    @GetMapping("/obtener")
    public ResponseEntity<List<TipoActividadComplementariaComplemento>> getActividadesComplemento() {
        return ResponseEntity.ok(ComplementoServide.getATipoActividadComplementariaComplementos());
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> postActividadesComplemento(@RequestBody TipoActividadComplementariaComplemento actividad) {
        TipoActividadComplementariaComplemento actividadcreada = ComplementoServide.createActividadComplementariaComplemento(actividad);
        LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(TipoActividadComplementariaComplemento.class.toString());
		log.setIdAccion(actividadcreada.getId());
		log.setDescripcion(actividadcreada.toString());
		this.logService.saveLog(log);
        return ResponseEntity.ok(actividadcreada);
    }
    
    @PutMapping("/editar")
    public ResponseEntity<TipoActividadComplementariaComplemento> putActividadesComplemento(@RequestBody TipoActividadComplementariaComplemento actividad) {
        TipoActividadComplementariaComplemento actividadEncontrada = ComplementariaRepository.findById(actividad.getId()).orElse(null);
        LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setFechaHora(new Date());
		log.setTabla(TipoActividadComplementariaComplemento.class.toString());
		log.setIdAccion(actividadEncontrada.getId());
		log.setDescripcion(actividadEncontrada.toString());
		this.logService.saveLog(log);
        actividadEncontrada.setNombre(actividad.getNombre());
        actividadEncontrada.setActividad(actividad.getActividad());
        TipoActividadComplementariaComplemento actividadCreada = ComplementoServide.createActividadComplementariaComplemento(actividadEncontrada);
        return ResponseEntity.ok(actividadCreada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarActividadesComplemento(@PathVariable Integer id) {
        this.ComplementoServide.deleteActividadComplementaria(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
}
