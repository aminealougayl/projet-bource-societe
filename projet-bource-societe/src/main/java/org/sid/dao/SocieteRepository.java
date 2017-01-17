package org.sid.dao;

import java.util.List;

import org.sid.entities.Societe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocieteRepository extends JpaRepository<Societe,String>{

	@Query("select s from  Societe s where s.nom like :nom")
	public Page<Societe> findByMC(@Param("nom")String nom,Pageable pageable);
	@Query("select s from  Societe s where s.nom like :nom")
	public List<Societe> findByMC(@Param("nom")String nom);
	
}
