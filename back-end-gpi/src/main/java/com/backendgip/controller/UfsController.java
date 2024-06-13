package com.backendgip.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.LogSistema;
import com.backendgip.model.Ufs;
import com.backendgip.repository.UfsRepository;
import com.backendgip.service.LogSistemaService;
import com.backendgip.service.UfsService;

@RestController
@Transactional
@RequestMapping({ "/api" })
public class UfsController {

    @Autowired
    private UfsService ufsService;
    @Autowired
	private LogSistemaService logService;
    @Autowired
    private UfsRepository ufsRepository;
    
    public UfsController(){
    }

    @GetMapping({"/unidad-funcional"})
    public List<Ufs> getUfs(){
        return this.ufsService.getUfs();
    }

    @PostMapping({"/unidad-funcional"})
    public ResponseEntity<?> saveUfs(@RequestBody Ufs ufs){
        if(this.ufsRepository.existsByNombre(ufs.getNombre()) /*|| this.ufsRepository.existsById(ufs.getId())*/){
            return ResponseEntity.badRequest().body("Esta Unidad funcional ya existe");
        }else{
            LocalDate fechaCreacion = LocalDate.now(ZoneId.of("America/Bogota"));
			Ufs createdUfs = this.ufsService.saveUfs(ufs);
			LogSistema log = new LogSistema();
			log.setAccion("CREATE");
			log.setFechaHora(new Date());
			log.setTabla(Ufs.class.toString());
			log.setIdAccion(createdUfs.getId());
			log.setDescripcion(createdUfs.toString());
			this.logService.saveLog(log);
			return ResponseEntity.ok(createdUfs);
        }
    }

    @PutMapping({ "/unidad-funcional/{id}" })
	public ResponseEntity<?> updateUfs(@PathVariable Integer id, @RequestBody Ufs UfsDetails) {
		Ufs ufs = (Ufs) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado la unidad funcional con el id: " + id);
		});
		LogSistema log = new LogSistema();
		log.setAccion("UPDATE");
		log.setDescripcion(ufs.toString());
		log.setFechaHora(new Date(Calendar.getInstance().getTime().getTime()));
		log.setIdAccion(ufs.getId());
		log.setTabla(ufs.getClass().toString());
		this.logService.saveLog(log);
		ufs.setNombre(UfsDetails.getNombre());
		Ufs updatedUfs = this.ufsService.saveUfs(ufs);
		return ResponseEntity.ok(updatedUfs);
	}

    @GetMapping({ "/unidad-funcional/{id}" })
	public ResponseEntity<Ufs> getUfsById(@PathVariable Integer id) {
		Ufs ufs = (Ufs) this.ufsRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("ID " + id + " NO ENCONTRADO");
		});
		return ResponseEntity.ok(ufs);
	}
}
