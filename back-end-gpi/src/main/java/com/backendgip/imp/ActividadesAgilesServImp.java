package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.ActividadesAgiles;
import com.backendgip.repository.ActividadesAgilesRepository;
import com.backendgip.service.ActividadesAgilesService;

@Service
public class ActividadesAgilesServImp implements ActividadesAgilesService{
    
    @Autowired
    private ActividadesAgilesRepository actividadesAgilesRepository;

    @Override
    public List<ActividadesAgiles> getActividades() {
       return (List) actividadesAgilesRepository.findAll();
    }

    @Override
    public ActividadesAgiles saveActividad(ActividadesAgiles actividad) {
        return actividadesAgilesRepository.save(actividad);
    }

    @Override
    public void deleteActividad(ActividadesAgiles actividad) {
        actividadesAgilesRepository.delete(actividad);
    }

    @Override
    public ActividadesAgiles getActividadById(Integer idActividad) {
        return actividadesAgilesRepository.findById(idActividad).orElse(null);
    }

    
}
