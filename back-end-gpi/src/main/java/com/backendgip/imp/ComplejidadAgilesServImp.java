package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.ComplejidadAgiles;
import com.backendgip.repository.ComplejidadAgilesRepository;
import com.backendgip.service.ComplejidadAgilesService;

@Service
public class ComplejidadAgilesServImp implements ComplejidadAgilesService {
    
    @Autowired
    private ComplejidadAgilesRepository complejidadAgilesRepository;

    @Override
    public ComplejidadAgiles getComplejidadesById(Integer id) {
        return (ComplejidadAgiles) this.complejidadAgilesRepository.findById(id).orElseThrow(null);
    };

    @Override
    public List<ComplejidadAgiles> getAllComplejidadAgiles() {
        return (List<ComplejidadAgiles>) this.complejidadAgilesRepository.findAll();
    };

    @Override
    public ComplejidadAgiles saveComplejidadAgiles(ComplejidadAgiles complejidadAgiles) {
        return (ComplejidadAgiles) this.complejidadAgilesRepository.save(complejidadAgiles);
    };

    @Override
    public void deleteComplejidades(Integer id) {
        this.complejidadAgilesRepository.deleteById(id);
    }
}
