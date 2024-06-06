//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;
import com.backendgip.model.EstimacionUfs;
import java.util.List;

public interface EstimacionesUfsService {
	List<EstimacionUfs> getEstimaciones();  

    EstimacionUfs saveEstimacionIn(EstimacionUfs estimacionesUfs);

    EstimacionUfs saveEstimaciones(EstimacionUfs estimacionesUfs);
    void deleteEstimaciones(EstimacionUfs estimacionesUfs);
}
