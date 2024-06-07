package com.backendgip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendgip.model.Esfuerzo;

@Repository
public interface EsfuerzoRepository extends CrudRepository<Esfuerzo, Integer> {

}
