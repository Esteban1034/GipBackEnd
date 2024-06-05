package com.backendgip.security.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendgip.security.models.Item;
import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.MenuResponse;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.SubItem;
import com.backendgip.security.models.SubItemRol;
import com.backendgip.security.models.SubmenuRol;

@Service
public class ArmaMenuRol {

	@Autowired
	ISubmenuRolService submenuRolService;

	@Autowired
	IItemRolService iItemRolService;

	@Autowired
	ISubItemRolService iSubItemRolService;

	MenuResponse menuResponse;

	SubmenuRol submenuRol;

	List<SubmenuRol> listaSubmenus;

	List<MenuResponse> listaMenus;

	List<Item> listaItems;

	List<SubItem> listaSubItems;

	public List<MenuResponse> armarMenu(RolSeg rol) throws Exception {
		listaSubmenus = submenuRolService.buscarOpcionesRol(rol);
		listaMenus = new ArrayList<>();
		
		for (SubmenuRol submenuRol : listaSubmenus) {
			menuResponse = new MenuResponse();
			menuResponse.setIcon(submenuRol.getSubmenu().getIcon());
			menuResponse.setLabel(submenuRol.getSubmenu().getLabel());
			menuResponse.setLink(submenuRol.getSubmenu().getLink());
	
			listaItems = new ArrayList<>();
	
			for (ItemRol i : submenuRol.getItemRol()) {
				Item item = i.getItem();
				listaSubItems = new ArrayList<>();
	
				for (SubItemRol j : i.getSubItemRol()) {
					listaSubItems.add(j.getSubItem());
				}
				item.setSubItems(listaSubItems); 
				listaItems.add(item);
			}
	
			menuResponse.setSubMenus(listaItems); 
			listaMenus.add(menuResponse);
		}
	
		return listaMenus;
	}
	

	public void eliminarOpciones(RolSeg Rol) throws Exception {

		for (SubmenuRol submenuRol : Rol.getSubmenuRoles()) {

			for (ItemRol itemRol: submenuRol.getItemRol()){
				iSubItemRolService.eliminarSubItemsPorItem(itemRol);
			}

			iItemRolService.eliminarItemsPorSubmenu(submenuRol);
			
		}
		submenuRolService.eliminarSubmenuPorRol(Rol);
	}
}
