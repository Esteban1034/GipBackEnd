//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "funcion")
public class Funcion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_funcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre_funcion")
	private String nombre;
    @ManyToOne
    @JoinColumn(name = "fk_unidad_funcional")
    private UnidadFuncional unidadFuncional;
    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subfuncion> subfunciones;

    public Funcion() {
    }

    public Funcion(Integer id, String nombre, UnidadFuncional unidadFuncional) {
        this.id = id;
        this.nombre = nombre;
        this.unidadFuncional = unidadFuncional;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public UnidadFuncional getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(UnidadFuncional unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }

    @Override
    public String toString() {
        return "Funcion [id=" + id + ", nombre=" + nombre + ", unidadFuncional=" + unidadFuncional + "]";
    }
}
