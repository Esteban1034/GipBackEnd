package com.backendgip.service;

import java.util.List;

import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.EstadoCliente;

public interface ContenidoUfsService {

    List<ContenidoUfs> getContenidoUfs();

    ContenidoUfs saveContenidoUfs(ContenidoUfs contenidoUfs);

    void deleteContenidoUfs(ContenidoUfs contenidoUfs);

    ContenidoUfs getContenidoUfsById(Integer idContenidoUfs);

}
