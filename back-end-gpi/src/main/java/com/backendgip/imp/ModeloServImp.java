package com.backendgip.imp;


import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstadoProyecto;
import com.backendgip.model.Modelo;
import com.backendgip.repository.ModeloRepository;
import com.backendgip.service.ModeloService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeloServImp implements ModeloService{
    @Autowired
	private ModeloRepository ModeloRepository;

    public ModeloServImp() {
	}

    public List<Modelo> getNombresList() {
		return (List) this.ModeloRepository.findAll();
	}
    
}
