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
@Table(name = "ufs")
public class Ufs implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_ufs")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@ManyToOne
    @JoinColumn(name = "fk_contenido_ufs")
    private ContenidoUfs contenidoUfs;

	public Ufs() {
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

	public ContenidoUfs getContenidoUfs() {
		return contenidoUfs;
	}

	public void setContenidoUfs(ContenidoUfs contenidoUfs) {
		this.contenidoUfs = contenidoUfs;
	}

	public Ufs(Integer id, String nombre, ContenidoUfs contenidoUfs) {
		this.id = id;
		this.nombre = nombre;
		this.contenidoUfs = contenidoUfs;
	}

}
