package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.repository.TipoActividadComplementariaRespository;
import com.backendgip.service.TipoActividadComplementariaService;

@Service
public class TipoActividadComplementariaServImp implements TipoActividadComplementariaService{

    @Autowired
    private TipoActividadComplementariaRespository tipoActividadRespository;

    @Override
    public List<TipoActividadComplementaria> getActividades() {
        return (List<TipoActividadComplementaria>) this.tipoActividadRespository.findAll();
    }

    @Override
    public TipoActividadComplementaria getTipoActividadNombre(String nombre) {
        return this.tipoActividadRespository.findByNombre(nombre);
    }
    
}
