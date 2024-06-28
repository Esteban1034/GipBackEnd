package com.backendgip.service;

import java.util.List;

import com.backendgip.model.ContenidoUfs;

public interface ContenidoUfsService {

    List<ContenidoUfs> getContenidoUfs();

    ContenidoUfs saveContenidoUfs(ContenidoUfs contenidoUfs);

    void deleteContenidoUfs(ContenidoUfs contenidoUfs);

    ContenidoUfs getContenidoUfsById(Integer idContenidoUfs);
    List<ContenidoUfs> getContenidoUfsByUltimoIdEstimacion();


    ContenidoUfs getUltimoContenidoUfs();

    boolean existsById(Integer integer);

    Long findMaxId();

}
