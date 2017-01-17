package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Societe implements Serializable{
	
	@Id
	private String code;
	private String nom;
	/*@OneToMany(mappedBy="societe",fetch=FetchType.LAZY)
	private Collection<Ordre> listeOrdres;*/
	
	public Societe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Societe(String code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	/*public Collection<Ordre> getListeOrdres() {
		return listeOrdres;
	}
	public void setListeOrdres(Collection<Ordre> listeOrdres) {
		this.listeOrdres = listeOrdres;
	}*/
	
	
}
