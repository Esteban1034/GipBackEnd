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
@Table(name = "tipo_actividad_complementaria_complemento")
public class TipoActividadComplementariaComplemento implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_propuesta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
    @ManyToOne
    @JoinColumn(name = "fk_tipo_actividad")
    private TipoActividadComplementaria actividad;

    public TipoActividadComplementariaComplemento() {
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

    public TipoActividadComplementaria getActividad() {
        return actividad;
    }

    public void setActividad(TipoActividadComplementaria actividad) {
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return "TipoActividadComplementariaComplemento [id=" + id + ", nombre=" + nombre + ", actividad=" + actividad
                + "]";
    }
    
}
