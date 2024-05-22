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
@Table(name = "estado_actividad_asignada")
public class EstadoActividadAsig implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_estado_actividad_asignada")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "desc_estado_actividad")
	private String estado;

	public EstadoActividadAsig() {
	}

	public EstadoActividadAsig(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String toString() {
		return "EstadoActividadAsig [id=" + this.id + ", estado=" + this.estado + "]";
	}
}
