package com.backendgip.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.EsfuerzoAgil;
import com.backendgip.model.EstimacionUfs;
import com.backendgip.repository.EsfuerzoAgilRepository;
import com.backendgip.service.EsfuerzoAgilService;

@Service
public class EsfuerzoAgilServiceImp implements EsfuerzoAgilService {

    @Autowired
    private EsfuerzoAgilRepository esfuerzoAgilRepository;

    @Override
    public EsfuerzoAgil save(EsfuerzoAgil esfuerzoAgil) {
        return esfuerzoAgilRepository.save(esfuerzoAgil);
    }

    @Override
    public List<EsfuerzoAgil> findAll() {
        return esfuerzoAgilRepository.findAll();
    }

    @Override
    public EsfuerzoAgil findById(Integer id) {
        Optional<EsfuerzoAgil> esfuerzoAgil = esfuerzoAgilRepository.findById(id);
        return esfuerzoAgil.orElse(null);
    }

    @Override
    public EsfuerzoAgil update(EsfuerzoAgil esfuerzoAgil) {
        if (esfuerzoAgil.getId() != null && esfuerzoAgilRepository.existsById(esfuerzoAgil.getId())) {
            return esfuerzoAgilRepository.save(esfuerzoAgil);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        esfuerzoAgilRepository.deleteById(id);
    }

    @Override
    public List<EsfuerzoAgil> findByEstimacion(EstimacionUfs estimacion) {
        return esfuerzoAgilRepository.findByEstimacion(estimacion);
    }
}
