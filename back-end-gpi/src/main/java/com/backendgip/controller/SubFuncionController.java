package com.backendgip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;
import com.backendgip.service.SubFuncionService;



@RestController
@RequestMapping("/api")
public class SubFuncionController {

    @Autowired
    private SubFuncionService subfuncionService;


    


    @GetMapping({"/Subfuncion"})
        public ResponseEntity<List<Subfuncion>> getAllSubfunciones() {
        List<Subfuncion> subfunciones = subfuncionService.getAllSubfunciones();
        return ResponseEntity.ok(subfunciones);
    }
     @PostMapping("/Subfuncion")
    public ResponseEntity<Subfuncion> createSubfuncion(@RequestBody Subfuncion subfuncion) {
        Subfuncion createdSubfuncion = subfuncionService.createSubfuncion(subfuncion);
        return new ResponseEntity<>(createdSubfuncion, HttpStatus.CREATED);
    }
    
    
    
    
   
}

