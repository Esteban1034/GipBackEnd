//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.MantenimientoUnidad;
import java.time.LocalDate;
import java.util.List;

public interface ContenidoUfsService {
	List<ContenidoUfs> getContenidoUfs();

	ContenidoUfs saveContenidoUfs(ContenidoUfs contenidoUfs);

	ContenidoUfs getContenidoUfsById(Integer idContenidUfs);

	void deleteContenidoUfs(ContenidoUfs contenidoUfs);

}

