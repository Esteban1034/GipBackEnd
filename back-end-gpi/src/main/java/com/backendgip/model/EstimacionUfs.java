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
@Table(name = "estimacion_ufs")
public class EstimacionUfs implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "pk_estimacion_ufs")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
    @JoinColumn(name = "fk_ufs")
    private Ufs ufs;

	public EstimacionUfs() {
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

	public Ufs getUfs() {
		return ufs;
	}

	public void setUfs(Ufs ufs) {
		this.ufs = ufs;
	}

	public EstimacionUfs(Integer id, Ufs ufs) {
		this.id = id;
		this.ufs = ufs;
	}

}
