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
    private EstimacionUfs estimacion_ufs;

	public UnidadFuncional() {
	}

	public UnidadFuncional(Integer id, String nombre, EstimacionUfs estimacion_ufs) {
		this.id = id;
		this.nombre = nombre;
		this.estimacion_ufs = estimacion_ufs;
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

	public EstimacionUfs getEstimacion_ufs() {
		return estimacion_ufs;
	}

	public void setEstimacion_ufs(EstimacionUfs estimacion_ufs) {
		this.estimacion_ufs = estimacion_ufs;
	}

	@Override
	public String toString() {
		return "UnidadFuncional [id=" + id + ", nombre=" + nombre + ", estimacion_ufs=" + estimacion_ufs + "]";
	}
	
}
