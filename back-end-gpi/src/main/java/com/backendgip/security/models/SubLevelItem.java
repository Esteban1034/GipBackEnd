package com.backendgip.security.models;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="sublevelitem")
public class SubLevelItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="isTitle")
	private boolean isTitle;
	
	@Column(name="label")
	private String label;
	
	@Column(name="link")
	private String link;
	
	@Transient
	private boolean seleccionado; 
	
	@ManyToOne
    @JoinColumn(name = "subitem_id")
	@JsonIgnore
    private SubItem subItem;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subLevelItem")
    @JsonIgnore
    private List<SubLevelItemRol> subLevelItemRol;
	
	public SubLevelItem() {


	}
	
	public SubLevelItem(long id, boolean isTitle, String label, String link, SubItem subItem) {
		this.id = id;
		this.isTitle = isTitle;
		this.label = label;
		this.link = link;
		this.subItem = subItem;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isTitle() {
		return isTitle;
	}
	public void setTitle(boolean isTitle) {
		this.isTitle = isTitle;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
		
	}

	public SubItem getSubItem() {
		return subItem;
	}

	public void setSubItem(SubItem subItem) {
		this.subItem = subItem;
	}

	public List<SubLevelItemRol> getSubLevelItemRol() {
		return subLevelItemRol;
	}

	public void setSubLevelItemRol(List<SubLevelItemRol> subLevelItemRol) {
		this.subLevelItemRol = subLevelItemRol;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	@Override
	public String toString() {
		return "{\"label\":\"" + label + "\", \"link\":\"" + link + "\"}";
	}


}
