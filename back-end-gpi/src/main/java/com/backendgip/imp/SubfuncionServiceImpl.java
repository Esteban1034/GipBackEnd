
package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.repository.FuncionRepository;
import com.backendgip.repository.SubfuncionRepository;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.SubFuncionService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubfuncionServiceImpl implements SubFuncionService {

    @Autowired
    private SubfuncionRepository subfuncionRepository;

    public Subfuncion createSubfuncion(Subfuncion subfuncion) {
        return subfuncionRepository.save(subfuncion);
    }

    public List<Subfuncion> getAllSubfunciones() {
        return (List<Subfuncion>) subfuncionRepository.findAll();
    }

    @Override
    public List<Subfuncion> findByFuncion(Funcion funcion) {
        return this.subfuncionRepository.findByFuncion(funcion);
    }

    @Override
    public List<Subfuncion> findByEstimacionUfs(EstimacionUfs estimacionUfs) {
        return subfuncionRepository.findByFuncion_UnidadFuncional_EstimacionUfs(estimacionUfs);
    }
}
