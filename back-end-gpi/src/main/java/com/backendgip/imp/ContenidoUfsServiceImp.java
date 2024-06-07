//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.service.ContenidoUfsService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContenidoUfsServiceImp implements ContenidoUfsService {
	@Autowired
	private ContenidoUfsRepository contenidoUfsRepository;

	public List<ContenidoUfs> getContenidoUfs() {
		return (List) this.contenidoUfsRepository.findAll();
	}

	public ContenidoUfs saveContenidoUfs(ContenidoUfs contenidoUfs) {
		return (ContenidoUfs) this.contenidoUfsRepository.save(contenidoUfs);
	}

	public ContenidoUfs getContenidoUfsById(Integer idContenidoUfs) {
		return (ContenidoUfs) this.contenidoUfsRepository.findById(idContenidoUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("La estimacion no existe" + idContenidoUfs);
		});
	}

	public void deleteContenidoUfs(ContenidoUfs contenidoUfs) {
		this.contenidoUfsRepository.delete(contenidoUfs);
	}
}

