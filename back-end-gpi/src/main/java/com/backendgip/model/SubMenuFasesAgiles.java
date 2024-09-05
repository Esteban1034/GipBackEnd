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
@Table(name = "submenu_fases_agiles")
public class SubMenuFasesAgiles implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_fases_agiles")
    private FasesAgiles fasesAgiles;

    @Column(name = "nombre")
    private String nombre;

    public SubMenuFasesAgiles() {
    }

    public SubMenuFasesAgiles(Integer id, FasesAgiles fasesAgiles, String nombre) {
        this.id = id;
        this.fasesAgiles = fasesAgiles;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FasesAgiles getFasesAgiles() {
        return fasesAgiles;
    }

    public void setFasesAgiles(FasesAgiles fasesAgiles) {
        this.fasesAgiles = fasesAgiles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "SubMenuFasesAgiles [id=" + id + ", fasesAgiles=" + fasesAgiles + ", nombre=" + nombre + "]";
    }
}
