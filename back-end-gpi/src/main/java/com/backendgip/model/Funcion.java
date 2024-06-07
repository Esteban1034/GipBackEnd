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
import javax.persistence.Table;

@Entity
@Table(name = "Funcion")
public class Funcion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_funcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "funcion")
	private String funcion;


    public Funcion() {
    }

    public Funcion(Integer id, String funcion) {
        this.id = id;
        this.funcion = funcion;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    @Override
    public String toString() {
        return "Funcion [id=" + id + ", funcion=" + funcion + "]";
    }
    
}
