package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SocieteModel {
	private StringProperty code;
    private StringProperty nom;
    private DoubleProperty totalVente;
    private DoubleProperty totalAchat;
    private DoubleProperty prixVente;
    private DoubleProperty prixAchat;
    private DoubleProperty prixEstm;
    
    public SocieteModel(String code, String nom) {
        this.code = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
    }

	public SocieteModel() {
	}

	public StringProperty getCodeProperty() {
		return code;
	}
	public String getCode() {
		return code.get();
	}
	public void setCode(String code) {
		this.code.set(code);
	}

	public String getNom() {
		return nom.get();
	}
	public StringProperty getNomProperty() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public DoubleProperty getTotalVenteProperty() {
		return totalVente;
	}
	public Double getTotalVente() {
		return totalVente.get();
	}
	public void setTotalVente(double totalVente) {
		this.totalVente.set(totalVente);
	}

	public DoubleProperty getTotalAchatProperty() {
		return totalAchat;
	}
	public Double getTotalAchat() {
		return totalAchat.get();
	}
	public void setTotalAchat(double totalAchat) {
		this.totalAchat.set(totalAchat);
	}

	public DoubleProperty getPrixVenteProperty() {
		return prixVente;
	}
	public double getPrixVente() {
		return prixVente.get();
	}
	public void setPrixVente(double prixVente) {
		this.prixVente.set(prixVente);
	}

	public DoubleProperty getPrixAchatProperty() {
		return prixAchat;
	}
	public double getPrixAchat() {
		return prixAchat.get();
	}
	public void setPrixAchat(double prixAchat) {
		this.prixAchat.set(prixAchat);
	}

	public DoubleProperty getPrixEstmProperty() {
		return prixEstm;
	}
	public double getPrixEstm() {
		return prixEstm.get();
	}
	public void setPrixEstm(double prixEstm) {
		this.prixEstm.set(prixEstm);
	}
	
	
    
    
}
