//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.exception.ResourceAlreadyExistsException;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.repository.MantenimientoUnidadRepository;
import com.backendgip.service.MantenimientoUnidadService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping({ "/api" })
public class MantenimientoUnidadController {
	@Autowired
	private MantenimientoUnidadService mantenimientounidadService;
	@Autowired
	private MantenimientoUnidadRepository mantenimientounidadRepository;
	private Object logService;

	public MantenimientoUnidadController() {
	}

	@GetMapping({ "/mantenimiento-unidad" })
	public List<MantenimientoUnidad> obtenerMantenimientoUnidad() {
		return this.mantenimientounidadService.obtenerMantenimientoUnidad();
	}

	@PostMapping({ "/mantenimiento-unidad" })
	public ResponseEntity<?> saveMantenimientoUnidad(@RequestBody MantenimientoUnidad mantenimientounidad) {
		LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
		MantenimientoUnidad createdMantenimientoUnidad = this.mantenimientounidadService
				.guardarMantenimientoUnidad(mantenimientounidad);
		LogSistema log = new LogSistema();
		log.setAccion("CREATE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setTabla(MantenimientoUnidad.class.toString());
		log.setIdAccion(createdMantenimientoUnidad.getId());
		log.setDescripcion(createdMantenimientoUnidad.toString());
		// this.logService.saveLog(log);
		return ResponseEntity.ok(createdMantenimientoUnidad);
	}
}
/*
 * @PutMapping({ "/clientes/{id}" })
 * public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody
 * Cliente clienteDetails) {
 * Cliente cliente = (Cliente)
 * this.clienteRepository.findById(id).orElseThrow(() -> {
 * return new ResourceNotFoundException("Cliente no existe con id: " + id);
 * });
 * if
 * (this.clienteRepository.existsByNomenclatura(clienteDetails.getNomenclatura()
 * )
 * && cliente.getId() != clienteDetails.getId()) {
 * return ResponseEntity.badRequest().body(new
 * ResourceAlreadyExistsException("Nomenclatura existente"));
 * } else {
 * LogSistema log = new LogSistema();
 * log.setAccion("UPDATE");
 * log.setDescripcion(cliente.toString());
 * log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
 * log.setIdAccion(cliente.getId());
 * log.setTabla(cliente.getClass().toString());
 * this.logService.saveLog(log);
 * cliente.setNit(clienteDetails.getNit());
 * cliente.setNombre(clienteDetails.getNombre());
 * cliente.setNomenclatura(clienteDetails.getNomenclatura());
 * cliente.setFechaCreacion(clienteDetails.getFechaCreacion());
 * EstadoCliente estado =
 * this.estadoService.getEstadoById(clienteDetails.getEstado().getId());
 * cliente.setEstado(estado);
 * SectorCliente sector =
 * this.sectorService.getSectorById(clienteDetails.getSector().getId());
 * cliente.setSector(sector);
 * Empleado director =
 * this.empleadoService.getEmpleadoById(clienteDetails.getGerenteCuenta().getId(
 * ));
 * cliente.setGerenteCuenta(director);
 * Cliente updatedCliente = this.clienteService.saveCliente(cliente);
 * return ResponseEntity.ok(updatedCliente);
 * }
 * }
 * 
 * @GetMapping({ "/clientes/{id}" })
 * public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
 * Cliente cliente = (Cliente)
 * this.clienteRepository.findById(id).orElseThrow(() -> {
 * return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
 * });
 * return ResponseEntity.ok(cliente);
 * }
 * 
 * @DeleteMapping({ "/clientes/{id}" })
 * public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
 * Cliente cliente = (Cliente)
 * this.clienteRepository.findById(id).orElseThrow(() -> {
 * return new ResourceNotFoundException("Cliente no exite con el id:" + id);
 * });
 * if (this.proyectoRepository.existsByCliente(cliente)) {
 * return ResponseEntity.badRequest()
 * .body("Cliente no se puede eliminar, Ya se encuentra asignado a uno o mas proyectos."
 * );
 * } else {
 * LogSistema log = new LogSistema();
 * log.setAccion("DELETE");
 * log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
 * log.setIdAccion(cliente.getId());
 * log.setDescripcion(cliente.toString());
 * log.setTabla(Cliente.class.toString());
 * this.logService.saveLog(log);
 * this.clienteService.deleteCliente(cliente);
 * Map<String, Boolean> response = new HashMap();
 * response.put("deleted", Boolean.TRUE);
 * return ResponseEntity.ok(response);
 * }
 * }
 */
