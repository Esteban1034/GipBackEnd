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
@Table(name = "submenu_fases_agiles")
public class SubMenuFasesAgiles implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_propuesta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @ManyToOne
	@JoinColumn(name = "fk_fases_agiles")
    private FasesAgiles fases_agiles;

    @Column(name = "nombre")
    private String nombre;

    public SubMenuFasesAgiles() {
    }

    public SubMenuFasesAgiles(Integer id, FasesAgiles fases_agiles, String nombre) {
        this.id = id;
        this.fases_agiles = fases_agiles;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "SubMenuFasesAgiles [id=" + id + ", fases_agiles=" + fases_agiles + ", nombre=" + nombre + "]";
    }


    
    
    


}
