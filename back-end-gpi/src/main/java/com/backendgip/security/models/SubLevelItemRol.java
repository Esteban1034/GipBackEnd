package com.backendgip.security.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SubLevelItemRol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private SubLevelItem subLevelItem;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subLevelItem")
    private List<SubLevelItemRol> subLevelItemRol;

	@ManyToOne
	private SubItemRol subItemRol;

	public Long getId() {
		return id;
	}

	public SubLevelItem getSubLevelItem() {
		return subLevelItem;
	}

	public void setSubLevelItem(SubLevelItem subLevelItem) {
		this.subLevelItem = subLevelItem;
	}

	public SubItemRol getSubItemRol() {
		return subItemRol;
	}

	public void setSubItemRol(SubItemRol subItemRol) {
		this.subItemRol = subItemRol;
	}
	

}
