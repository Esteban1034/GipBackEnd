package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.Ufs;
import com.backendgip.repository.UfsRepository;
import com.backendgip.service.UfsService;

@Service
public class UfsServiceImp implements UfsService {

    @Autowired
    UfsService ufsService;
    @Autowired
    UfsRepository ufsRepository;

    public UfsServiceImp() {
	}

    public List<Ufs> getUfs(){
        return (List<Ufs>) this.ufsRepository.findAll();
    }

    public Ufs saveUfs(Ufs ufs){
        return this.ufsRepository.save(ufs);
    }

    public void deleteUfs(Ufs ufs){
        this.ufsRepository.delete(ufs);
    }

    public Ufs getUfsById(Integer idUfs) {
		return (Ufs) this.ufsRepository.findById(idUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("Cliente no existe con el ID" + idUfs);
		});
	}
    
}
