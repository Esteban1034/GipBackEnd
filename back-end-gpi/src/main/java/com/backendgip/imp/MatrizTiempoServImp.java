package com.backendgip.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.service.MatrizTiempoService;
import com.backendgip.model.GeneracionMatrizTiempo;
import com.backendgip.model.Generarmatriztiemposasignacionrecursos;
import com.backendgip.model.MatrizTiempo;
import com.backendgip.model.ProjectStatusReport;
import com.backendgip.repository.GeneracionMatrizTiempoRepository;
import com.backendgip.repository.GeneracionmatriztiemposrecursosRepository;
import com.backendgip.repository.MatrizTiempoRepository;

@Service
public class MatrizTiempoServImp implements MatrizTiempoService {
  @Autowired
  private MatrizTiempoRepository matrizTiempoRepository;
  private GeneracionMatrizTiempoRepository generacionMatrizTiempoRepository;
  private GeneracionmatriztiemposrecursosRepository generacionmatriztiemposrecursosRepository;

  @Override
  public MatrizTiempo saveMatrizTiempo(MatrizTiempo matrizTiempo) {
    return (MatrizTiempo) this.matrizTiempoRepository.save(matrizTiempo);
  }

  @Override
  public List<MatrizTiempo> getMatrizTiempo() {
    return (List<MatrizTiempo>) this.matrizTiempoRepository.findAll();

  }

  @Override
  public void deleteMatrizById(MatrizTiempo matriz) {
      this.matrizTiempoRepository.delete(matriz);
  }

}