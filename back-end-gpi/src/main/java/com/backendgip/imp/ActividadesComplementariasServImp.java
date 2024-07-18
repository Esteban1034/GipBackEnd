package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ActividadesComplementarias;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.repository.ActividadesComplementariasRepository;

import com.backendgip.service.ActividadesComplementariasService;

@Service
public class ActividadesComplementariasServImp implements ActividadesComplementariasService {
    
    @Autowired
    private ActividadesComplementariasRepository actividadesComplementariasRepository;

    public List<ActividadesComplementarias> getActividades(){
        return (List<ActividadesComplementarias>) actividadesComplementariasRepository.findAll();
    }

    public ActividadesComplementarias saveActividad(ActividadesComplementarias actividad) {
		return (ActividadesComplementarias) this.actividadesComplementariasRepository.save(actividad);
	  }

    @Override
    public void deleteActividad(Integer id) {
      this.actividadesComplementariasRepository.deleteById(id);
    }

    @Override
    public ActividadesComplementarias getById(Integer id) {
        return (ActividadesComplementarias) this.actividadesComplementariasRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la actividad con el id:" + id);
		});
    }

    @Override
    public List<ActividadesComplementarias> getActividadesEstimacion(EstimacionUfs estimacion) {
      return this.actividadesComplementariasRepository.findByEstimacion(estimacion);
    }

}
