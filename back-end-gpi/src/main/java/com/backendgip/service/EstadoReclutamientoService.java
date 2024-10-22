package com.backendgip.service;

import com.backendgip.model.EstadoReclutamiento;
import java.util.List;

public interface EstadoReclutamientoService {
	List<EstadoReclutamiento> getEstadoReclutamientoes();
	EstadoReclutamiento saveEstadoReclutamiento(EstadoReclutamiento estadoReclutamiento);
	void deleteEstadoReclutamiento(EstadoReclutamiento estadoReclutamiento);
	EstadoReclutamiento getEstadoReclutamientoById(Integer id);
	EstadoReclutamiento findByEstado(String estado);
}
