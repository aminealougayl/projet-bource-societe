package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Vente")
public class Vente extends Ordre {

	public Vente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vente(Date date, int nbreAction, double prixAction, Societe societe) {
		super(date, nbreAction, prixAction, societe);
		// TODO Auto-generated constructor stub
	}
	
	public Vente(Date date, int nbreAction, double prixAction) {
		super(date, nbreAction, prixAction);
		// TODO Auto-generated constructor stub
	}
	
	
}
