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
import javax.persistence.Table;

@Entity
@Table(name = "mantenimiento_peso_hora")
public class MantenimientoPesoHora implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_mantenimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "peso")
	private Integer peso;
	@Column(name = "hora")
	private Double hora;

	public MantenimientoPesoHora() {
	}

    public MantenimientoPesoHora(Integer id, Integer peso, Double hora) {
        this.id = id;
        this.peso = peso;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Double getHora() {
        return hora;
    }

    public void setHora(Double hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "MantenimientoPesoHora [id=" + id + ", peso=" + peso + ", hora=" + hora + "]";
    }

    public MantenimientoPesoHora orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    

}
