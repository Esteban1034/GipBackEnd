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
@Table(name = "actividad_agiles")
public class ActividadesAgiles  implements Serializable{

    private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fk_fases")
    private FasesAgiles fases_agiles;

    @ManyToOne
    @JoinColumn(name = "fk_submenu_fases")
    private SubMenuFasesAgiles subMenuFasesAgiles;

    @ManyToOne
    @JoinColumn(name = "fk_actividades_complemento_as400")
    private ActividadesComplementarias actividadesComplementarias;

    public ActividadesAgiles() {
    }

    public ActividadesAgiles(Integer id, String nombre, FasesAgiles fases_agiles, SubMenuFasesAgiles subMenuFasesAgiles,
            ActividadesComplementarias actividadesComplementarias) {
        this.id = id;
        this.nombre = nombre;
        this.fases_agiles = fases_agiles;
        this.subMenuFasesAgiles = subMenuFasesAgiles;
        this.actividadesComplementarias = actividadesComplementarias;
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

    public FasesAgiles getFases_agiles() {
        return fases_agiles;
    }

    public void setFases_agiles(FasesAgiles fases_agiles) {
        this.fases_agiles = fases_agiles;
    }

    public SubMenuFasesAgiles getSubMenuFasesAgiles() {
        return subMenuFasesAgiles;
    }

    public void setSubMenuFasesAgiles(SubMenuFasesAgiles subMenuFasesAgiles) {
        this.subMenuFasesAgiles = subMenuFasesAgiles;
    }

    public ActividadesComplementarias getActividadesComplementarias() {
        return actividadesComplementarias;
    }

    public void setActividadesComplementarias(ActividadesComplementarias actividadesComplementarias) {
        this.actividadesComplementarias = actividadesComplementarias;
    }

    @Override
    public String toString() {
        return "ActividadesAgiles [id=" + id + ", nombre=" + nombre + ", fases_agiles=" + fases_agiles
                + ", subMenuFasesAgiles=" + subMenuFasesAgiles + ", actividadesComplementarias="
                + actividadesComplementarias + "]";
    }

    
    

    
}
