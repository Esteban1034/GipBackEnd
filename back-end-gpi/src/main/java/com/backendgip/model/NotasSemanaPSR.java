package com.backendgip.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "psr_notas_semana")
public class NotasSemanaPSR implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_psr_notas_semana")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   
    @ManyToOne
    @JoinColumn(name = "fk_pk_empleado")
    private Empleado empleado;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @Column(name = "esObservacion")
    private Boolean esObservacion;
    @Column(name = "fecha_observacion")
    private LocalDate fechaObservacion;
    
    
    public NotasSemanaPSR() {
    }



    public NotasSemanaPSR(Integer id, Empleado empleado, String comentario, LocalDate fechaCreacion,
            Boolean esObservacion, LocalDate fechaObservacion) {
        this.id = id;
        this.empleado = empleado;
        this.comentario = comentario;
        this.fechaCreacion = fechaCreacion;
        this.esObservacion = esObservacion;
        this.fechaObservacion = fechaObservacion;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Boolean getEsObservacion() {
        return esObservacion;
    }

    public void setEsObservacion(Boolean esObservacion) {
        this.esObservacion = esObservacion;
    }

    public LocalDate getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(LocalDate fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }

    

}
