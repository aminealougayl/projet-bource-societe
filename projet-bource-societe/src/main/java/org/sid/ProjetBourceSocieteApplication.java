package org.sid;

import java.util.Date;

import org.sid.entities.Achat;
import org.sid.entities.Ordre;
import org.sid.entities.Societe;
import org.sid.entities.Vente;
import org.sid.metier.IOrdreMetier;
import org.sid.metier.ISocieteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan
@ImportResource("spring-config.xml")
@EnableAutoConfiguration
public class ProjetBourceSocieteApplication implements CommandLineRunner{

	@Autowired
	private ISocieteMetier ism;
	@Autowired
	private IOrdreMetier iom;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetBourceSocieteApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	//Test des societes
		//Ajouter les societes
		/*Societe sc1=new Societe("SC1","HP");
		ism.save(sc1);
		Societe sc2=new Societe("SC2","Samsung");
		ism.save(sc2);
		Societe sc3=new Societe("SC3","Appel");
		ism.save(sc3);
		Societe sc4=new Societe("SC4","MicroSoft");
		ism.save(sc4);
		Societe sc5=new Societe("SC5","Dell");
		ism.save(sc5);
		Societe sc6=new Societe("SC6","IBM");
		ism.save(sc6);*/

		//Afficher les societes
		System.out.println("-------------- Tous les societes --------------");
		ism.findAll().forEach(s->System.out.println(s.getCode() +" => "+s.getNom()));
		System.out.println("-------------- Page 1 de 10 societes --------------");
		ism.findAll(0,10).forEach(s->System.out.println(s.getCode() +" => "+s.getNom()));
	
	//Test des ordres
		//Ajouter les ordres
			/*//Pour HP
			Ordre or1=new Achat(new Date(),3000,100,sc1);
			iom.save(or1);
			Ordre or2=new Vente(new Date(),200,60,sc1);
			iom.save(or2);
			Ordre or12=new Vente(new Date(),10,40,sc1);
			iom.save(or12);
			Ordre or3=new Achat(new Date(),1000,90,sc1);
			iom.save(or3);
			//Pour Samsung
			Ordre or4=new Achat(new Date(),600,50,sc2);
			iom.save(or4);
			Ordre or13=new Achat(new Date(),90,10,sc2);
			iom.save(or13);
			Ordre or5=new Vente(new Date(),1500,70,sc2);
			iom.save(or5);
			//Pour MicroSoft
			Ordre or6=new Achat(new Date(),3500,100,sc4);
			iom.save(or6);
			Ordre or7=new Vente(new Date(),200,90,sc4);
			iom.save(or7);
			Ordre or8=new Achat(new Date(),700,200,sc4);
			iom.save(or8);
			Ordre or9=new Vente(new Date(),1900,50,sc4);
			iom.save(or9);
			Ordre or10=new Vente(new Date(),900,20,sc4);
			iom.save(or10);
			Ordre or11=new Achat(new Date(),2500,60,sc4);
			iom.save(or11);*/
	
		//Afficher les ordres
			System.out.println("-------------- Tous les ordres --------------");
			iom.findAll().forEach(o->System.out.println(o.getNum() +" => Date: "+o.getDate()
				+" Nbre d'actions: "+o.getNbreAction()+" Prix d'action: "+o.getPrixAction()+" societe: "+o.getSociete().getNom()));
	
			System.out.println("-------------- Page 2 de 5 ordres --------------");
			iom.findAll(1,10).forEach(o->System.out.println(o.getNum() +" => Date: "+o.getDate()
				+" Nbre d'actions: "+o.getNbreAction()+" Prix d'action: "+o.getPrixAction()+" societe: "+o.getSociete().getNom()));
		//Afficher les Achats de HP
			/*System.out.println("-------------- Achats de HP (Page 1 de 2 Achats) --------------");
			iom.findAllAchats("SC1",0,2).forEach(o->System.out.println(o.getNum() +" => Date: "+o.getDate()
					+" Nbre d'actions: "+o.getNbreAction()+" Prix d'action: "+o.getPrixAction()+" societe: "+o.getSociete().getNom()));
			 
		//Afficher les Vents de MS
		System.out.println("-------------- Ventes de Microsoft (Page 1 de 2 Ventes) --------------");
		iom.findAllVentes("SC4",0,2).forEach(o->System.out.println(o.getNum() +" => Date: "+o.getDate()
				+" Nbre d'actions: "+o.getNbreAction()+" Prix d'action: "+o.getPrixAction()+" societe: "+o.getSociete().getNom()));
		//Afficher total des Ventes de Microsoft
		System.out.println("------ Afficher total des Ventes de Microsoft : "+iom.totalVentes("SC4").toString());
		//Afficher total des Achats de HP
		System.out.println("------ Afficher total des Achats de HP : "+iom.totalAchats("SC1").toString());
		//Afficher le moyenne de prix des Achats de Samsung
		System.out.println("------ Afficher le moyenne de prix des Achats de Samsung : "+iom.avgPrixAchats("SC2").toString());
		//Afficher le moyenne de prix des Ventes de HP
		System.out.println("------ Afficher le moyenne de prix des Ventes de HP : "+iom.avgPrixVentes("SC1").toString());
		
		*/
	}
}
