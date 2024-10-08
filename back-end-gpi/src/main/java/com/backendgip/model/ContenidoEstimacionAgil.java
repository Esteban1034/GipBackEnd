package com.backendgip.model;

import java.io.Serializable;
import java.util.List;

public class ContenidoEstimacionAgil implements Serializable{
    
    public EstimacionUfs estimacion;
    public List<EstimacionAgil> estimacionAgil;
    public List<EsfuerzoAgil> esfuerzoAgil;
    public List<ActividadesComplementarias> actividades;
    
    public ContenidoEstimacionAgil() {
    }

    public ContenidoEstimacionAgil(EstimacionUfs estimacion, List<EstimacionAgil> estimacionAgil,
            List<EsfuerzoAgil> esfuerzoAgil, List<ActividadesComplementarias> actividades) {
        this.estimacion = estimacion;
        this.estimacionAgil = estimacionAgil;
        this.esfuerzoAgil = esfuerzoAgil;
        this.actividades = actividades;
    }

    
    
    public EstimacionUfs getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(EstimacionUfs estimacion) {
        this.estimacion = estimacion;
    }

    public List<EstimacionAgil> getEstimacionAgil() {
        return estimacionAgil;
    }

    public void setEstimacionAgil(List<EstimacionAgil> estimacionAgil) {
        this.estimacionAgil = estimacionAgil;
    }

    public List<EsfuerzoAgil> getEsfuerzoAgil() {
        return esfuerzoAgil;
    }

    public void setEsfuerzoAgil(List<EsfuerzoAgil> esfuerzoAgil) {
        this.esfuerzoAgil = esfuerzoAgil;
    }

    public List<ActividadesComplementarias> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadesComplementarias> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "EstimacionContenidoAgil [estimacion=" + estimacion + ", estimacionAgil=" + estimacionAgil
                + ", esfuerzoAgil=" + esfuerzoAgil + ", actividades=" + actividades + "]";
    }

}
