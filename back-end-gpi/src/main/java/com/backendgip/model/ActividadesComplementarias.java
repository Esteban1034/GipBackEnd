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
@Table(name = "actividades_complementarias")
public class ActividadesComplementarias implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_Actividad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_actividades_estimacion")
	private ActividadesEstimacion actividadesEstimacion;
	@ManyToOne
	@JoinColumn(name = "fk_propuesta")
	private Propuesta propuesta;
	@ManyToOne
	@JoinColumn(name = "fk_analisis_diseno")
	private AnalisisDiseno analisisDiseno;
	@ManyToOne
	@JoinColumn(name = "fk_elaboracion")
	private Elaboracion elaboracion;
	@ManyToOne
	@JoinColumn(name = "fk_transicion")
	private Transicion transicion;

	public ActividadesComplementarias() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ActividadesEstimacion getActividadesEstimacion() {
		return actividadesEstimacion;
	}

	public void setActividadesEstimacion(ActividadesEstimacion actividadesEstimacion) {
		this.actividadesEstimacion = actividadesEstimacion;
	}

	public Propuesta getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public AnalisisDiseno getAnalisisDiseno() {
		return analisisDiseno;
	}

	public void setAnalisisDiseno(AnalisisDiseno analisisDiseno) {
		this.analisisDiseno = analisisDiseno;
	}

	public Elaboracion getElaboracion() {
		return elaboracion;
	}

	public void setElaboracion(Elaboracion elaboracion) {
		this.elaboracion = elaboracion;
	}

	public Transicion getTransicion() {
		return transicion;
	}

	public void setTransicion(Transicion transicion) {
		this.transicion = transicion;
	}

	public ActividadesComplementarias(Integer id, ActividadesEstimacion actividadesEstimacion, Propuesta propuesta,
			AnalisisDiseno analisisDiseno, Elaboracion elaboracion, Transicion transicion) {
		this.id = id;
		this.actividadesEstimacion = actividadesEstimacion;
		this.propuesta = propuesta;
		this.analisisDiseno = analisisDiseno;
		this.elaboracion = elaboracion;
		this.transicion = transicion;
	}
	
	
}
