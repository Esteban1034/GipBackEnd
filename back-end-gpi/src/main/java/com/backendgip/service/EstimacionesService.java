package com.backendgip.service;

import com.backendgip.model.Estimaciones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstimacionesService {

    private List<Estimaciones> estimacionesList = new ArrayList<>();

    public List<Estimaciones> listarEstimaciones() {
        return estimacionesList;
    }

    public Estimaciones save(Estimaciones estimacion) {
              return estimacion;
    }

    public void eliminarEstimacion(Long id) {
        estimacionesList.removeIf(estimacion -> estimacion.getId().equals(id));
    }

    public Estimaciones actualizarEstimacion(Long id, Estimaciones estimacionActualizada) {
        for (int i = 0; i < estimacionesList.size(); i++) {
            if (estimacionesList.get(i).getId().equals(id)) {
                estimacionesList.set(i, estimacionActualizada);
                return estimacionActualizada;
            }
        }
        return null;
    }
}
