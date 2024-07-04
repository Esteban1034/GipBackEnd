package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.UnidadFuncional;
import com.backendgip.repository.UnidadFuncionalRepository;
import com.backendgip.service.UnidadFuncionalService;

@Service
public class UnidadFuncionalServiceImp implements UnidadFuncionalService {

    @Autowired
    UnidadFuncionalService ufsService;
    @Autowired
    UnidadFuncionalRepository ufsRepository;

    public UnidadFuncionalServiceImp() {
	}

    public List<UnidadFuncional> getUfs(){
        return (List<UnidadFuncional>) this.ufsRepository.findAll();
    }

    public UnidadFuncional saveUfs(UnidadFuncional ufs){
        return this.ufsRepository.save(ufs);
    }

    public void deleteUfs(UnidadFuncional ufs){
        this.ufsRepository.delete(ufs);
    }

    public UnidadFuncional getUfsById(Integer idUfs) {
		return (UnidadFuncional) this.ufsRepository.findById(idUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("Cliente no existe con el ID" + idUfs);
		});
	}
    
}
