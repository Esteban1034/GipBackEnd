package com.backendgip.service;

import com.backendgip.model.Profesion;
import java.util.List;

public interface ProfesionService {
	List<Profesion> getProfesiones();
	Profesion saveProfesion(Profesion profesion);
	void deleteProfesion(Profesion profesion);
	Profesion getProfesionById(Integer id);
	Profesion findByProfesion(String profesion);
}
