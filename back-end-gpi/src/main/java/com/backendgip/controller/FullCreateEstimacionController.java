package com.backendgip.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.FullCreateEstimacion;
import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.FuncionService;
import com.backendgip.service.SubFuncionService;
import com.backendgip.service.UnidadFuncionalService;

@RestController
@RequestMapping("/api")
public class FullCreateEstimacionController {

    @Autowired
    EstimacionesUfsService estimacionService;
    @Autowired
    UnidadFuncionalService unidadFuncionalService;
    @Autowired
    FuncionService funcionService;
    @Autowired
    SubFuncionService subFuncionService;


    @GetMapping({"/estimacion-data/{estimacionId}"})
    public ResponseEntity<FullCreateEstimacion> getFullEstimacion(@PathVariable Integer estimacionId) {
        EstimacionUfs estimacion = estimacionService.getEstimacionById(estimacionId);
        List<UnidadFuncional> unidadesFuncionales = unidadFuncionalService.findByEstimacionUfsId(estimacionId);
        List<Funcion> funciones = funcionService.geFuncions();
        List<Subfuncion> subfunciones = subFuncionService.getAllSubfunciones();

        FullCreateEstimacion fullCreateEstimacion = new FullCreateEstimacion();
        fullCreateEstimacion.setEstimacionUfs(estimacion);
        fullCreateEstimacion.setUnidadFuncionales(unidadesFuncionales);
        fullCreateEstimacion.setFunciones(funciones);
        fullCreateEstimacion.setSubfunciones(subfunciones);

        return ResponseEntity.ok(fullCreateEstimacion);
    }
}
