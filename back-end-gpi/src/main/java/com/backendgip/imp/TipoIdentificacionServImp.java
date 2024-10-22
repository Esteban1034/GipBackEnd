//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Funcion;
import com.backendgip.model.Subfuncion;
import com.backendgip.model.TipoIdentificacion;
import com.backendgip.repository.TipoIdentificacionRepository;
import com.backendgip.service.TipoIdentificacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoIdentificacionServImp implements TipoIdentificacionService {
	@Autowired
	private TipoIdentificacionRepository tipoRepository;

	public TipoIdentificacionServImp() {
	}

	public List<TipoIdentificacion> getTipos() {
		return (List) this.tipoRepository.findAll();
	}

	public TipoIdentificacion saveTipo(TipoIdentificacion tipo) {
		return (TipoIdentificacion) this.tipoRepository.save(tipo);
	}

	public void deleteTipo(TipoIdentificacion tipo) {
		this.tipoRepository.delete(tipo);
	}

	public TipoIdentificacion getTipoById(Integer id) {
		return (TipoIdentificacion) this.tipoRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el recurso solicitado.");
		});
	}

	@Override
	public TipoIdentificacion findByTipoAndAbreviatura(String tipo, String abreviatura) {
		return this.tipoRepository.findByTipoAndAbreviatura(tipo, abreviatura);
	}
}
