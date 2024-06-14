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
@Table(name = "mantenimiento_unidad")
public class MantenimientoUnidad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_mantenimiento_unidad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "peso")
	private Integer peso;
    

	public MantenimientoUnidad() {
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

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }


    public MantenimientoUnidad(Integer id, String nombre, Integer peso, MantenimientoPesoHora mantenimientoPesoHora) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "MantenimientoUnidad [id=" + id + ", nombre=" + nombre + ", peso=" + peso + "]";
    }

    
}
