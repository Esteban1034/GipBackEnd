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
    private Subfuncion subfuncion;
  
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
    @JoinColumn(name = "fk_ufs")
    private UnidadFuncional ufs;
    @ManyToOne
    @JoinColumn(name = "fk_estimacionUfs")
    private EstimacionUfs estimacionUfs;


 
    public ContenidoUfs() {
    }


    public ContenidoUfs(Integer id, String nombreCaso, Esfuerzo esfuerzo, Subfuncion subfuncion,
            Integer porcentajeDiseno, Integer porcentajeConstruccion,
            Integer porcentajePruebas, Integer totalDiseno, Integer totalConstruccion, Integer totalPruebas, UnidadFuncional ufs,
            EstimacionUfs estimacionUfs) {
        this.id = id;
        this.nombreCaso = nombreCaso;
        this.esfuerzo = esfuerzo;
        this.subfuncion = subfuncion;
        this.porcentajeDiseno = porcentajeDiseno;
        this.porcentajeConstruccion = porcentajeConstruccion;
        this.porcentajePruebas = porcentajePruebas;
        this.totalDiseno = totalDiseno;
        this.totalConstruccion = totalConstruccion;
        this.totalPruebas = totalPruebas;
        this.ufs = ufs;
        this.estimacionUfs = estimacionUfs;
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


    public UnidadFuncional getUfs() {
        return ufs;
    }


    public void setUfs(UnidadFuncional ufs) {
        this.ufs = ufs;
    }


    public EstimacionUfs getEstimacionUfs() {
        return estimacionUfs;
    }


    public void setEstimacionUfs(EstimacionUfs estimacionUfs) {
        this.estimacionUfs = estimacionUfs;
    }


    public Subfuncion getSubfuncion() {
        return subfuncion;
    }


    public void setSubfuncion(Subfuncion subfuncion) {
        this.subfuncion = subfuncion;
    }


    @Override
    public String toString() {
        return "ContenidoUfs [id=" + id + ", nombreCaso=" + nombreCaso + ", esfuerzo=" + esfuerzo + ", subfuncion="
                + subfuncion +", porcentajeDiseno=" + porcentajeDiseno
                + ", porcentajeConstruccion=" + porcentajeConstruccion + ", porcentajePruebas=" + porcentajePruebas
                + ", totalDiseno=" + totalDiseno + ", totalConstruccion=" + totalConstruccion + ", totalPruebas="
                + totalPruebas + ", ufs=" + ufs + ", estimacionUfs=" + estimacionUfs + "]";
    }


   

}