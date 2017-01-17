package org.sid.service;

import java.rmi.RemoteException;
import java.util.List;

import org.sid.entities.Ordre;
import org.sid.entities.Societe;
import org.sid.metier.IOrdreMetier;
import org.sid.metier.ISocieteMetier;
import org.sid.rmi.ISocieteRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocieteRMIService implements ISocieteRemote {
	
	@Autowired
	private ISocieteMetier ism;
	@Autowired
	private IOrdreMetier iom;

	@Override
	public Societe getSociete(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return ism.get(code);
	}
	@Override
	public Ordre getOrdre(Long num) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.get(num);
	}
	@Override
	public Societe saveSociete(Societe s) throws RemoteException {
		// TODO Auto-generated method stub
		return ism.save(s);
	}
	@Override
	public Ordre saveOrdre(Ordre o) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.save(o);
	}
	@Override
	public Long totalVentes(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.totalVentes(code);
	}
	@Override
	public Long totalAchats(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.totalAchats(code);
	}
	@Override
	public Double avgPrixVentes(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.avgPrixVentes(code);
	}
	@Override
	public Double avgPrixAchats(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.avgPrixAchats(code);
	}
	@Override
	public List<Societe> listeSocietes() throws RemoteException {
		// TODO Auto-generated method stub
		return ism.findAll();
	}
	@Override
	public List<Ordre> listeAchats(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.findAllAchats(code);
	}
	@Override
	public List<Ordre> listeVentes(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.findAllVentes(code);
	}
	@Override
	public List<Societe> search(String mc) {
		// TODO Auto-generated method stub
		return ism.findByMC(mc);
	}
	@Override
	public List<Ordre> listeOrdres(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.findAll(code);
	}
	@Override
	public Double estimation(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return iom.estimation(code);
	}
}
