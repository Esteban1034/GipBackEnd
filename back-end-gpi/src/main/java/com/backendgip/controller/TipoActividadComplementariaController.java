package com.backendgip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.service.TipoActividadComplementariaService;



@RestController
@RequestMapping("/api/tipo-actividades-complementarias")
public class TipoActividadComplementariaController {

    @Autowired
    private TipoActividadComplementariaService tipoActividadComplementariaService;

    @GetMapping("/obtener-actividades")
    public List<TipoActividadComplementaria> obtnerActividades(){
        return tipoActividadComplementariaService.getActividades();
    }

}