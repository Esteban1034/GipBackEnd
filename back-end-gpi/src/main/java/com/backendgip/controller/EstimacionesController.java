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
@RequestMapping("/estimaciones")
public class EstimacionesController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstimacionesService estimacionesService;


    @GetMapping("/listar")
    public List<Estimaciones> listarEstimacionesConDatos() {
        // Obtener la lista de todas las estimaciones
        List<Estimaciones> estimacionesList = estimacionesService.listarEstimacioness();
    
        // Crear una lista para almacenar los datos de cada estimación
        List<Estimaciones> estimacionesConDatos = new ArrayList<>();
        long idCounter = 1;
        for (Estimaciones estimacion : estimacionesList) {
            Proyecto proyecto = estimacion.getProyecto();
            Cliente cliente = estimacion.getCliente();
            String estadoEstimacion = estimacion.getEstadoEstimacion();
    
            Estimaciones estimacionDatos = new Estimaciones(idCounter++, proyecto, cliente, estadoEstimacion);
            estimacionesConDatos.add(estimacionDatos);
        }
    
        // Devolver la lista de datos de estimaciones
        return estimacionesConDatos;
    }
   


    @GetMapping("/listarR")
    public List<Estimaciones> listarEstimaciones() {
        // Obtener la lista de todos los proyectos y clientes
        List<Proyecto> proyectos = proyectoService.getProyectos();
        List<Cliente> clientes = clienteService.getClientes();
    
        // Crear una lista para almacenar todas las estimaciones
        List<Estimaciones> estimacionesList = new ArrayList<>();
    
        // Iterar sobre la lista de proyectos y clientes para crear las estimaciones
        for (Proyecto proyecto : proyectos) {
            for (Cliente cliente : clientes) {
                // Obtener el estado de estimación de alguna manera (p. ej., de un método en EstimacionesService)
                String estadoEstimacion = estimacionesService.obtenerEstadoEstimacion(); 
    
                // Crear una nueva estimación con el proyecto, cliente y estadoEstimacion
                Estimaciones estimacion = new Estimaciones(proyecto, cliente, estadoEstimacion);
                estimacionesList.add(estimacion);
            }
        }
    
        // Devolver la lista de todas las estimaciones creadas
        return estimacionesList;
    
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearEstimacion(@RequestBody Estimaciones estimacion) {
        Estimaciones estimacionGuardada = estimacionesService.save(estimacion);

        if (estimacionGuardada != null) {
            return new ResponseEntity<>("Estimación creada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al crear la estimación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarEstimacion(@PathVariable Long id, @RequestBody Estimaciones estimacionActualizada) {
        Estimaciones estimacionExistente = estimacionesService.obtenerEstimacionPorId(id);

        if (estimacionExistente != null) {
            estimacionExistente.setProyecto(estimacionActualizada.getProyecto());
            estimacionExistente.setCliente(estimacionActualizada.getCliente());
            estimacionExistente.setEstadoEstimacion(estimacionActualizada.getEstadoEstimacion());

            Estimaciones estimacionActualizadaGuardada = estimacionesService.save(estimacionExistente);

            if (estimacionActualizadaGuardada != null) {
                return new ResponseEntity<>("Estimación actualizada correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error al actualizar la estimación", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("No se pudo encontrar la estimación con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
