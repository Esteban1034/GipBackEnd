package com.backendgip.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "actividades_estimacion")
public class ActividadesEstimacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "pk_act_estimacion");
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "act_nombre")
    private String nombre;
    @Column (name = "act_hora")
    private Integer hora;
    @Column (name = "act_porcentaje")
    private Integer porcentaje;

    public ActividadesEstimacion(){

    }

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Integer getHora(){
        return hora;
    }

    public void setHora(Integer hora){
        this.hora = hora;
    }

    public Integer getPorcentaje(){
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje){
        this.porcentaje = porcentaje;
    }

    public ActividadesEstimacion(Integer id, String nombre, Integer hora, Integer porcentaje){
        this.id = id;
        this.nombre = nombre;
        this.hora = hora;
        this.porcentaje = porcentaje;
    }
}    
