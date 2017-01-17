package org.sid.test;

import java.rmi.Naming;
import java.util.List;

import org.sid.entities.Societe;
import org.sid.rmi.ISocieteRemote;

public class ClientRMI {

	public static void main(String[] args) {
		try{
				ISocieteRemote stub=(ISocieteRemote) Naming.lookup("rmi://localhost:1099/SC");
				List<Societe> scs= stub.listeSocietes();
				
				System.out.println("Test \"Affichage des societes\":");
				for(Societe c:scs){
					System.out.println(c.getCode()+" => "+c.getNom() + " .. ");
				}
				
			}
		catch (Exception e) {
				e.printStackTrace();
			}
	}
}
