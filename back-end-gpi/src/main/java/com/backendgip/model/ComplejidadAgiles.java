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
@Table(name = "complejidad_agiles")
public class ComplejidadAgiles implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "complejidad")
    private String complejidadActividad;
    
    @Column(name = "hora")
    private int horaActividad;

    public ComplejidadAgiles() {
    }

    public ComplejidadAgiles(Integer id, String complejidadActividad,  int horaActividad) {
        this.id = id;
        this.complejidadActividad = complejidadActividad;
        this.horaActividad = horaActividad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplejidadActividad() {
        return complejidadActividad;
    }

    public void setComplejidadActividad(String complejidadActividad) {
        this.complejidadActividad = complejidadActividad;
    }

    public int getHoraActividad() {
        return horaActividad;
    }

    public void setHoraActividad(int horaActividad) {
        this.horaActividad = horaActividad;
    }

    @Override
    public String toString() {
        return "ComplejidadAgiles [id=" + id + ", complejidadActividad=" + complejidadActividad
                + ", horaActividad=" + horaActividad + "]";
    }
}