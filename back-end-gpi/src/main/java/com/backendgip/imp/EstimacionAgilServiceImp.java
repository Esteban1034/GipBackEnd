package com.backendgip.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.EstimacionAgil;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.repository.EstimacionAgilRepository;
import com.backendgip.service.EstimacionAgilService;

@Service
public class EstimacionAgilServiceImp implements EstimacionAgilService {

    @Autowired
    private EstimacionAgilRepository estimacionAgilRepository;

    @Override
    public EstimacionAgil save(EstimacionAgil estimacionAgil) {
        return estimacionAgilRepository.save(estimacionAgil);
    }

    @Override
    public List<EstimacionAgil> findAll() {
        return estimacionAgilRepository.findAll();
    }

    @Override
    public EstimacionAgil findById(Integer id) {
        Optional<EstimacionAgil> estimacionAgil = estimacionAgilRepository.findById(id);
        return estimacionAgil.orElse(null);
    }

    @Override
    public EstimacionAgil update(EstimacionAgil estimacionAgil) {
        if (estimacionAgil.getId() != null && estimacionAgilRepository.existsById(estimacionAgil.getId())) {
            return estimacionAgilRepository.save(estimacionAgil);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        estimacionAgilRepository.deleteById(id);
    }

    @Override
    public List<EstimacionAgil> findByEstimacion(EstimacionUfs estimacion) {
        return estimacionAgilRepository.findByEstimacion(estimacion);
    }
}
