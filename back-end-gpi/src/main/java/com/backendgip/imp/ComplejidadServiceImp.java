//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Complejidad;
import com.backendgip.repository.ComplejidadRepository;
import com.backendgip.service.ComplejidadService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplejidadServiceImp implements ComplejidadService  {
	@Autowired
	private ComplejidadRepository complejidadRepository;

	public ComplejidadServiceImp() {
	}

	public List<Complejidad> getComplejidad() {
		return (List) this.complejidadRepository.findAll();
	}

	public Complejidad saveComplejidad(Complejidad complejidad) {
		return (Complejidad) this.complejidadRepository.save(complejidad);
	}

	public void deleteComplejidad(Complejidad complejidad) {
		this.complejidadRepository.delete(complejidad);
	}

	public Complejidad getComplejidadById(Integer idComplejidad) {
		return (Complejidad) this.complejidadRepository.findById(idComplejidad).orElseThrow(() -> {
			return new ResourceNotFoundException("Complejidad no existe con el ID" + idComplejidad);
		});
	}

}
