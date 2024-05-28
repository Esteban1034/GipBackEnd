package com.backendgip.controller;

import com.backendgip.model.Cliente;
import com.backendgip.model.Estimaciones;
import com.backendgip.model.Proyecto;
import com.backendgip.service.ClienteService;
import com.backendgip.service.EstimacionesService;
import com.backendgip.service.ProyectoService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EstimacionesController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstimacionesService estimacionesService;


    @GetMapping({"/estimaciones"})
	public List<Estimaciones> getEstimaciones(){
		return this.estimacionesService.getEstimaciones();
	}

}
