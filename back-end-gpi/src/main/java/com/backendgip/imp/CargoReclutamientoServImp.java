//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.CargoReclutamiento;
import com.backendgip.repository.CargoReclutamientoRepository;
import com.backendgip.service.CargoReclutamientoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoReclutamientoServImp implements CargoReclutamientoService {
	@Autowired
	private CargoReclutamientoRepository cargoReclutamientoRepository;

	public CargoReclutamientoServImp() {
	}

	public List<CargoReclutamiento> getCargoReclutamientoes() {
		return (List) this.cargoReclutamientoRepository.findAll();
	}

	public CargoReclutamiento saveCargoReclutamiento(CargoReclutamiento cargoReclutamiento) {
		return (CargoReclutamiento) this.cargoReclutamientoRepository.save(cargoReclutamiento);
	}

	public void deleteCargoReclutamiento(CargoReclutamiento cargoReclutamiento) {
		this.cargoReclutamientoRepository.delete(cargoReclutamiento);
	}

	public CargoReclutamiento getCargoReclutamientoById(Integer id) {
		return (CargoReclutamiento) this.cargoReclutamientoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	@Override
	public CargoReclutamiento findByCargo(String cargo) {
		return this.cargoReclutamientoRepository.findByCargo(cargo);
	}
}
