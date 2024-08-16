package com.backendgip.security.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendgip.security.dao.ISubItemRolDao;
import com.backendgip.security.dao.ISubLevelItemRolDao;
import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubItemRol;
import com.backendgip.security.models.SubLevelItemRol;
import com.backendgip.security.services.ISubItemRolService;
import com.backendgip.security.services.ISubLevelItemRolService;


@Service
public class SubLevelItemRolImp implements ISubLevelItemRolService {


	@Autowired
	ISubLevelItemRolDao subLevelItemRolDao;
	
	@Override
	public List<SubLevelItemRol> listar() throws Exception {
		return (List<SubLevelItemRol>) subLevelItemRolDao.findAll();
	}


	@Override
	public void eliminar(SubLevelItemRol subLevelItemRol) throws Exception {
		subLevelItemRolDao.delete(subLevelItemRol);		
	}


	@Override
	public void eliminarSubLevelPorSubItem(SubItemRol subItemRol) throws Exception {
		subLevelItemRolDao.eliminarSubLevelPorSubItem(subItemRol);
	}
	
}
