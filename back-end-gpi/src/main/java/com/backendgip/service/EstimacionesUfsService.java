//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;
import com.backendgip.model.ContenidoUfs;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.model.FullCreateEstimacion;
import com.backendgip.model.UnidadFuncional;

import java.util.List;

public interface EstimacionesUfsService {

    EstimacionUfs saveCreacionCompleta(FullCreateEstimacion dto);

    EstimacionUfs getEstimacionById(Integer id);
    
	List<EstimacionUfs> getEstimaciones();  

    EstimacionUfs saveEstimacionIn(EstimacionUfs estimacionesUfs);

    void deleteEstimaciones(EstimacionUfs estimacionesUfs);
    
    EstimacionUfs getUltimaEstimacionUfs();

    boolean existsByProyectoId(Integer proyectoId);
 

}
