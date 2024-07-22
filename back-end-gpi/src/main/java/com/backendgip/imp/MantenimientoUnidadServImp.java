package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Cliente;
import com.backendgip.model.EstadoCliente;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.repository.MantenimientoUnidadRepository;
import com.backendgip.service.MantenimientoUnidadService;

@Service
public class MantenimientoUnidadServImp implements MantenimientoUnidadService {

    @Autowired
    private MantenimientoUnidadRepository mantenimineMantenimientoUnidadRepository;

    public List<MantenimientoUnidad> getMantenimientos(){
        return (List<MantenimientoUnidad>) mantenimineMantenimientoUnidadRepository.findAll();
    }

    public MantenimientoUnidad saveMantenimientoUnd(MantenimientoUnidad mantenimientoUnidad) {
		return (MantenimientoUnidad) this.mantenimineMantenimientoUnidadRepository.save(mantenimientoUnidad);
	  }

    public MantenimientoUnidad getMantenimientoUndById(Integer idMantenimientoUnd) {
		return (MantenimientoUnidad) this.mantenimineMantenimientoUnidadRepository.findById(idMantenimientoUnd).orElseThrow(() -> {
			return new ResourceNotFoundException("No se ha encontrado el estado con el id:" + idMantenimientoUnd);
		});
	}

    @Override
    public boolean validarNombre(String nombre) {
      MantenimientoUnidad mantenimiento = this.mantenimineMantenimientoUnidadRepository.findByNombre(nombre);
      if (mantenimiento != null) {
        return true;
      }else{
        return false;
      }
    }

    @Override
    public void deleteById(Integer id) {
      this.mantenimineMantenimientoUnidadRepository.deleteById(id);
    }

    @Override
    public MantenimientoUnidad findByPeso(Integer peso) {
      return this.mantenimineMantenimientoUnidadRepository.findByPeso(peso);
    }
}
