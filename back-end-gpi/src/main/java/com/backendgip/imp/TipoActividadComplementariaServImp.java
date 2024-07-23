package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.TipoActividadComplementaria;
import com.backendgip.repository.TipoActividadComplementariaRespository;
import com.backendgip.service.TipoActividadComplementariaService;

import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;

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

    @Override
    public TipoActividadComplementaria saveActividad(TipoActividadComplementaria tipoActividadComplementaria) {
        return this.tipoActividadRespository.save(tipoActividadComplementaria);
    }

    @Override
    public TipoActividadComplementaria getActividadById(Integer id) {
        java.util.Optional<TipoActividadComplementaria> optionalActividad = this.tipoActividadRespository.findById(id);
        if (optionalActividad.isPresent()) {
            return optionalActividad.get();
        } else {
            throw new RuntimeException("Actividad no encontrada para el ID :: " + id);
        }
    }

    @Override
    public void deleteActividad(Integer id) {
        tipoActividadRespository.deleteById(id);
    }
    
}
