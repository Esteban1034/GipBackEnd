//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.FullCreateEstimacion;
import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.repository.FuncionRepository;
import com.backendgip.repository.SubfuncionRepository;
import com.backendgip.repository.UnidadFuncionalRepository;
import com.backendgip.service.EstimacionesUfsService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimacionesUfsServiceImp implements EstimacionesUfsService {
    @Autowired
    private EstimacionesUfsRepository estimacionesUfsRepository;
    @Autowired
    private UnidadFuncionalRepository unidadFuncionalRepository;
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private SubfuncionRepository subfuncionRepository;


    public List<EstimacionUfs> getEstimaciones() {
        return (List<EstimacionUfs>) estimacionesUfsRepository.findAll();
    }


    public EstimacionUfs saveEstimacionIn(EstimacionUfs estimacionUfs) {
        return estimacionesUfsRepository.save(estimacionUfs);
    }

    public EstimacionUfs saveEstimaciones(EstimacionUfs estimacionUfs) {
        return estimacionesUfsRepository.save(estimacionUfs);
    }

    public void deleteEstimaciones(EstimacionUfs estimacionUfs) {
        estimacionesUfsRepository.delete(estimacionUfs);
    }
    
    public EstimacionUfs getUltimaEstimacionUfs() {
        Long ultimoId = estimacionesUfsRepository.findMaxId();
        if (ultimoId != null) {
            return estimacionesUfsRepository.findById(ultimoId.intValue()).orElse(null);
        }
        return null;
    }

    public boolean existsByProyectoId(Integer proyectoId) {
		return this.estimacionesUfsRepository.existsByProyectoId(proyectoId);
	}

    public EstimacionUfs getEstimacionById(Integer id) {
        return estimacionesUfsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estimacion no encontrada"));
    }

    @Transactional
    public EstimacionUfs saveCreacionCompleta(FullCreateEstimacion dto) {
        EstimacionUfs estimacion = dto.getEstimacionUfs();
        EstimacionUfs savedEstimacion = estimacionesUfsRepository.save(estimacion);

        List<UnidadFuncional> unidadesFuncionales = dto.getUnidadFuncionales();
        for (UnidadFuncional unidadFuncional : unidadesFuncionales) {
            unidadFuncional.setEstimacion_ufs(savedEstimacion);
            unidadFuncionalRepository.save(unidadFuncional);
        }

        List<Funcion> funciones = dto.getFunciones();
        for (Funcion funcion : funciones) {
            for (UnidadFuncional unidadFuncional : unidadesFuncionales) {
                if (funcion.getUnidadFuncional().getId().equals(unidadFuncional.getId())) {
                    funcion.setUnidadFuncional(unidadFuncional);
                    funcionRepository.save(funcion);
                }
            }
        }

        List<Subfuncion> subfunciones = dto.getSubfunciones();
        for (Subfuncion subfuncion : subfunciones) {
            for (Funcion funcion : funciones) {
                if (subfuncion.getFuncion().getId().equals(funcion.getId())) {
                    subfuncion.setFuncion(funcion);
                    subfuncionRepository.save(subfuncion);
                }
            }
        }

        return savedEstimacion;
    }
}
