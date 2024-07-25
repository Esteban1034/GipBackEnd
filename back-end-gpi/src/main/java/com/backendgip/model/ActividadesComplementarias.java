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
@Table(name = "actividad_complementarias")
public class ActividadesComplementarias implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "fk_pk_estimacion")
	private EstimacionUfs estimacion;
	@ManyToOne
	@JoinColumn(name = "fk_pk_tipo_actividad")
	private TipoActividadComplementaria tipoActividad;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "horas")
	private Double horas;
	@Column(name = "porcentaje")
	private Integer porcentaje;

	public ActividadesComplementarias() {
	}

	public ActividadesComplementarias(Integer id, EstimacionUfs estimacion, TipoActividadComplementaria tipoActividad,
			String nombre, Double horas, Integer porcentaje) {
		this.id = id;
		this.estimacion = estimacion;
		this.tipoActividad = tipoActividad;
		this.nombre = nombre;
		this.horas = horas;
		this.porcentaje = porcentaje;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public EstimacionUfs getEstimacion() {
		return estimacion;
	}
	public void setEstimacion(EstimacionUfs estimacion) {
		this.estimacion = estimacion;
	}
	public TipoActividadComplementaria getTipoActividad() {
		return tipoActividad;
	}
	public void setTipoActividad(TipoActividadComplementaria tipoActividad) {
		this.tipoActividad = tipoActividad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getHoras() {
		return horas;
	}
	public void setHoras(Double horas) {
		this.horas = horas;
	}
	public Integer getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public String toString() {
		return "ActividadesComplementarias [id=" + id + ", estimacion=" + estimacion + ", tipoActividad="
				+ tipoActividad + ", nombre=" + nombre + ", horas=" + horas + ", porcentaje=" + porcentaje + "]";
	}

}
