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
@Table(name = "actividad_agil")
public class ActividadesAgiles  implements Serializable{

    private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fk_submenu_fases")
    private SubMenuFasesAgiles subMenuFasesAgiles;

    public ActividadesAgiles() {
    }

    public ActividadesAgiles(Integer id, String nombre, SubMenuFasesAgiles subMenuFasesAgiles) {
        this.id = id;
        this.nombre = nombre;
        this.subMenuFasesAgiles = subMenuFasesAgiles;
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

    public SubMenuFasesAgiles getSubMenuFasesAgiles() {
        return subMenuFasesAgiles;
    }

    public void setSubMenuFasesAgiles(SubMenuFasesAgiles subMenuFasesAgiles) {
        this.subMenuFasesAgiles = subMenuFasesAgiles;
    }

    @Override
    public String toString() {
        return "ActividadesAgiles [id=" + id + ", nombre=" + nombre +   ", subMenuFasesAgiles=" + subMenuFasesAgiles + "]";
    }

    
    

    
}
