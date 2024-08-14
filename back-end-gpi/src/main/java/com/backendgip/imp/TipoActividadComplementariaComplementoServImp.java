package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.model.TipoActividadComplementariaComplemento;
import com.backendgip.repository.TipoActividadComplementariaComplementoRepository;
import com.backendgip.service.TipoActividadComplementariaComplementoServide;

@Service
public class TipoActividadComplementariaComplementoServImp implements TipoActividadComplementariaComplementoServide{

    @Autowired
    private TipoActividadComplementariaComplementoRepository tipoActividadRespository;
    
    @Override
    public List<TipoActividadComplementariaComplemento> getActividadesTipoActividad(
            TipoActividadComplementaria actividad) {
        return tipoActividadRespository.findByActividad(actividad);
    }
    
}
