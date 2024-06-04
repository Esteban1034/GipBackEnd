package com.backendgip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.service.MantenimientoUnidadService;
import org.springframework.web.bind.annotation.GetMapping;

import com.backendgip.model.MantenimientoUnidad;

@RestController
@RequestMapping("/api")
public class MantenimientoUnidadController {

    @Autowired
    private MantenimientoUnidadService mantenimientoUnidadService;


    @GetMapping("/mantenimiento-unidad")
    public List<MantenimientoUnidad> getMantenimiento() {
        return this.mantenimientoUnidadService.getMantenimiento();
    }
    
}
