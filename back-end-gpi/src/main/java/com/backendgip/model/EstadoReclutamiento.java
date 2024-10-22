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
@Table(name = "estado_reclutamiento")
public class EstadoReclutamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_estado")
	private Integer id;
	@Column(name = "estado")
	private String estado;
	@Column(name = "descripcion")
	private String descripcion;

	public EstadoReclutamiento() {
	}

	public EstadoReclutamiento(Integer id, String estado, String descripcion) {
		this.id = id;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", estado=" + estado + ", descripcion=" + descripcion + "]";
	}	
}
