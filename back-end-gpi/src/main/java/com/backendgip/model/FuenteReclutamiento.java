//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fuente_reclutamiento")
public class FuenteReclutamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_fuente")
	private Integer id;
	@Column(name = "fuente")
	private String fuente;
	@Column(name = "descripcion")
	private String descripcion;

	public FuenteReclutamiento() {
	}

	public FuenteReclutamiento(Integer id, String fuente, String descripcion) {
		this.id = id;
		this.fuente = fuente;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Fuente [id=" + id + ", fuente=" + fuente + ", descripcion=" + descripcion + "]";
	}	
}
