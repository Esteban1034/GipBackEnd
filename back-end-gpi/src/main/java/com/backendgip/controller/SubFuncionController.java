package com.backendgip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.repository.FuncionRepository;
import com.backendgip.repository.UnidadFuncionalRepository;
import com.backendgip.service.FuncionService;
import com.backendgip.service.SubFuncionService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class SubFuncionController {

    @Autowired
    private SubFuncionService subfuncionService;
    @Autowired
    private FuncionService funcionService;
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private EstimacionesUfsRepository estimacionesUfsRepository;

    @GetMapping({ "/subfuncion" })
    public ResponseEntity<List<Subfuncion>> getAllSubfunciones() {
        List<Subfuncion> subfunciones = subfuncionService.getAllSubfunciones();
        return ResponseEntity.ok(subfunciones);
    }

    @PostMapping("/subfuncion")
    public ResponseEntity<Subfuncion> createSubfuncion(@RequestBody Subfuncion subfuncion) {
        Subfuncion createdSubfuncion = subfuncionService.createSubfuncion(subfuncion);
        return new ResponseEntity<>(createdSubfuncion, HttpStatus.CREATED);
    }

    @GetMapping({ "/subfuncion/{idFuncion}" })
    public List<Subfuncion> findByFuncion(@PathVariable Integer idFuncion) {
        Funcion funcion = (Funcion) this.funcionRepository.findById(idFuncion).orElseThrow(() -> {
            return new ResourceNotFoundException("Funcion no encontrado con id:" + idFuncion);
        });
        return this.subfuncionService.findByFuncion(funcion);
    }

    @GetMapping("/subfuncion/estimacion/{idEstimacion}")
    public ResponseEntity<List<Subfuncion>> findByEstimacionUfs(@PathVariable Integer idEstimacion) {
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(idEstimacion)
                .orElseThrow(() -> new ResourceNotFoundException("Estimación no encontrada con el id: " + idEstimacion));
        
        List<Subfuncion> subfunciones = subfuncionService.findByEstimacionUfs(estimacion);
        
        if (subfunciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron Subfunciones para la estimación con id:" + idEstimacion);
        }

        return ResponseEntity.ok(subfunciones);
        
    }

}
