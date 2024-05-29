package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Estimaciones;
import com.backendgip.repository.EstimacionesRepository;
import com.backendgip.service.EstimacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimacionesServiceImp implements EstimacionesService {

    @Autowired
    private EstimacionesRepository estimacionesRepository;

  
    public List<Estimaciones> getEstimaciones() {
        return estimacionesRepository.findAll();
    }

 
    public Estimaciones saveEstimaciones(Estimaciones estimaciones) {
        return estimacionesRepository.save(estimaciones);
    }

    public void deleteEstimaciones(Estimaciones estimaciones) {
        estimacionesRepository.delete(estimaciones);
    }


  
}
