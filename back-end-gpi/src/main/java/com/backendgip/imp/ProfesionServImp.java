//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Profesion;
import com.backendgip.repository.ProfesionRepository;
import com.backendgip.service.ProfesionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionServImp implements ProfesionService {
	@Autowired
	private ProfesionRepository profesionRepository;

	public ProfesionServImp() {
	}

	public List<Profesion> getProfesiones() {
		return (List) this.profesionRepository.findAll();
	}

	public Profesion saveProfesion(Profesion profesion) {
		return (Profesion) this.profesionRepository.save(profesion);
	}

	public void deleteProfesion(Profesion profesion) {
		this.profesionRepository.delete(profesion);
	}

	public Profesion getProfesionById(Integer id) {
		return (Profesion) this.profesionRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	@Override
	public Profesion findByProfesion(String profesion) {
		return this.profesionRepository.findByProfesion(profesion);
	}
}
