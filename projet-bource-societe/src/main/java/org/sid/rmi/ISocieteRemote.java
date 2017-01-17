package org.sid.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.sid.entities.Ordre;
import org.sid.entities.Societe;

public interface ISocieteRemote extends Remote{
	public List<Societe> listeSocietes() throws RemoteException;
	public List<Societe> search(String mc) throws RemoteException;
	public Societe getSociete(String code) throws RemoteException;
	public Societe saveSociete(Societe s) throws RemoteException;
	public Ordre getOrdre(Long num) throws RemoteException;
	public Ordre saveOrdre(Ordre o) throws RemoteException;
	public List<Ordre> listeOrdres(String code) throws RemoteException;
	public List<Ordre> listeAchats(String code) throws RemoteException;
	public List<Ordre> listeVentes(String code) throws RemoteException;
	public Long totalVentes(String code) throws RemoteException;
	public Long totalAchats(String code) throws RemoteException;
	public Double avgPrixVentes(String code) throws RemoteException;
	public Double avgPrixAchats(String code) throws RemoteException;
	public Double estimation(String code) throws RemoteException;
}
