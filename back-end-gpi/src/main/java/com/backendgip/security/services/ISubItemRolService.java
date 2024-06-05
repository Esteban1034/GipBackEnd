package com.backendgip.security.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubItemRol;

@Service
public interface ISubItemRolService  {


	public List<SubItemRol> listar() throws Exception;
	
	public void eliminar(SubItemRol itemRol) throws Exception;
	
	public void eliminarSubItemsPorItem(ItemRol itemRol) throws Exception;

}
