//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.model.EstimacionUfs;

import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.service.EstimacionesUfsService;

import java.util.List;
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
}
