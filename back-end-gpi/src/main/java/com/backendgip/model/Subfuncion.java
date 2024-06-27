package com.backendgip.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "subfuncion")
public class Subfuncion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_funcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "FK_funciones")
    private Funcion funcion;

    @ManyToOne
    @JoinColumn(name = "FK_mantenimientos")
    private MantenimientoUnidad mantenimientoUnidad;

    // Constructor sin argumentos
    public Subfuncion() {
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public MantenimientoUnidad getMantenimientoUnidad() {
        return mantenimientoUnidad;
    }

    public void setMantenimientoUnidad(MantenimientoUnidad mantenimientoUnidad) {
        this.mantenimientoUnidad = mantenimientoUnidad;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Subfuncion(Integer id, String descripcion, Funcion funcion, MantenimientoUnidad mantenimientoUnidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.funcion = funcion;
        this.mantenimientoUnidad = mantenimientoUnidad;
    }

    @Override
    public String toString() {
        return "Subfuncion [id=" + id + ", descripcion=" + descripcion + ", funcion=" + funcion
                + ", mantenimientoUnidad=" + mantenimientoUnidad + "]";
    }

    
}
