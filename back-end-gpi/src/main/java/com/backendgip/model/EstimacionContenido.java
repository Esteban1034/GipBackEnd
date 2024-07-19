package com.backendgip.model;

import java.io.Serializable;
import java.util.List;

public class EstimacionContenido  implements Serializable{

    public EstimacionUfs estimacion;
    public List<ActividadesComplementarias> actividades;
    public List<Subfuncion> subFuncion;
    public List<Funcion> funcion;
    public List<UnidadFuncional> unidadFuncional;
    
    
    public EstimacionContenido() {
    }

    public EstimacionContenido(EstimacionUfs estimacion, List<ActividadesComplementarias> actividades,
            List<Subfuncion> subFuncion, List<Funcion> funcion, List<UnidadFuncional> unidadFuncional) {
        this.estimacion = estimacion;
        this.actividades = actividades;
        this.subFuncion = subFuncion;
        this.funcion = funcion;
        this.unidadFuncional = unidadFuncional;
    }

    public EstimacionUfs getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(EstimacionUfs estimacion) {
        this.estimacion = estimacion;
    }

    public List<ActividadesComplementarias> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadesComplementarias> actividades) {
        this.actividades = actividades;
    }

    public List<Subfuncion> getSubFuncion() {
        return subFuncion;
    }

    public void setSubFuncion(List<Subfuncion> subFuncion) {
        this.subFuncion = subFuncion;
    }

    public List<Funcion> getFuncion() {
        return funcion;
    }

    public void setFuncion(List<Funcion> funcion) {
        this.funcion = funcion;
    }

    public List<UnidadFuncional> getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(List<UnidadFuncional> unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }

    @Override
    public String toString() {
        return "EstimacionContenido [estimacion=" + estimacion + ", actividades=" + actividades + ", subFuncion="
                + subFuncion + ", funcion=" + funcion + ", unidadFuncional=" + unidadFuncional + "]";
    }

    
    
}
