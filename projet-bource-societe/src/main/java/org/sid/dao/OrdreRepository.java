package org.sid.dao;

import java.util.List;

import org.sid.entities.Ordre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdreRepository extends JpaRepository<Ordre,Long> {

	@Query("select o from  Ordre o where o.societe.code = :code")
	public Page<Ordre> findAll(@Param("code")String code,Pageable pageable);
	@Query("select o from  Ordre o where o.societe.code = :code")
	public List<Ordre> findAll(@Param("code")String code);
	@Query("select o from  Ordre o where o.prixAction like :prix")
	public Page<Ordre> findByPrix(@Param("prix")Double prix,Pageable pageable);
	@Query("select o from  Ordre o where o.prixAction like :prix")
	public List<Ordre> findByPrix(@Param("prix")Double prix);
	@Query("select a from  Achat a where a.societe.code = :code")
	public Page<Ordre> pageAchats(@Param("code")String code,Pageable pageable);
	@Query("select v from  Vente v where v.societe.code = :code")
	public Page<Ordre> pageVentes(@Param("code")String code,Pageable pageable);
	@Query("select a from  Achat a where a.societe.code = :code")
	public List<Ordre> listeAchats(@Param("code")String code);
	@Query("select v from  Vente v where v.societe.code = :code")
	public List<Ordre> listeVentes(@Param("code")String code);
	@Query("select sum(v.nbreAction) from  Vente v where v.societe.code = :code")
	Long totalVentes(@Param("code")String code);
	@Query("select sum(a.nbreAction) from  Achat a where a.societe.code = :code")
	Long totalAchats(@Param("code")String code);
	@Query("select avg(v.prixAction) from  Vente v where v.societe.code = :code")
	Double avgPrixVentes(@Param("code")String code);
	@Query("select avg(a.prixAction) from  Achat a where a.societe.code = :code")
	Double avgPrixAchats(@Param("code")String code);
	@Query("select sum(o.prixAction*o.nbreAction)/sum(o.nbreAction) from  Ordre o where o.societe.code = :code")
	Double estimation(@Param("code")String code);
	
}
