//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.service;
import com.backendgip.model.Ufs;
import java.time.LocalDate;
import java.util.List;

public interface UfsService {
	List<Ufs> getUfs();

	Ufs saveUfs(Ufs ufs);

	void deleteUfs(Ufs ufs);

	Ufs getUfsById(Integer idUfs);

}
