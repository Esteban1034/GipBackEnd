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

    @Override
    public TipoActividadComplementariaComplemento createActividadComplementariaComplemento(
            TipoActividadComplementariaComplemento actividad) {
       return tipoActividadRespository.save(actividad);
    }


    @Override
    public void deleteActividadComplementaria(Integer id) {
        tipoActividadRespository.deleteById(id);
    }

    @Override
    public List<TipoActividadComplementariaComplemento> getATipoActividadComplementariaComplementos() {
        return (List<TipoActividadComplementariaComplemento>) tipoActividadRespository.findAll();
    }
    
}
