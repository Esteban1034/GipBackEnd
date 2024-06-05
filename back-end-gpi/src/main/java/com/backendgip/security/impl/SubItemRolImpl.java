package com.backendgip.security.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.ISubItemRolDao;
import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubItemRol;
import com.backendgip.security.services.ISubItemRolService;


@Service
public class SubItemRolImpl implements ISubItemRolService {


	@Autowired
	ISubItemRolDao subItemRolDao;
	
	@Override
	public List<SubItemRol> listar() throws Exception {
		return (List<SubItemRol>) subItemRolDao.findAll();
	}


	@Override
	public void eliminar(SubItemRol subItemRol) throws Exception {
		subItemRolDao.delete(subItemRol);		
	}


	@Override
	public void eliminarSubItemsPorItem(ItemRol itemRol) throws Exception {
		subItemRolDao.eliminarSubItemsPorItem(itemRol);
	}
	
}
