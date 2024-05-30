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
@Table(name = "esfuerzo")
public class Esfuerzo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_esfuerzo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "porcentaje")
	private Integer porcentaje;
    @Column(name = "total_horas_con_ajuste")
	private Integer totalHorasConAjuste;
    @Column(name = "total_horas_sin_ajuste")
	private Integer totalHorasSinAjuste;

	public Esfuerzo() {
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

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getTotalHorasConAjuste() {
        return totalHorasConAjuste;
    }

    public void setTotalHorasConAjuste(Integer totalHorasConAjuste) {
        this.totalHorasConAjuste = totalHorasConAjuste;
    }

    public Integer getTotalHorasSinAjuste() {
        return totalHorasSinAjuste;
    }

    public void setTotalHorasSinAjuste(Integer totalHorasSinAjuste) {
        this.totalHorasSinAjuste = totalHorasSinAjuste;
    }

    public Esfuerzo(Integer id, String nombre, Integer porcentaje, Integer totalHorasConAjuste,
            Integer totalHorasSinAjuste) {
        this.id = id;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.totalHorasConAjuste = totalHorasConAjuste;
        this.totalHorasSinAjuste = totalHorasSinAjuste;
    }

    
}
