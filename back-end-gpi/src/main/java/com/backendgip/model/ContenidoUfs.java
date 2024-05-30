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
	@Column(name = "funcion")
	private String funcion;
	@Column(name = "nombre_caso")
	private String nombreCaso;
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
    
	public ContenidoUfs() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
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

    public ContenidoUfs(Integer id, String funcion, String nombreCaso, MantenimientoUnidad mantenimientoUnidad,
            Integer porcentajeDiseno, Integer porcentajeConstruccion, Integer porcentajePruebas, Integer totalDiseno,
            Integer totalConstruccion, Integer totalPruebas) {
        this.id = id;
        this.funcion = funcion;
        this.nombreCaso = nombreCaso;
        this.mantenimientoUnidad = mantenimientoUnidad;
        this.porcentajeDiseno = porcentajeDiseno;
        this.porcentajeConstruccion = porcentajeConstruccion;
        this.porcentajePruebas = porcentajePruebas;
        this.totalDiseno = totalDiseno;
        this.totalConstruccion = totalConstruccion;
        this.totalPruebas = totalPruebas;
    }

    
    
}
