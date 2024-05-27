//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.Complejidad;
import java.time.LocalDate;
import java.util.List;

public interface ComplejidadService {
	List<Complejidad> getComplejidad();

	Complejidad saveComplejidad(Complejidad complejidad);

	void deleteComplejidad(Complejidad complejidad);

	Complejidad getComplejidadById(Integer idComplejidad);

}
