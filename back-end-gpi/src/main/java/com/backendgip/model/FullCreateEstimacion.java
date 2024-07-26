package com.backendgip.model;

import java.util.List;

public class FullCreateEstimacion {
    private EstimacionUfs estimacionUfs;
    private List<UnidadFuncional> unidadFuncionales;
    private List<Funcion> funciones;
    private List<Subfuncion> subfunciones;

    public FullCreateEstimacion() {
    }
    
    public FullCreateEstimacion(EstimacionUfs estimacionUfs, List<UnidadFuncional> unidadFuncionales,
            List<Funcion> funciones, List<Subfuncion> subfunciones) {
        this.estimacionUfs = estimacionUfs;
        this.unidadFuncionales = unidadFuncionales;
        this.funciones = funciones;
        this.subfunciones = subfunciones;
    }


    public EstimacionUfs getEstimacionUfs() {
        return estimacionUfs;
    }
    public void setEstimacionUfs(EstimacionUfs estimacionUfs) {
        this.estimacionUfs = estimacionUfs;
    }
    public List<UnidadFuncional> getUnidadFuncionales() {
        return unidadFuncionales;
    }
    public void setUnidadFuncionales(List<UnidadFuncional> unidadFuncionales) {
        this.unidadFuncionales = unidadFuncionales;
    }
    public List<Funcion> getFunciones() {
        return funciones;
    }
    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }
    public List<Subfuncion> getSubfunciones() {
        return subfunciones;
    }
    public void setSubfunciones(List<Subfuncion> subfunciones) {
        this.subfunciones = subfunciones;
    }

    @Override
    public String toString() {
        return "FullCreateEstimacion [estimacionUfs=" + estimacionUfs + ", unidadFuncionales=" + unidadFuncionales
                + ", funciones=" + funciones + ", subfunciones=" + subfunciones + "]";
    }
}
