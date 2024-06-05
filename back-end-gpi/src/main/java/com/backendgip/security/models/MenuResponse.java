package com.backendgip.security.models;

import java.util.List;

public class MenuResponse {

	String label;
	String icon;
	String link;
	List<Item> subMenus;
	List<SubItem> subItems;

	public MenuResponse() {
	}

	public MenuResponse(String label, String icon, List<Item> subMenus, List<SubItem> subItems, String link) {
		this.label = label;
		this.icon = icon;
		this.subMenus = subMenus;
		this.subItems = subItems;
		this.link = link;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Item> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Item> subMenus) {
		this.subMenus = subMenus;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<SubItem> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<SubItem> subItems) {
		this.subItems = subItems;
	}

	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder(
				"{\"label\":\"" + label + "\", \"icon\":\"" + icon + "\", \"link\":\"" + link + "\"");

		if (subMenus != null && !subMenus.isEmpty()) {
			retorno.append(", \"subItems\": [");
			for (int i = 0; i < subMenus.size(); i++) {
				Item item = subMenus.get(i);
				retorno.append("{\"label\":\"").append(item.getLabel()).append("\", \"link\":\"").append(item.getLink())
						.append("\"");

				List<SubItem> subItems = item.getSubItems();
				if (subItems != null && !subItems.isEmpty()) {
					retorno.append(", \"subItems\": [");
					for (int j = 0; j < subItems.size(); j++) {
						SubItem subItem = subItems.get(j);
						retorno.append("{\"label\":\"").append(subItem.getLabel()).append("\", \"link\":\"")
								.append(subItem.getLink()).append("\"}");
						if (j < subItems.size() - 1) {
							retorno.append(",");
						}
					}
					retorno.append("]");
				}

				retorno.append("}");
				if (i < subMenus.size() - 1) {
					retorno.append(",");
				}
			}
			retorno.append("]");
		}

		retorno.append("}");
		return retorno.toString();
	}

}
