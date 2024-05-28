//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Estimaciones;
import com.backendgip.repository.EstimacionesRepository;
import com.backendgip.service.EstimacionesService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimacionesServiceImp implements EstimacionesService {
	
	
	@Autowired
	private EstimacionesRepository estimacionesRepository;

	public EstimacionesServiceImp() {
	}

	public List<Estimaciones> getEstimaciones() {
		return (List) this.estimacionesRepository.findAll();}

	

	public Estimaciones saveEstimaciones(Estimaciones estimaciones) {
		        return estimacionesRepository.save(estimaciones);  }

	public void deleteEstimaciones(Estimaciones estimaciones) {
				this.estimacionesRepository.delete(estimaciones);}

	


}
