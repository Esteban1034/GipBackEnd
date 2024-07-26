package com.backendgip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendgip.exception.ResourceAlreadyExistsException;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Empleado;
import com.backendgip.model.Esfuerzo;
import com.backendgip.model.Funcion;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.service.EsfuerzoService;
import com.backendgip.service.FuncionService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FuncionController {

    @Autowired

    private FuncionService funcionService;

    @Autowired

    @GetMapping({ "/funciones" })
    public List<Funcion> getFuncions() {
        return this.funcionService.geFuncions();
    }

    @PostMapping("/funciones")
    public ResponseEntity<Funcion> saveFuncion(@RequestBody Funcion funcion) {
        Funcion savedFuncion = funcionService.createFuncion(funcion);
        return new ResponseEntity<>(savedFuncion, HttpStatus.CREATED);
    }

    @GetMapping({ "/funciones/{id}" })
	public ResponseEntity<Funcion> getfuncionById (@PathVariable Integer id) {
		Funcion funcion = (Funcion) this.funcionService.getFuncionById(id);
		return ResponseEntity.ok(funcion);
	}

}
