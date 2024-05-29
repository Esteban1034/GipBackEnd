//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.repository.EstimacionUfsRepository;
import com.backendgip.service.EstimacionUfsService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimacionUfsServiceImp implements EstimacionUfsService  {
	@Autowired
	private EstimacionUfsRepository estimacionUfsRepository;

	public EstimacionUfsServiceImp() {
	}

	public List<EstimacionUfs> getEstimacionUfs() {
		return (List) this.estimacionUfsRepository.findAll();
	}

	public EstimacionUfs saveEstimacionUfs(EstimacionUfs estimacionUfs) {
		return (EstimacionUfs) this.estimacionUfsRepository.save(estimacionUfs);
	}

	public void deleteEstimacionUfs(EstimacionUfs estimacionUfs) {
		this.estimacionUfsRepository.delete(estimacionUfs);
	}

	public EstimacionUfs getEstimacionUfsById(Integer idEstimacionUfs) {
		return (EstimacionUfs) this.estimacionUfsRepository.findById(idEstimacionUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("Estimacion no existe con el ID" + idEstimacionUfs);
		});
	}

}
