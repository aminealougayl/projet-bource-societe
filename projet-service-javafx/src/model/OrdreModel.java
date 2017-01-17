package model;

import java.util.Date;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OrdreModel {
	private LongProperty num;
    private DoubleProperty prix;
    private ObjectProperty<Date> date;
    private IntegerProperty nbre;
	public OrdreModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrdreModel(Long num, Double prix,
			Date date, Integer nbre) {
		super();
		this.num = new SimpleLongProperty(num);
		this.prix = new SimpleDoubleProperty(prix);
		this.date = new SimpleObjectProperty<Date>(date);
		this.nbre = new SimpleIntegerProperty(nbre);
	}
	public LongProperty getNumProperty() {
		return num;
	}
	public Long getNum() {
		return num.get();
	}
	public void setNum(Long num) {
		this.num.set(num);
	}
	public DoubleProperty getPrixProperty() {
		return prix;
	}
	public Double getPrix() {
		return prix.get();
	}
	public void setPrix(Double prix) {
		this.prix.set(prix);
	}
	public ObjectProperty<Date> getDateProperty() {
		return date;
	}
	public Date getDate() {
		return date.get();
	}
	public void setDate(Date date) {
		this.date.set(date);
	}
	public IntegerProperty getNbreProperty() {
		return nbre;
	}
	public Integer getNbre() {
		return nbre.get();
	}
	public void setNbre(Integer nbre) {
		this.nbre.set(nbre);
	}
   
	
    
    
    
}
