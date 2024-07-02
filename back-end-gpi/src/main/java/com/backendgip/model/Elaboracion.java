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
@Table(name = "elaboracion")
public class Elaboracion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_elaboracion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "horas")
	private Integer horas;
	@Column(name = "porcentaje")
	private Integer porcentaje;

	public Elaboracion() {
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

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Elaboracion(Integer id, String nombre, Integer horas, Integer porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
        this.porcentaje = porcentaje;
    }
}
