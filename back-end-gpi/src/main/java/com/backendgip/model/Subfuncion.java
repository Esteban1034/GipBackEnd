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
@Table(name = "subfuncion")
public class Subfuncion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_funcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_subfuncion")
    private String nombre;
    @Column(name = "nombre_caso_uso")
    private String nombreCasoDeUso;
    @ManyToOne
    @JoinColumn(name = "fk_funcion")
    private Funcion funcion;
    @ManyToOne
    @JoinColumn(name = "fk_complejidad")
    private MantenimientoUnidad mantenimientoUnidad;
    @Column(name = "porcentaje_contruccion")
    private double porcentajeVConstruccion;
    @Column(name = "porcentaje_diseño")
    private double porcentajeVDiseno;
    @Column(name = "porcentaje_pruebas")
    private double porcentajePruebas;


    public Subfuncion() {
    }


    public Subfuncion(Integer id, String nombre, Funcion funcion, MantenimientoUnidad mantenimientoUnidad,
            String nombreCasoDeUso, double porcentajeVConstruccion, double porcentajeVDiseno,
            double porcentajePruebas) {
        this.id = id;
        this.nombre = nombre;
        this.funcion = funcion;
        this.mantenimientoUnidad = mantenimientoUnidad;
        this.nombreCasoDeUso = nombreCasoDeUso;
        this.porcentajeVConstruccion = porcentajeVConstruccion;
        this.porcentajeVDiseno = porcentajeVDiseno;
        this.porcentajePruebas = porcentajePruebas;
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


    public Funcion getFuncion() {
        return funcion;
    }


    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }


    public MantenimientoUnidad getMantenimientoUnidad() {
        return mantenimientoUnidad;
    }


    public void setMantenimientoUnidad(MantenimientoUnidad mantenimientoUnidad) {
        this.mantenimientoUnidad = mantenimientoUnidad;
    }


    public String getNombreCasoDeUso() {
        return nombreCasoDeUso;
    }


    public void setNombreCasoDeUso(String nombreCasoDeUso) {
        this.nombreCasoDeUso = nombreCasoDeUso;
    }


    public double getPorcentajeVConstruccion() {
        return porcentajeVConstruccion;
    }


    public void setPorcentajeVConstruccion(double porcentajeVConstruccion) {
        this.porcentajeVConstruccion = porcentajeVConstruccion;
    }


    public double getPorcentajeVDiseno() {
        return porcentajeVDiseno;
    }


    public void setPorcentajeVDiseno(double porcentajeVDiseno) {
        this.porcentajeVDiseno = porcentajeVDiseno;
    }


    public double getPorcentajePruebas() {
        return porcentajePruebas;
    }


    public void setPorcentajePruebas(double porcentajePruebas) {
        this.porcentajePruebas = porcentajePruebas;
    }


    @Override
    public String toString() {
        return "Subfuncion [id=" + id + ", nombre=" + nombre + ", funcion=" + funcion + ", mantenimientoUnidad="
                + mantenimientoUnidad + ", nombreCasoDeUso=" + nombreCasoDeUso + ", porcentajeVConstruccion="
                + porcentajeVConstruccion + ", porcentajeVDiseno=" + porcentajeVDiseno + ", porcentajePruebas="
                + porcentajePruebas + "]";
    }
}
