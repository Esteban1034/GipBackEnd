package com.backendgip.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Esfuerzo;
import com.backendgip.repository.EsfuerzoRepository;
import com.backendgip.service.EsfuerzoService;

@Service
public class EsfuerzoServImp implements EsfuerzoService {
    @Autowired EsfuerzoRepository esfuerzoRepository;

    public Esfuerzo getEsfuerzoById(Integer idEsfuerzo) {
		return (Esfuerzo) this.esfuerzoRepository.findById(idEsfuerzo).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el estado con el id:" + idEsfuerzo);
		});
	}
}
