//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
	@Column(name = "pk_subfuncion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "descripcion")
	private String subfuncion;

    @ManyToOne
    @JoinColumn(name = "FK_funciones")
    private Funcion funcion;

    @ManyToOne
    @JoinColumn(name = "FK_mantenimientos")
    private MantenimientoUnidad mantenimientoUnidad;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubfuncion() {
        return subfuncion;
    }

    public void setSubfuncion(String subfuncion) {
        this.subfuncion = subfuncion;
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

    public Subfuncion(Integer id, String subfuncion, Funcion funcion, MantenimientoUnidad mantenimientoUnidad) {
        this.id = id;
        this.subfuncion = subfuncion;
        this.funcion = funcion;
        this.mantenimientoUnidad = mantenimientoUnidad;
    }


    
}
