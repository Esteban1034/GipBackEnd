//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;


import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.Funcion;
import com.backendgip.repository.EstimacionesUfsRepository;
import com.backendgip.repository.FuncionRepository;
import com.backendgip.service.EstimacionesUfsService;
import com.backendgip.service.FuncionService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionesServiceImp implements FuncionService {


	@Autowired
    private FuncionRepository funcionRepository;

    public List<Funcion> geFuncions() {
        return (List<Funcion>) funcionRepository.findAll();
    }


    public Funcion saFuncion(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    

    public void deleteFuncion(Funcion funcion) {
        funcionRepository.delete(funcion);
    }
    public Funcion sFuncion(Funcion funcion) {
        return funcionRepository.save(funcion);
    }
    


 
}
