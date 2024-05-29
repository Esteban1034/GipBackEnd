//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.Ufs;
import com.backendgip.repository.UfsRepository;
import com.backendgip.service.UfsService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UfsServiceImp implements UfsService  {
	@Autowired
	private UfsRepository ufsRepository;

	public UfsServiceImp() {
	}

	public List<Ufs> getUfs() {
		return (List) this.ufsRepository.findAll();
	}

	public Ufs saveUfs(Ufs ufs) {
		return (Ufs) this.ufsRepository.save(ufs);
	}

	public void deleteUfs(Ufs ufs) {
		this.ufsRepository.delete(ufs);
	}

	public Ufs getUfsById(Integer idUfs) {
		return (Ufs) this.ufsRepository.findById(idUfs).orElseThrow(() -> {
			return new ResourceNotFoundException("La unidad funcional no existe con el ID" + idUfs);
		});
	}

}
