//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
@Table(name = "estimacion_ufs")
public class EstimacionUfs implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_estimacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "fk_proyecto")
    private Proyecto proyecto;
    @ManyToOne
    @JoinColumn(name = "fk_modelo")
    private Modelo modelo;
    @OneToMany(mappedBy = "estimacionUfs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnidadFuncional> unidadesFuncionales;

    public EstimacionUfs() {
    }

    public EstimacionUfs(Integer id, LocalDate fechaCreacion, Proyecto proyecto, Modelo modelo) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.proyecto = proyecto;
        this.modelo = modelo;
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "EstimacionUfs [id=" + id + ", fechaCreacion=" + fechaCreacion + ", proyecto=" + proyecto + ", modelo="
                + modelo + "]";
    }   

}
