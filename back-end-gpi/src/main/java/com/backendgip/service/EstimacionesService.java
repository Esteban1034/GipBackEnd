package com.backendgip.service;

import com.backendgip.model.Estimaciones;
import java.util.List;

public interface EstimacionesService {
    List<Estimaciones> getEstimaciones();  
    Estimaciones saveEstimaciones(Estimaciones estimaciones);
    void deleteEstimaciones(Estimaciones estimaciones);
    
}
