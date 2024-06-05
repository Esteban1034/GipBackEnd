package com.backendgip.security.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.SubItemRol;



public interface ISubItemRolDao extends CrudRepository<SubItemRol, Long>{


	@Modifying
	@Transactional
	@Query("delete from SubItemRol where itemRol=?1")
	public void eliminarSubItemsPorItem(ItemRol itemRol) throws Exception;
	
}
