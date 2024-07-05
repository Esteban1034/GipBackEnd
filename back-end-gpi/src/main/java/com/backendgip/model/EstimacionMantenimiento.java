package com.backendgip.model;

public class EstimacionMantenimiento {

    private UnidadFuncional unidadFuncional;
    private MantenimientoUnidad mantenimientoUnidad ;
    private MantenimientoPesoHora mantenimientoPesoHora;
    private Integer cantidadUnidad;
    private Integer cantidadHora;

    public EstimacionMantenimiento() {
    }

    public EstimacionMantenimiento(UnidadFuncional unidadFuncional, MantenimientoUnidad mantenimientoUnidad,
            MantenimientoPesoHora mantenimientoPesoHora, Integer cantidadUnidad, Integer cantidadHora) {
        this.unidadFuncional = unidadFuncional;
        this.mantenimientoUnidad = mantenimientoUnidad;
        this.mantenimientoPesoHora = mantenimientoPesoHora;
        this.cantidadUnidad = cantidadUnidad;
        this.cantidadHora = cantidadHora;
    }

    public UnidadFuncional getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(UnidadFuncional unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }

    public MantenimientoUnidad getMantenimientoUnidad() {
        return mantenimientoUnidad;
    }

    public void setMantenimientoUnidad(MantenimientoUnidad mantenimientoUnidad) {
        this.mantenimientoUnidad = mantenimientoUnidad;
    }

    public MantenimientoPesoHora getMantenimientoPesoHora() {
        return mantenimientoPesoHora;
    }

    public void setMantenimientoPesoHora(MantenimientoPesoHora mantenimientoPesoHora) {
        this.mantenimientoPesoHora = mantenimientoPesoHora;
    }

    public Integer getCantidadUnidad() {
        return cantidadUnidad;
    }

    public void setCantidadUnidad(Integer cantidadUnidad) {
        this.cantidadUnidad = cantidadUnidad;
    }

    public Integer getCantidadHora() {
        return cantidadHora;
    }

    public void setCantidadHora(Integer cantidadHora) {
        this.cantidadHora = cantidadHora;
    }


    @Override
    public String toString() {
        return "EstimacionMantenimiento [unidadFuncional=" + unidadFuncional + ", mantenimientoUnidad="
                + mantenimientoUnidad + ", mantenimientoPesoHora=" + mantenimientoPesoHora + ", cantidadUnidad="
                + cantidadUnidad + ", cantidadHora=" + cantidadHora + "]";
    }
    
}
