package com.backendgip.controller;

import com.backendgip.model.Cliente;
import com.backendgip.model.Proyecto;
import com.backendgip.model.Estimaciones;
import com.backendgip.service.ClienteService;
import com.backendgip.service.EstimacionesService;
import com.backendgip.service.ProyectoService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Estimaciones")
public class EstimacionesController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstimacionesService estimacionesService;

    // Supongamos que tienes un método en cada servicio para obtener todos los registros
    @GetMapping("/listar")
    public List<Estimaciones> listarEstimaciones() {
        // Obtener la lista de todos los proyectos y clientes
        List<Proyecto> proyectos = proyectoService.getProyectos();
        List<Cliente> clientes = clienteService.getClientes();

        // Crear una lista para almacenar todas las estimaciones
        List<Estimaciones> estimacionesList = new ArrayList<>();

        // Iterar sobre la lista de proyectos y clientes para crear las estimaciones
        for (Proyecto proyecto : proyectos) {
            for (Cliente cliente : clientes) {
                // Crear una nueva estimación con el proyecto y cliente actuales
                Estimaciones estimacion = new Estimaciones(proyecto, cliente, "Estado de estimación");
                estimacionesList.add(estimacion);
            }
        }

        // Devolver la lista de todas las estimaciones creadas
        return estimacionesList;
    }
    @PostMapping("/crear")
    public Estimaciones crearEstimacion(@RequestBody Estimaciones estimacion) {
            return estimacionesService.save(estimacion);
    }



}
