//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @JoinColumn(name = "fk_actividades")
    private ActividadesComplementarias actividadesComplementarias;
    @ManyToOne
    @JoinColumn(name = "fk_modelo")
    private Modelo modelo;
    @ManyToOne
    @JoinColumn(name = "fk_empleado")
    private Empleado recurso;
    @ManyToOne
    @JoinColumn(name = "fk_ufs")
    private Ufs ufs;
  

	public EstimacionUfs() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
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

    public ActividadesComplementarias getActividadesComplementarias() {
        return actividadesComplementarias;
    }

    public void setActividadesComplementarias(ActividadesComplementarias actividadesComplementarias) {
        this.actividadesComplementarias = actividadesComplementarias;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Empleado getRecurso() {
        return recurso;
    }

    public void setRecurso(Empleado recurso) {
        this.recurso = recurso;
    }

    public Ufs getUfs() {
        return ufs;
    }

    public void setUfs(Ufs ufs) {
        this.ufs = ufs;
    }

    public EstimacionUfs(Integer id, LocalDate fechaCreacion, Proyecto proyecto,
            ActividadesComplementarias actividadesComplementarias, Modelo modelo, Empleado recurso, Ufs ufs) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.proyecto = proyecto;
        this.actividadesComplementarias = actividadesComplementarias;
        this.modelo = modelo;
        this.recurso = recurso;
        this.ufs = ufs;
    }



    

}
