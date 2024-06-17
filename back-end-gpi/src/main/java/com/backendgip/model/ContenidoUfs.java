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
@Table(name = "contenido_ufs")
public class ContenidoUfs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pk_contenido_ufs")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_caso")
    private String nombreCaso;
    @ManyToOne
    @JoinColumn(name = "fk_esfuerzo")
    private Esfuerzo esfuerzo;
    @ManyToOne
    @JoinColumn(name = "fk_funcion")
    private Funcion funcion;
    @ManyToOne
    @JoinColumn(name = "fk_mantenimiento_unidad")
    private MantenimientoUnidad mantenimientoUnidad;
    @Column(name = "porcentaje_diseño")
    private Integer porcentajeDiseno;
    @Column(name = "porcentaje_construccion")
    private Integer porcentajeConstruccion;
    @Column(name = "porcentaje_Pruebas")
    private Integer porcentajePruebas;
    @Column(name = "total_diseno")
    private Integer totalDiseno;
    @Column(name = "total_construccion")
    private Integer totalConstruccion;
    @Column(name = "total_pruebas")
    private Integer totalPruebas;
    @ManyToOne
    @JoinColumn(name = "fk_ufs", nullable=false)
    private Ufs ufs;


    public ContenidoUfs() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public Esfuerzo getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(Esfuerzo esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public MantenimientoUnidad getMantenimientoUnidad() {
        return mantenimientoUnidad;
    }

    public void setMantenimientoUnidad(MantenimientoUnidad mantenimientoUnidad) {
        this.mantenimientoUnidad = mantenimientoUnidad;
    }

    public Integer getPorcentajeDiseno() {
        return porcentajeDiseno;
    }

    public void setPorcentajeDiseno(Integer porcentajeDiseno) {
        this.porcentajeDiseno = porcentajeDiseno;
    }

    public Integer getPorcentajeConstruccion() {
        return porcentajeConstruccion;
    }

    public void setPorcentajeConstruccion(Integer porcentajeConstruccion) {
        this.porcentajeConstruccion = porcentajeConstruccion;
    }

    public Integer getPorcentajePruebas() {
        return porcentajePruebas;
    }

    public void setPorcentajePruebas(Integer porcentajePruebas) {
        this.porcentajePruebas = porcentajePruebas;
    }

    public Integer getTotalDiseno() {
        return totalDiseno;
    }

    public void setTotalDiseno(Integer totalDiseno) {
        this.totalDiseno = totalDiseno;
    }

    public Integer getTotalConstruccion() {
        return totalConstruccion;
    }

    public void setTotalConstruccion(Integer totalConstruccion) {
        this.totalConstruccion = totalConstruccion;
    }

    public Integer getTotalPruebas() {
        return totalPruebas;
    }

    public void setTotalPruebas(Integer totalPruebas) {
        this.totalPruebas = totalPruebas;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    

    public Ufs getUfs() {
        return ufs;
    }

    public void setUfs(Ufs ufs) {
        this.ufs = ufs;
    }

    public ContenidoUfs(Integer id, String nombreCaso, Esfuerzo esfuerzo, Funcion funcion,
            MantenimientoUnidad mantenimientoUnidad, Integer porcentajeDiseno, Integer porcentajeConstruccion,
            Integer porcentajePruebas, Integer totalDiseno, Integer totalConstruccion, Integer totalPruebas, Ufs ufs) {
        this.id = id;
        this.nombreCaso = nombreCaso;
        this.esfuerzo = esfuerzo;
        this.funcion = funcion;
        this.mantenimientoUnidad = mantenimientoUnidad;
        this.porcentajeDiseno = porcentajeDiseno;
        this.porcentajeConstruccion = porcentajeConstruccion;
        this.porcentajePruebas = porcentajePruebas;
        this.totalDiseno = totalDiseno;
        this.totalConstruccion = totalConstruccion;
        this.totalPruebas = totalPruebas;
        this.ufs = ufs;
    }

    

}
