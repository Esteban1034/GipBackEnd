//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;

import com.backendgip.model.EstimacionUfs;
import java.time.LocalDate;
import java.util.List;

public interface EstimacionUfsService {
	List<EstimacionUfs> getEstimacionUfs();

	EstimacionUfs saveEstimacionUfs(EstimacionUfs estimacionUfs);

	void deleteEstimacionUfs(EstimacionUfs estimacionUfs);

	EstimacionUfs getEstimacionUfsById(Integer idEstimacionUfs);

}
