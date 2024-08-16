package com.backendgip.security.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubItemRol;
import com.backendgip.security.models.SubLevelItemRol;

@Service
public interface ISubLevelItemRolService  {


	public List<SubLevelItemRol> listar() throws Exception;
	
	public void eliminar(SubLevelItemRol itemRol) throws Exception;
	
	public void eliminarSubLevelPorSubItem(SubItemRol subItemRol) throws Exception;

}
