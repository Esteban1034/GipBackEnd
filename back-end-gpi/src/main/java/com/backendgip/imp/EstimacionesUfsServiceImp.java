//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.EstimacionesUfsService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimacionesUfsServiceImp implements EstimacionesUfsService {
    @Autowired
    private EstimacionesUfsRepository estimacionesUfsRepository;

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
}
