package com.backendgip.service;

import com.backendgip.model.Cliente;
import com.backendgip.model.Estimaciones;
import java.util.ArrayList;
import java.util.List;


public interface EstimacionesService {
    List<Estimaciones> getEstimaciones();  

	Estimaciones saveEstimaciones(Estimaciones estimaciones);

	void deleteEstimaciones(Estimaciones estimaciones);

    boolean existsByEstimacion(Integer id);

    Object findById(Integer id);

} 
