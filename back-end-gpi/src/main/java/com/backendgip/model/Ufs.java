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
@Table(name = "ufs")
public class Ufs implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_ufs")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "funcion")
	private String funcion;
	@Column(name = "nombre_caso")
	private String nombreCaso;
	@ManyToOne
    @JoinColumn(name = "fk_complejidad")
    private Complejidad complejidad;

	public Ufs() {
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

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	public Complejidad getComplejidad() {
		return complejidad;
	}

	public void setComplejidad(Complejidad complejidad) {
		this.complejidad = complejidad;
	}

	public Ufs(Integer id, String funcion, String nombreCaso, Complejidad complejidad) {
		this.id = id;
		this.funcion = funcion;
		this.nombreCaso = nombreCaso;
		this.complejidad = complejidad;
	}

}
