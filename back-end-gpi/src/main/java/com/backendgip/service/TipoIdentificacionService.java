package com.backendgip.service;

import com.backendgip.model.TipoIdentificacion;
import java.util.List;

public interface TipoIdentificacionService {
	List<TipoIdentificacion> getTipos();
	TipoIdentificacion saveTipo(TipoIdentificacion tipo);
	void deleteTipo(TipoIdentificacion tipo);
	TipoIdentificacion getTipoById(Integer id);
	TipoIdentificacion findByTipoAndAbreviatura(String tipo, String abreviatura);
}
