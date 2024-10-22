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
@Table(name = "tipo_identificacion")
public class TipoIdentificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_tipo_identificacion")
	private Integer id;
	@Column(name = "tipo_identificacion")
	private String tipo;
	@Column(name = "abreviatura")
	private String abreviatura;

	public TipoIdentificacion() {
	}

	public TipoIdentificacion(String tipo, String abreviatura) {
		this.tipo = tipo;
		this.abreviatura = abreviatura;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Override
	public String toString() {
		return "TipoIdentificacion [id=" + id + ", tipo=" + tipo + ", abreviatura=" + abreviatura + "]";
	}
	
}
