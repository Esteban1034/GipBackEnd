package com.backendgip.service;

import com.backendgip.model.estimaciones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstimacionesService {

    private List<estimaciones> estimacionesList = new ArrayList<>();

    public List<estimaciones> listarEstimaciones() {
        return estimacionesList;
    }

    public estimaciones save(estimaciones estimacion) {
              return estimacion;
    }

    public void eliminarEstimacion(Long id) {
        estimacionesList.removeIf(estimacion -> estimacion.getId().equals(id));
    }

    public estimaciones actualizarEstimacion(Long id, estimaciones estimacionActualizada) {
        for (int i = 0; i < estimacionesList.size(); i++) {
            if (estimacionesList.get(i).getId().equals(id)) {
                estimacionesList.set(i, estimacionActualizada);
                return estimacionActualizada;
            }
        }
        return null;
    }
}
