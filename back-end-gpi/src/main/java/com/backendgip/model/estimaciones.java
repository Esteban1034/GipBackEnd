package com.backendgip.model;

public class estimaciones {
    private Long id; // Campo de identificación único

    private Proyecto proyecto;
    private Cliente cliente;
    private String estadoestimacion;

    public estimaciones(Proyecto proyecto, Cliente cliente, String estadoestimacion) {
        this.proyecto = proyecto;
        this.cliente = cliente;
        this.estadoestimacion = estadoestimacion;
    }

    // Getters y setters para el campo de identificación único
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstadoestimacion() {
        return estadoestimacion;
    }

    public void setEstadoestimacion(String estadoestimacion) {
        this.estadoestimacion = estadoestimacion;
    }
}
