//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.repository;

import com.backendgip.model.Actividad;
import com.backendgip.model.ActividadAsignada;
import com.backendgip.model.Cliente;
import com.backendgip.model.Estimaciones;
import com.backendgip.model.Proyecto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimacionesRepository  extends JpaRepository<Estimaciones, Integer> {
}