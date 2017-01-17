package org.sid.metier;

import java.util.List;

import org.sid.entities.Societe;
import org.springframework.data.domain.Page;

public interface ISocieteMetier {
	List<Societe> findAll();
	Page<Societe> findAll(int page,int size);
	Page<Societe> findByMC(String mc,int page,int size);
	List<Societe> findByMC(String mc);
	Societe get(String code);
	Societe save(Societe s);
	Societe update(Societe s);
	void delete(String code);
}
