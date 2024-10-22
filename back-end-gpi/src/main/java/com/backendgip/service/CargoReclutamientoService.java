package com.backendgip.service;

import com.backendgip.model.CargoReclutamiento;
import java.util.List;

public interface CargoReclutamientoService {
	List<CargoReclutamiento> getCargoReclutamientoes();
	CargoReclutamiento saveCargoReclutamiento(CargoReclutamiento cargoReclutamiento);
	void deleteCargoReclutamiento(CargoReclutamiento cargoReclutamiento);
	CargoReclutamiento getCargoReclutamientoById(Integer id);
	CargoReclutamiento findByCargo(String cargo);
}
