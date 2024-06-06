package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.service.ContenidoUfsService;

@Service
public class ContenidoUfsServImp implements ContenidoUfsService {

    @Autowired
    private ContenidoUfsRepository contenidoUfsRepository;

    public List<ContenidoUfs> getContenidoUfs(){
        return (List<ContenidoUfs>) contenidoUfsRepository.findAll();
    }
    
    public ContenidoUfs saveContenidoUfs(ContenidoUfs contenidoUfs) {
		return (ContenidoUfs) this.contenidoUfsRepository.save(contenidoUfs);
	}

    public void deleteContenidoUfs(ContenidoUfs contenidoUfs) {
		this.contenidoUfsRepository.delete(contenidoUfs);
	}

	public ContenidoUfs getContenidoUfsById(Integer idContenidoUfs) {
		return (ContenidoUfs) this.contenidoUfsRepository.findById(idContenidoUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("Contenido no existe con el ID" + idContenidoUfs);
		});
	}
}
