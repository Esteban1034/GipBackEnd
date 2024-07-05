package com.backendgip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import com.backendgip.model.EstimacionMantenimiento;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.repository.FuncionRepository;
import com.backendgip.repository.MantenimientoUnidadRepository;
import com.backendgip.repository.UnidadFuncionalRepository;
import com.backendgip.service.FuncionService;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import com.backendgip.service.SubFuncionService;
import com.backendgip.service.UnidadFuncionalService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class SubFuncionController {

    @Autowired
    private SubFuncionService subfuncionService;
    @Autowired
    private MantenimientoUnidadService mantenimientoUnidadService;
    @Autowired
    private MantenimientoPesoHoraService mantenimientoPesoHoraService;
    @Autowired
    private UnidadFuncionalService unidadFuncionalService;
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
    public ResponseEntity<List<EstimacionMantenimiento>> findByEstimacionUfs(@PathVariable Integer idEstimacion) {
        EstimacionUfs estimacion = estimacionesUfsRepository.findById(idEstimacion)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Estimación no encontrada con el id: " + idEstimacion));

        List<UnidadFuncional> unidadFuncional = unidadFuncionalService.findByEstimacionUfs(estimacion);
        List<MantenimientoUnidad> mantenimientoUnidad = mantenimientoUnidadService.getMantenimientos();
        List<Subfuncion> subfunciones = subfuncionService.findByEstimacionUfs(estimacion);
        List<EstimacionMantenimiento> estimacionList = new ArrayList<>();

        for (UnidadFuncional uf : unidadFuncional) {
            Map<String, Integer> cantidadMantenimiento = new HashMap<>();
            for (MantenimientoUnidad mantenimiento : mantenimientoUnidad) {
                cantidadMantenimiento.put(mantenimiento.getNombre(), 0);
            }

            for (Subfuncion subfuncion : subfunciones) {
                String nombreMantenimiento = subfuncion.getMantenimientoUnidad().getNombre();
                Integer idUfs = subfuncion.getFuncion().getUnidadFuncional().getId();

                if (idUfs.equals(uf.getId())) {
                    cantidadMantenimiento.put(nombreMantenimiento, cantidadMantenimiento.get(nombreMantenimiento) + 1);
                }
            }

            for (MantenimientoUnidad mantenimiento : mantenimientoUnidad) {
                Integer cantidad = cantidadMantenimiento.get(mantenimiento.getNombre());
                if (cantidad != 0) {
                    EstimacionMantenimiento estimacionMantenimiento = new EstimacionMantenimiento();
                    estimacionMantenimiento.setUnidadFuncional(uf);
                    estimacionMantenimiento.setMantenimientoUnidad(mantenimiento);
                    estimacionMantenimiento.setCantidadUnidad(cantidad);
                    estimacionList.add(estimacionMantenimiento);
                }
            }
        }

        for (EstimacionMantenimiento estimacionMantenimiento : estimacionList) {
            Integer peso = estimacionMantenimiento.getMantenimientoUnidad().getPeso();
            Integer cantidadUnidad = estimacionMantenimiento.getCantidadUnidad();
            estimacionMantenimiento.setMantenimientoPesoHora(mantenimientoPesoHoraService.buscarPeso(peso));
            Integer cantidadHora = cantidadUnidad * estimacionMantenimiento.getMantenimientoPesoHora().getHora().intValue();
            estimacionMantenimiento.setCantidadHora(cantidadHora);
            Integer cantidadPeso = cantidadUnidad * estimacionMantenimiento.getMantenimientoUnidad().getPeso().intValue();
            estimacionMantenimiento.setCantidadPeso(cantidadPeso);
        }

        if (estimacionList.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No se encontraron Subfunciones para la estimación con id:" + idEstimacion);
        }

        return ResponseEntity.ok(estimacionList);
    }

}
