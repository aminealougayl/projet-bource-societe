package org.sid.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.sid.entities.Achat;
import org.sid.entities.Ordre;
import org.sid.entities.Societe;
import org.sid.entities.Vente;
import org.sid.metier.IOrdreMetier;
import org.sid.metier.ISocieteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebService
public class SocieteSOAPService {
	@Autowired
	private ISocieteMetier ism;
	@Autowired
	private IOrdreMetier iom;
	
	@WebMethod(operationName="saveSociete")
	public Societe saveSociete(@WebParam(name="code")String code,
			@WebParam(name="nom")String nom){
		Societe sc=new Societe(code,nom);
		ism.save(sc);
		return sc;
	}
	@WebMethod
	public List<Societe> listeSocietes(){
		return ism.findAll();
	}
	@WebMethod
	public List<Societe> search(@WebParam(name="mc")String mc){
		return ism.findByMC("%"+mc+"%");
	}
	@WebMethod
	public Societe getSociete(@WebParam(name="code")String code){
		return ism.get(code);
	}
	@WebMethod
	public List<Ordre> listeOrdres(@WebParam(name="code")String code){
		return iom.findAll(code);
	}
	@WebMethod
	public List<Ordre> listeAchats(@WebParam(name="code")String code){
		return iom.findAllAchats(code);
	}
	@WebMethod
	public List<Ordre> listeVentes(@WebParam(name="code")String code){
		return iom.findAllVentes(code);
	}
	@WebMethod(operationName="saveAchat")
	public Ordre saveAchat(@WebParam(name="date")Date date,
			@WebParam(name="nbre")int nbre,
			@WebParam(name="prix")double prix,
			@WebParam(name="code")String code){
		Achat a=new Achat(date,nbre,prix);
		a.setSociete(ism.get(code));
		iom.save(a);
		return a;
	}
	@WebMethod(operationName="saveVente")
	public Ordre saveVente(@WebParam(name="date")Date date,
			@WebParam(name="nbre")int nbre,
			@WebParam(name="prix")double prix,
			@WebParam(name="code")String code){
		Vente v=new Vente(date,nbre,prix);
		v.setSociete(ism.get(code));
		iom.save(v);
		return v;
	}
	@WebMethod
	public Ordre geteOrdre(@WebParam(name="num")Long num){
		return iom.get(num);
	}
	
}
