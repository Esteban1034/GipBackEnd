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
public class SubItemRol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private SubItem subItem;

	@ManyToOne
	private ItemRol itemRol;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subItemRol")
	private List<SubLevelItemRol> subLevelItemRols;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubItem getSubItem() {
		return subItem;
	}

	public void setSubItem(SubItem subItem) {
		this.subItem = subItem;
	}

	public ItemRol getItemRol() {
		return itemRol;
	}

	public void setItemRol(ItemRol itemRol) {
		this.itemRol = itemRol;
	}

	public List<SubLevelItemRol> getSubLevelItemRols() {
		return subLevelItemRols;
	}

	public void setSubLevelItemRols(List<SubLevelItemRol> subLevelItemRols) {
		this.subLevelItemRols = subLevelItemRols;
	}
}
