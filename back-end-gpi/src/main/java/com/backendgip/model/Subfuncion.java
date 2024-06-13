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
@Table(name = "subfuncion")
public class Subfuncion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_funcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "base_de_datos")
	private String basededatos;
	@Column(name = "parametrizacion")
	private String parametrizacion;
    @JoinColumn(name = "mantenimientos")
    private String mantenimiento;
    @JoinColumn(name = "procesos")
    private String procesos;
    
	public Subfuncion() {
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBasededatos() {
        return basededatos;
    }

    public void setBasededatos(String basededatos) {
        this.basededatos = basededatos;
    }

    public String getParametrizacion() {
        return parametrizacion;
    }

    public void setParametrizacion(String parametrizacion) {
        this.parametrizacion = parametrizacion;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public String getProcesos() {
        return procesos;
    }

    public void setProcesos(String procesos) {
        this.procesos = procesos;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Subfuncion(Integer id, String basededatos, String parametrizacion, String mantenimiento, String procesos) {
        this.id = id;
        this.basededatos = basededatos;
        this.parametrizacion = parametrizacion;
        this.mantenimiento = mantenimiento;
        this.procesos = procesos;
    }

    
    
}
