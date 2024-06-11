package com.backendgip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.Esfuerzo;
import com.backendgip.model.Funcion;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.service.EsfuerzoService;
import com.backendgip.service.FuncionService;


@RestController
@RequestMapping("/api")
public class FuncionController {

    @Autowired

    private FuncionService funcionService;
    @Autowired

    


    @GetMapping({"/funcion"})
    public List<Funcion> getFuncions(){
        return this.funcionService.geFuncions() ;
    }
    
   
}

