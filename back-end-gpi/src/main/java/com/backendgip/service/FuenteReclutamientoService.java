package com.backendgip.service;

import com.backendgip.model.FuenteReclutamiento;
import java.util.List;

public interface FuenteReclutamientoService {
	List<FuenteReclutamiento> getFuenteReclutamientoes();
	FuenteReclutamiento saveFuenteReclutamiento(FuenteReclutamiento fuenteReclutamiento);
	void deleteFuenteReclutamiento(FuenteReclutamiento fuenteReclutamiento);
	FuenteReclutamiento getFuenteReclutamientoById(Integer id);
	FuenteReclutamiento findByFuente(String fuenteReclutamiento);
}
