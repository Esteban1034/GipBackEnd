package com.backendgip.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendgip.repository.ContenidoUfsRepository;
import com.backendgip.repository.MantenimientoPesoHoraRepository;
import com.backendgip.repository.UfsRepository;
import com.backendgip.service.ContenidoUfsService;
import com.backendgip.service.EsfuerzoService;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.MantenimientoPesoHoraService;
import com.backendgip.service.MantenimientoUnidadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.LogSistema;
import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.model.TipoDocumento;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping( "/api" )
public class ContenidoUfsController {

	@Autowired
	ContenidoUfsRepository contenidoUfsRepository;
	@Autowired
	ContenidoUfsService contenidoUfsService;
	@Autowired
	MantenimientoUnidadService mantenimientoUnidadService;
	@Autowired
	private LogSistemaService logService;
	@Autowired
	private EsfuerzoService esfuerzoService;
	@Autowired
	MantenimientoPesoHoraService pesoHoraService;
	@Autowired
	MantenimientoPesoHoraRepository pesoHoraRepository;

	@Autowired
	UfsRepository ufsRepository;

	@GetMapping( "/contenido-ufs" )
	public List<ContenidoUfs> getContenidoUfs() {
		return contenidoUfsService.getContenidoUfs();
	}

	@GetMapping("/contenido-ufs/obtenerHoras/{peso}")
    public ResponseEntity<?> obtenerHoras(@PathVariable Integer peso) {
        MantenimientoPesoHora pesoMantenimiento = pesoHoraService.buscarPeso(peso);
        if (pesoMantenimiento != null) {
            return ResponseEntity.ok(pesoMantenimiento);
        } else {
			return ResponseEntity.badRequest().body("No se encuentra horas asignadas a esta complejidad");
        }
    }

	@GetMapping("/contenido-ufs/ultimo")
    public ResponseEntity<ContenidoUfs> getUltimoContenidoUfs() {
        ContenidoUfs ultimoContenido = contenidoUfsService.getUltimoContenidoUfs();
        if (ultimoContenido != null) {
            return ResponseEntity.ok(ultimoContenido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/contenido-ufs/ultimo-id-estimacion")
    public List<ContenidoUfs> getContenidoUfsByUltimoIdEstimacion() {
        return contenidoUfsService.getContenidoUfsByUltimoIdEstimacion();
    }

	
	
	@PostMapping("/contenido-ufs")
    public ResponseEntity<?> saveContenidoUfs(@RequestBody ContenidoUfs contenidoUfs) {
    try {
        // Validación básica de datos de entrada
        if (contenidoUfs == null || contenidoUfs.getId() == null) {
            return ResponseEntity.badRequest().body("El contenido de UFS o su ID no pueden ser nulos");
        }

        // Verifica si el contenidoUfs ya existe para decidir si es una actualización o inserción
        boolean exists = this.contenidoUfsRepository.existsById(contenidoUfs.getId());

        // Verificar si existe el ufs asociado (si aplica)
        if (contenidoUfs.getUfs() != null && !this.ufsRepository.existsById(contenidoUfs.getUfs().getId())) {
            return ResponseEntity.badRequest().body("No existe ufs con este ID: " + contenidoUfs.getUfs().getId());
        }

        // Guardar o actualizar según corresponda
        ContenidoUfs updatedContenidoUfs;
        if (exists) {
            updatedContenidoUfs = this.contenidoUfsService.saveContenidoUfs(contenidoUfs); // Actualización
        } else {
            updatedContenidoUfs = this.contenidoUfsRepository.save(contenidoUfs); // Inserción
        }

        // Registra el log de sistema para la acción realizada
        LogSistema log = new LogSistema();
        log.setAccion(exists ? "UPDATE" : "INSERT");
        log.setFechaHora(new Date());
        log.setTabla(ContenidoUfs.class.toString());
        log.setIdAccion(updatedContenidoUfs.getId());
        log.setDescripcion(updatedContenidoUfs.toString());
        this.logService.saveLog(log);

        // Devuelve respuesta con el contenidoUfs actualizado o insertado correctamente
        return ResponseEntity.ok(updatedContenidoUfs);
    } catch (Exception e) {
        e.printStackTrace(); // Imprime la excepción para depuración
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error al guardar o actualizar el contenido de UFS: " + e.getMessage());
    }
}

	@PutMapping( "/contenido-ufs/{id}" )
	public ResponseEntity<?> updateContenidoUfs(@PathVariable Integer id,
			@RequestBody ContenidoUfs contenidoUfsDetails) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("La unidad Funcional no existe con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(contenidoUfs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(contenidoUfs.getId());
		log.setTabla(contenidoUfs.getClass().toString());
		this.logService.saveLog(log);
		contenidoUfs.setSubfuncion(contenidoUfsDetails.getSubfuncion());
		contenidoUfs.setNombreCaso(contenidoUfsDetails.getNombreCaso());
		contenidoUfs.setPorcentajeConstruccion(contenidoUfsDetails.getPorcentajeConstruccion());
		contenidoUfs.setPorcentajeDiseno(contenidoUfsDetails.getPorcentajeDiseno());
		contenidoUfs.setPorcentajePruebas(contenidoUfsDetails.getPorcentajePruebas());
		contenidoUfs.setUfs(contenidoUfsDetails.getUfs());
		contenidoUfs.setEstimacionUfs(contenidoUfsDetails.getEstimacionUfs());


		ContenidoUfs updatedContenidoUfs = this.contenidoUfsService.saveContenidoUfs(contenidoUfs);
		return ResponseEntity.ok(updatedContenidoUfs);
	}

	@DeleteMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<?> deleteContenidoUfs(@PathVariable Integer id) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("La función no exite con el id:" + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("DELETE");
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(contenidoUfs.getId());
		log.setDescripcion(contenidoUfs.toString());
		log.setTabla(ContenidoUfs.class.toString());
		this.logService.saveLog(log);
		this.contenidoUfsRepository.deleteById(contenidoUfs.getId());
		Map<String, Boolean> response = new HashMap();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping({ "/contenido-ufs/{id}" })
	public ResponseEntity<ContenidoUfs> getContenidoUfsById(@PathVariable Integer id) {
		ContenidoUfs contenidoUfs = (ContenidoUfs) this.contenidoUfsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("el ID " + id + " no existe");
		});
		return ResponseEntity.ok(contenidoUfs);
	}

}