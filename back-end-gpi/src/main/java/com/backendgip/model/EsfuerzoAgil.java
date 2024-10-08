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
@Table(name = "esfuerzo_agil")      
public class EsfuerzoAgil implements Serializable{
    
	@Id
	@Column(name = "pk_esfuerzo_agil")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    private FasesAgiles fase;

    @ManyToOne
    @JoinColumn(name = "fk_estimacion")
    private EstimacionUfs estimacion;

    @Column(name = "horas")
    private Double horas;

    @Column(name = "porcentaje_desviacion")
    private Integer porcentaje;

    public EsfuerzoAgil() {
    }

    public EsfuerzoAgil(Integer id, FasesAgiles fase, EstimacionUfs estimacion, Double horas, Integer porcentaje) {
        this.id = id;
        this.fase = fase;
        this.estimacion = estimacion;
        this.horas = horas;
        this.porcentaje = porcentaje;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FasesAgiles getFase() {
        return fase;
    }

    public void setFase(FasesAgiles fase) {
        this.fase = fase;
    }

    public EstimacionUfs getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(EstimacionUfs estimacion) {
        this.estimacion = estimacion;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "EsfuerzoAgil [id=" + id + ", fase=" + fase + ", estimacion=" + estimacion + ", horas=" + horas
                + ", porcentaje=" + porcentaje + "]";
    }

    
    
    
}
