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
@Table(name = "subitem")
public class SubItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "isTitle")
	private boolean isTitle;

	@Column(name = "label")
	private String label;

	@Column(name = "link")
	private String link;

	@Transient
	private boolean seleccionado;

	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonIgnore
	private Item item;

	@OneToMany(mappedBy = "subItem", cascade = CascadeType.ALL)
	private List<SubLevelItem> subLevelItems;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subItem")
	@JsonIgnore
	private List<SubItemRol> subItemRols;

	public SubItem() {

	}

	public SubItem(long id, boolean isTitle, String label, String link, Item item) {
		this.id = id;
		this.isTitle = isTitle;
		this.label = label;
		this.link = link;
		this.item = item;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public List<SubLevelItem> getSubLevelItems() {
		return subLevelItems;
	}

	public void setSubLevelItems(List<SubLevelItem> subLevelItems) {
		this.subLevelItems = subLevelItems;
	}

	public List<SubItemRol> getSubItemRols() {
		return subItemRols;
	}

	public void setSubItemRols(List<SubItemRol> subItemRols) {
		this.subItemRols = subItemRols;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"label\":\"").append(label).append("\", ");
		sb.append("\"link\":\"").append(link).append("\", ");
		sb.append("\"subLevelItems\": [");

		if (subLevelItems != null && !subLevelItems.isEmpty()) {
			for (int i = 0; i < subLevelItems.size(); i++) {
				SubLevelItem subLevelItem = subLevelItems.get(i);
				sb.append(subLevelItem.toString());
				if (i < subLevelItems.size() - 1) {
					sb.append(", ");
				}
			}
		}
		sb.append("]");
		sb.append("}");
		return sb.toString();
	}

}
