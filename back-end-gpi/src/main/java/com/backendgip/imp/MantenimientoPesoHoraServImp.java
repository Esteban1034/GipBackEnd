package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.model.MantenimientoPesoHora;
import com.backendgip.model.MantenimientoUnidad;
import com.backendgip.repository.MantenimientoPesoHoraRepository;
import com.backendgip.service.MantenimientoPesoHoraService;

@Service
public class MantenimientoPesoHoraServImp implements MantenimientoPesoHoraService {
    
    @Autowired
    private MantenimientoPesoHoraRepository mantenimientoPesoHoraRepository;

    public List<MantenimientoPesoHora> getPesoHora(){
        return (List<MantenimientoPesoHora>) this.mantenimientoPesoHoraRepository.findAll();
    }

    public MantenimientoPesoHora saveMantenimientoPesoHora(MantenimientoPesoHora mantenimientoPesoHora) {
		return (MantenimientoPesoHora) this.mantenimientoPesoHoraRepository.save(mantenimientoPesoHora);
	}
}
