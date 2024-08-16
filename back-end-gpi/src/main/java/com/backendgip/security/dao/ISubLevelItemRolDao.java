package com.backendgip.security.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubItemRol;
import com.backendgip.security.models.SubLevelItemRol;



public interface ISubLevelItemRolDao extends CrudRepository<SubLevelItemRol, Long>{


	@Modifying
	@Transactional
	@Query("delete from SubLevelItemRol where subItemRol=?1")
	public void eliminarSubLevelPorSubItem(SubItemRol subItemRol) throws Exception;
	
}
