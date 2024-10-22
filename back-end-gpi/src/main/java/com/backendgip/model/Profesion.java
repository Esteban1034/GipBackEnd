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
@Table(name = "profesion")
public class Profesion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_profesion")
	private Integer id;
	@Column(name = "profesion")
	private String profesion;
	@Column(name = "descripcion")
	private String descripcion;

	public Profesion() {
	}

	public Profesion(Integer id, String profesion, String descripcion) {
		this.id = id;
		this.profesion = profesion;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Profesion [id=" + id + ", profesion=" + profesion + ", descripcion=" + descripcion + "]";
	}	
}
