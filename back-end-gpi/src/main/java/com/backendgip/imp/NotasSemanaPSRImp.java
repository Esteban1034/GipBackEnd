package com.backendgip.imp;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.model.NotasSemanaPSR;
import com.backendgip.repository.NotasSemanaPSRRepository;
import com.backendgip.service.NotasSemanaPSRService;


@Service
public class NotasSemanaPSRImp  implements NotasSemanaPSRService {

    @Autowired
	private NotasSemanaPSRRepository notasSemanaPSRRepository;


  @Override
  public NotasSemanaPSR saveNotaSemanalPsr(NotasSemanaPSR notasSemanaPSR) {
    return (NotasSemanaPSR) this.notasSemanaPSRRepository.save(notasSemanaPSR);
  }

  @Override
  public List<NotasSemanaPSR> getNotaSemanalPsr() {
    return (List<NotasSemanaPSR>) this.notasSemanaPSRRepository.findAll();

  }

  @Override
    public List<NotasSemanaPSR> getNotasSemanalFechaInicialFechaFinal(LocalDate fechaI, LocalDate fechaF){ 
      return (List<NotasSemanaPSR>) this.notasSemanaPSRRepository.findByFechaCreacionBetween(fechaI,fechaF);
  }

  @Override
  public NotasSemanaPSR getNotaSemanalFechaCreacion(LocalDate fechaC){ 
    return (NotasSemanaPSR) this.notasSemanaPSRRepository.findByFechaCreacion(fechaC);
}

}


