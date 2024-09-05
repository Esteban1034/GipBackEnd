package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.FasesAgiles;
import com.backendgip.repository.FasesAgilesRepository;
import com.backendgip.service.FasesAgilesService;

@Service
public class FasesAgilesServImp implements FasesAgilesService {

    @Autowired
    private FasesAgilesRepository fasesAgilesRepository;

    @Override
    public FasesAgiles getFasesAgilesById(Integer id) {
        return (FasesAgiles) this.fasesAgilesRepository.findById(id).orElseThrow(null);
    }

    @Override
    public FasesAgiles findByNombre(String nombre) {
        return this.fasesAgilesRepository.findByNombre(nombre);
    }

    @Override
    public void deleteFases(Integer id){
        this.fasesAgilesRepository.deleteById(id);
    }
    
    public List<FasesAgiles> getFasesAgiles() {
        return (List<FasesAgiles>) this.fasesAgilesRepository.findAll();
    }

    @Override
    public FasesAgiles saveFasesAgiles(FasesAgiles fasesAgiles) {
        return (FasesAgiles) this.fasesAgilesRepository.save(fasesAgiles);
    }
}
