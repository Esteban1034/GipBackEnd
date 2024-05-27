package com.backendgip.service;

import com.backendgip.model.Estimaciones;
import com.backendgip.repository.EstimacionesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EstimacionesService {
    @Autowired
    private EstimacionesRepository estimacionesRepository;
    
    private List<Estimaciones> estimacionesList = new ArrayList<>();

    public List<Estimaciones> listarEstimaciones() {
        return estimacionesList;
    }

    public List<Estimaciones> listarEstimacioness() {
        return estimacionesRepository.findAll();
    }

    public Estimaciones save(Estimaciones estimacion) {
        // Guardar la estimación en el repositorio
        Estimaciones estimacionGuardada = estimacionesRepository.save(estimacion);
        
        // Agregar la estimación a la lista de estimaciones
        estimacionesList.add(estimacionGuardada);
        
        // Retornar la estimación guardada
        return estimacionGuardada;
    }

    public String obtenerEstadoEstimacion() {
             return null;
    }
    
    public boolean eliminarEstimacion(Long id) {
        try {
            estimacionesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Estimaciones obtenerEstimacionPorId(Long id) {
        return estimacionesRepository.findById(id).orElse(null);
    }
}
