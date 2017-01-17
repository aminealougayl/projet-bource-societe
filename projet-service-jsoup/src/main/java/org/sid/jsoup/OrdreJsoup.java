package org.sid.jsoup;

import java.rmi.Naming;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sid.entities.Achat;
import org.sid.entities.Societe;
import org.sid.entities.Vente;
import org.sid.rmi.ISocieteRemote;

public class OrdreJsoup {
	
	public static void main(String[] args) throws Exception {
		
		ISocieteRemote stub=(ISocieteRemote) Naming.lookup("rmi://localhost:1099/SC");
		
		List<Societe> societes= stub.listeSocietes();
		
		for(Societe s:societes){
			
			//Chargement la page qui contient l'ordres de societe
			Document docOr = Jsoup.connect("https://www.wafabourse.com/trader/market/"+s.getCode()+"/XCAS/ISIN").get();
			
			//Selection des achats
			Elements achats =docOr.select("table:has(th.th_achat)").select("table.t-data-grid").select("tr:has(td.bidNbOrders)");
			
			for(Element a: achats){
				
				if(!a.select(".bidNbOrders").text().equals("-") && !isDouble(a.select(".bidPrice").text()) && !a.select(".bidVolume").text().equals("-")){
					NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number nbrVolume=format.parse(a.select(".bidVolume").text());
					Number nbrNbreAction=format.parse(a.select(".bidNbOrders").text());
					Number nbrPrix = format.parse(a.select(".bidPrice").text());
					
					int volume=nbrVolume.intValue();
					int nbreAction= nbrNbreAction.intValue() * volume;
				    double prixAction = nbrPrix.doubleValue();
				    
				    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date dateAction = formatter.parse(docOr.select(".lastPriceDateTime").not(".t-first").text());
					
					Achat ach=new Achat(dateAction, nbreAction, prixAction, s);
					stub.saveOrdre(ach);
					//System.out.println(ach.getNbreAction() + "  " +ach.getPrixAction()+ "  "+ach.getDate());
				}
			}
			
			System.out.println(" Fin chargement des achats de "+s.getNom()+" !!");
			
			//Selection des ventes
			Elements ventes =docOr.select("table:has(th.th_vente)").select("table.t-data-grid").select("tr:has(td.askNbOrders)");
			
			for(Element v: ventes){
				
				if(!v.select(".askNbOrders").text().equals("-") && !isDouble(v.select(".askPrice").text()) && !v.select(".askVolume").text().equals("-") ){
					NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number nbrVolume=format.parse(v.select(".askVolume").text());
					Number nbrNbreAction=format.parse(v.select(".askNbOrders").text());
					Number nbrPrix = format.parse(v.select(".askPrice").text());
					
					int volume=nbrVolume.intValue();					
					int nbreAction=nbrNbreAction.intValue() * volume;
				    double prixAction = nbrPrix.doubleValue();
				    
				    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date dateAction = formatter.parse(docOr.select(".lastPriceDateTime").not(".t-first").text());
					
					Vente ven=new Vente(dateAction, nbreAction, prixAction, s);
					stub.saveOrdre(ven);
					//System.out.println(ven.getNbreAction() + "  " +ven.getPrixAction()+ "  "+ven.getDate());
				}
			}
			
			System.out.println(" Fin chargement des ventes de "+s.getNom()+"  !!");
			
		}
		System.out.println(" Fin chargement des ordres  !! ");
	}
	
	//Cette Methode pour tester si les valeurs recupré contient des nombre decimal 
	private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
