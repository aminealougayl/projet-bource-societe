package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_Ordre")
@DiscriminatorValue("Ordre")
public abstract class Ordre implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long num;
	protected Date date;
	protected int nbreAction;
	protected double prixAction;
	@ManyToOne
	@JoinColumn(name="CODE_SC")
	protected Societe societe;
	
	public Ordre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ordre(Date date, int nbreAction, double prixAction) {
		super();
		this.date = date;
		this.nbreAction = nbreAction;
		this.prixAction = prixAction;
	}
	
	public Ordre(Date date, int nbreAction, double prixAction, Societe societe) {
		super();
		this.date = date;
		this.nbreAction = nbreAction;
		this.prixAction = prixAction;
		this.societe = societe;
	}
	
	
	

	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNbreAction() {
		return nbreAction;
	}
	public void setNbreAction(int nbreAction) {
		this.nbreAction = nbreAction;
	}
	public double getPrixAction() {
		return prixAction;
	}
	public void setPrixAction(double prixAction) {
		this.prixAction = prixAction;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	
	
}
