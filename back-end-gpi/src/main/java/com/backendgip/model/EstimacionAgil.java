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
@Table(name = "estimacion_agil")               
public class EstimacionAgil implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_estimacion_agil")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_estimacion")
    private EstimacionUfs estimacion;

    @ManyToOne
    @JoinColumn(name = "fk_actividad_agil")
    private ActividadesAgiles actividad;

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    private FasesAgiles fase;

    @ManyToOne
    @JoinColumn(name = "fk_subFase")
    private SubMenuFasesAgiles subfase;

    @ManyToOne
    @JoinColumn(name = "fk_complejiad")
    private ComplejidadAgiles complejidad;

    public EstimacionAgil() {
    }

    public EstimacionAgil(Integer id, EstimacionUfs estimacion, ActividadesAgiles actividad, FasesAgiles fase,
            SubMenuFasesAgiles subfase, ComplejidadAgiles complejidad) {
        this.id = id;
        this.estimacion = estimacion;
        this.actividad = actividad;
        this.fase = fase;
        this.subfase = subfase;
        this.complejidad = complejidad;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstimacionUfs getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(EstimacionUfs estimacion) {
        this.estimacion = estimacion;
    }

    public ActividadesAgiles getActividad() {
        return actividad;
    }

    public void setActividad(ActividadesAgiles actividad) {
        this.actividad = actividad;
    }

    public FasesAgiles getFase() {
        return fase;
    }

    public void setFase(FasesAgiles fase) {
        this.fase = fase;
    }

    public SubMenuFasesAgiles getSubfase() {
        return subfase;
    }

    public void setSubfase(SubMenuFasesAgiles subfase) {
        this.subfase = subfase;
    }

    public ComplejidadAgiles getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(ComplejidadAgiles complejidad) {
        this.complejidad = complejidad;
    }

    @Override
    public String toString() {
        return "EstimacionAgil [id=" + id + ", estimacion=" + estimacion + ", actividad=" + actividad + ", fase=" + fase
                + ", subfase=" + subfase + ", complejidad=" + complejidad + "]";
    }

    

}
