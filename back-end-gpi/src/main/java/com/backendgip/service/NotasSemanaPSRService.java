package com.backendgip.service;

import java.time.LocalDate;
import java.util.List;

import com.backendgip.model.NotasSemanaPSR;

public interface NotasSemanaPSRService {

      public NotasSemanaPSR saveNotaSemanalPsr(NotasSemanaPSR notasSemanaPSR);
      public List<NotasSemanaPSR> getNotaSemanalPsr();
      public List<NotasSemanaPSR> getNotasSemanalFechaInicialFechaFinal(LocalDate fechaI, LocalDate fechaF);
      public NotasSemanaPSR getNotaSemanalFechaCreacion(LocalDate fechaC);

}
