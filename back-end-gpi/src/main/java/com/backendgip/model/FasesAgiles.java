package com.backendgip.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fases_agiles")
public class FasesAgiles implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_funcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public FasesAgiles() {
    }

    public FasesAgiles(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "FasesAgiles [id=" + id + ", nombre=" + nombre + "]";
    }

    
}