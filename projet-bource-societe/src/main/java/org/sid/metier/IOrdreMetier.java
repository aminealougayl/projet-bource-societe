package org.sid.metier;

import java.util.List;

import org.sid.entities.Ordre;
import org.springframework.data.domain.Page;

public interface IOrdreMetier {
	List<Ordre> findAll();
	Page<Ordre> findAll(int page,int size);
	List<Ordre> findAll(String code);
	Page<Ordre> findAll(String code,int page,int size);
	Page<Ordre> findAllAchats(String code,int page,int size);
	Page<Ordre> findAllVentes(String code,int page,int size);
	List<Ordre> findAllAchats(String code);
	List<Ordre> findAllVentes(String code);
	Ordre get(Long num);
	Ordre save(Ordre o);
	Ordre update(Ordre o);
	void delete(Long num);
	Page<Ordre> findByPrix(Double p,int page,int size);
	List<Ordre> findByPrix(Double p);
	Long totalVentes(String code);
	Long totalAchats(String code);
	Double avgPrixVentes(String code);
	Double avgPrixAchats(String code);
	Double estimation(String code);
}
