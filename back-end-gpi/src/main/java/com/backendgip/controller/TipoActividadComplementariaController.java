package com.backendgip.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.service.TipoActividadComplementariaService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/tipo-actividades-complementarias")
public class TipoActividadComplementariaController {

    @Autowired
    private TipoActividadComplementariaService tipoActividadComplementariaService;

    @GetMapping("/obtener-actividades")
    public List<TipoActividadComplementaria> obtenerActividades() {
        return tipoActividadComplementariaService.getActividades();
    }

    @GetMapping("/obtener-actividad/{id}")
    public ResponseEntity<TipoActividadComplementaria> obtenerActividadPorId(@PathVariable Integer id) {
        TipoActividadComplementaria actividad = tipoActividadComplementariaService.getActividadById(id);
        if (actividad == null) {
            throw new ResourceNotFoundException("Actividad no encontrada con id: " + id);
        }
        return new ResponseEntity<>(actividad, HttpStatus.OK);
    }

    @PostMapping("/crear-actividad")
    public ResponseEntity<TipoActividadComplementaria> crearActividad(
            @RequestBody TipoActividadComplementaria actividad) {
        TipoActividadComplementaria nuevaActividad = tipoActividadComplementariaService.saveActividad(actividad);
        return new ResponseEntity<>(nuevaActividad, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-actividad/{id}")
    public ResponseEntity<TipoActividadComplementaria> actualizarActividad(
            @PathVariable Integer id, @RequestBody TipoActividadComplementaria actividad) {
        TipoActividadComplementaria actividadExistente = tipoActividadComplementariaService.getActividadById(id);
        if (actividadExistente == null) {
            throw new ResourceNotFoundException("Actividad no encontrada con id: " + id);
        }

        actividad.setId(id); 
        TipoActividadComplementaria actividadActualizada = tipoActividadComplementariaService.saveActividad(actividad);
        return new ResponseEntity<>(actividadActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar-actividad/{id}")
    public ResponseEntity<?> eliminarActividad(@PathVariable Integer id) {
        System.out.println(id);
        this.tipoActividadComplementariaService.deleteActividad(id);
        Map<String, Boolean> response = new HashMap();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}