//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.CargoReclutamiento;
import com.backendgip.repository.CargoReclutamientoRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.CargoReclutamientoService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping({ "/api/reclutamiento" })
public class CargoReclutamientoController {
	@Autowired
	private CargoReclutamientoService cargoReclutamientoService;
	@Autowired
	private CargoReclutamientoRepository cargoReclutamientoRepository;
	@Autowired
	private LogSistemaService logService;

	public CargoReclutamientoController() {
	}

	@GetMapping({ "/cargos" })
	public List<CargoReclutamiento> getAllCargoReclutamientoes() {
		return this.cargoReclutamientoService.getCargoReclutamientoes();
	}

	@PostMapping({ "/cargos" })
	public ResponseEntity<?> saveCargoReclutamiento(@RequestBody CargoReclutamiento cargoReclutamiento) {
		CargoReclutamiento existingCargoReclutamiento = this.cargoReclutamientoService.findByCargo(cargoReclutamiento.getCargo());
		if (existingCargoReclutamiento != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El cargo ya existe: " + cargoReclutamiento.getCargo());
		}

		CargoReclutamiento createdCargoReclutamiento = this.cargoReclutamientoService.saveCargoReclutamiento(cargoReclutamiento);

		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date());
		log.setTabla(CargoReclutamiento.class.toString());
		log.setIdAccion(createdCargoReclutamiento.getId());
		log.setDescripcion(createdCargoReclutamiento.toString());
		this.logService.saveLog(log);

		return ResponseEntity.ok(createdCargoReclutamiento);
	}

	@PutMapping({ "/cargos/{id}" })
	public ResponseEntity<?> updateCargoReclutamiento(@PathVariable Integer id,
			@RequestBody CargoReclutamiento cargoReclutamientoDetails) {
		CargoReclutamiento cargoReclutamiento = this.cargoReclutamientoRepository.findById(id).orElse(null);

		if (cargoReclutamiento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		CargoReclutamiento existingCargoReclutamiento = this.cargoReclutamientoService.findByCargo(cargoReclutamientoDetails.getCargo());
		if (existingCargoReclutamiento != null && !existingCargoReclutamiento.getId().equals(cargoReclutamiento.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("El cargo ya existe: " + cargoReclutamientoDetails.getCargo());
		}

		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(cargoReclutamiento.toString());
		log.setFechaHora(new Date());
		log.setIdAccion(cargoReclutamiento.getId());
		log.setTabla(cargoReclutamiento.getClass().toString());
		this.logService.saveLog(log);
		cargoReclutamiento.setCargo(cargoReclutamientoDetails.getCargo());
		cargoReclutamiento.setDescripcion(cargoReclutamientoDetails.getDescripcion());
		CargoReclutamiento updatedCargoReclutamiento = this.cargoReclutamientoService.saveCargoReclutamiento(cargoReclutamiento);

		return ResponseEntity.ok(updatedCargoReclutamiento);
	}

	@GetMapping({ "/cargos/{id}" })
	public ResponseEntity<CargoReclutamiento> getCargoReclutamientoById(@PathVariable Integer id) {
		CargoReclutamiento cargoReclutamiento = (CargoReclutamiento) this.cargoReclutamientoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado" + id);
		});
		return ResponseEntity.ok(cargoReclutamiento); 
	}

	@DeleteMapping({ "/cargos/{id}" })
	public ResponseEntity<?> deleteCargoReclutamiento(@PathVariable Integer id) {
		CargoReclutamiento cargoReclutamiento = this.cargoReclutamientoRepository.findById(id).orElse(null);

		if (cargoReclutamiento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se ha encontrado el recurso solicitado: " + id);
		}

		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date());
		log.setIdAccion(cargoReclutamiento.getId());
		log.setDescripcion(cargoReclutamiento.toString());
		log.setTabla(CargoReclutamiento.class.toString());
		this.logService.saveLog(log);

		this.cargoReclutamientoRepository.deleteById(cargoReclutamiento.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
