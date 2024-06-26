//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.service.ContenidoUfsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContenidoUfsServiceImp implements ContenidoUfsService {
	@Autowired
	private ContenidoUfsRepository contenidoufsRepository;

	public List<ContenidoUfs> getContenidoUfs() {
		return (List) this.contenidoufsRepository.findAll();
	}

	public ContenidoUfs saveContenidoUfs(ContenidoUfs contenidoUfs) {
		return (ContenidoUfs) this.contenidoufsRepository.save(contenidoUfs);
	}

	public ContenidoUfs getContenidoUfsById(Integer idContenidoUfs) {
		return (ContenidoUfs) this.contenidoufsRepository.findById(idContenidoUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("Contenido no existe con el ID" + idContenidoUfs);
		});
	}

	public void deleteContenidoUfs(ContenidoUfs contenidoUfs) {
		this.contenidoufsRepository.delete(contenidoUfs);
	}
	public boolean existsById(Integer idUfs) {
        return contenidoufsRepository.existsById(idUfs);
    }

    public ContenidoUfs getUltimoContenidoUfs() {
        Long ultimoId = contenidoufsRepository.findMaxId();
        if (ultimoId != null) {
            return contenidoufsRepository.findById(ultimoId.intValue()).orElse(null);
        }
        return null;
    }


    public Long findMaxId() {
        return contenidoufsRepository.findMaxId();
    }


}


