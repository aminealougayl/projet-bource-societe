package org.sid.jsoup;

import java.rmi.Naming;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sid.entities.Societe;
import org.sid.rmi.ISocieteRemote;

public class SocieteJsoup {

	public static void main(String[] args) throws Exception {
		
		ISocieteRemote stub=(ISocieteRemote) Naming.lookup("rmi://localhost:1099/SC");
		
		Document docSc = Jsoup.connect("https://www.wafabourse.com/marches/actions/r").get();
		Elements elts = docSc.select(".longNameQS a");
		for(Element a: elts){
			String code=a.attr("href").split("/")[3];
			String nom=a.text();
			Societe s=new Societe(code,nom);
			stub.saveSociete(s);
		}
		
		System.out.println(" Fin chargement de societes  !! ");
	}

}
