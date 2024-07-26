//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unidad_funcional")
public class UnidadFuncional implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_unidad_funcional")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre_unidad_funcional")
	private String nombre;
	@ManyToOne
    @JoinColumn(name = "fk_estimacion")
    private EstimacionUfs estimacionUfs;


	public UnidadFuncional() {
	}

	public UnidadFuncional(Integer id, String nombre, EstimacionUfs estimacion_ufs) {
		this.id = id;
		this.nombre = nombre;
		this.estimacionUfs = estimacionUfs;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "UnidadFuncional [id=" + id + ", nombre=" + nombre + ", estimacionUfs=" + estimacionUfs + "]";
	}

	public EstimacionUfs getEstimacionUfs() {
		return estimacionUfs;
	}

	public void setEstimacion_ufs(EstimacionUfs estimacionUfs) {
		this.estimacionUfs = estimacionUfs;
	}
	
}
