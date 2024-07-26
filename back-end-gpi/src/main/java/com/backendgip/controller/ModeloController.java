package com.backendgip.controller;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Modelo;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.LogSistema;
import com.backendgip.repository.ModeloRepository;
import com.backendgip.service.ModeloService;
import com.backendgip.service.LogSistemaService;
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
public class ModeloController {
    @Autowired
	private ModeloService modeloService;
    
    public ModeloController(){
    }

    @GetMapping({ "/modelo" })
	public List<Modelo> getModelos() {
		return this.modeloService.getNombresList();
	}
}