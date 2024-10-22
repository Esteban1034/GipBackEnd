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
@Table(name = "cargo_reclutamiento")
public class CargoReclutamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_cargo")
	private Integer id;
	@Column(name = "cargo")
	private String cargo;
	@Column(name = "descripcion")
	private String descripcion;

	public CargoReclutamiento() {
	}

	public CargoReclutamiento(Integer id, String cargo, String descripcion) {
		this.id = id;
		this.cargo = cargo;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Cargo [id=" + id + ", cargo=" + cargo + ", descripcion=" + descripcion + "]";
	}	
}
