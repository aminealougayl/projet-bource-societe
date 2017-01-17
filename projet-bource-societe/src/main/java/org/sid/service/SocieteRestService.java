package org.sid.service;

import java.util.List;

import org.sid.entities.Achat;
import org.sid.entities.Ordre;
import org.sid.entities.Societe;
import org.sid.entities.Vente;
import org.sid.metier.IOrdreMetier;
import org.sid.metier.ISocieteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@CrossOrigin("*")
@RestController
public class SocieteRestService {
	@Autowired
	private ISocieteMetier ism;
	@Autowired
	private IOrdreMetier iom;
	
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<Societe> listeSocietes(){
		return ism.findAll();
	}
	@RequestMapping(value="/societes",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public Societe saveSociete(@RequestBody Societe s){
		return ism.save(s);
	}
	@RequestMapping(value="/societes",method=RequestMethod.GET)
	public Page<Societe> pageSocietes(int page,int size){
		return ism.findAll(page, size);
	}
	@RequestMapping(value="/societes/{code}",method=RequestMethod.GET)
	public Societe getSociete(@PathVariable("code")String code){
		return ism.get(code);
	}
	@RequestMapping(value="/societes/{code}",method=RequestMethod.PUT)
	public Societe updateSociete(@PathVariable("code")String code, @RequestBody Societe s){
		s.setCode(code);
		return ism.update(s);
	}
	@RequestMapping(value="/societes/{code}",method=RequestMethod.DELETE)
	public boolean deleteSociete(@PathVariable("code") String code){
		ism.delete(code);
		return true;
	}
	
	@RequestMapping(value="/societes/search", params = {"q"}, method=RequestMethod.GET)
	public Page<Societe> societesParMC(@RequestParam(value="q")String mc,int page,int size){
		return ism.findByMC("%"+mc+"%",page, size);
	}
	
	@RequestMapping(value="/societes/{code}/achats",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Ordre saveAchat(@PathVariable("code")String code,@RequestBody Achat a){
		a.setSociete(ism.get(code));
		return iom.save(a);
	}
	@RequestMapping(value="/societes/{code}/ventes",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Ordre saveVente(@PathVariable("code")String code,@RequestBody Vente v){
		v.setSociete(ism.get(code));
		return iom.save(v);
	}
	@RequestMapping(value="/societes/{code}/ordres",method=RequestMethod.GET)
	public Page<Ordre> findAllOrdres(@PathVariable("code")String code,int page,int size){
		return iom.findAll(code, page, size);
	}
	@RequestMapping(value="/societes/{code}/all",method=RequestMethod.GET)
	public List<Ordre> listeOrdres(@PathVariable("code")String code){
		return iom.findAll(code);
	}
	@RequestMapping(value="/societes/{code}/achats",method=RequestMethod.GET)
	public Page<Ordre> findAllAchats(@PathVariable("code")String code,int page,int size){
		return iom.findAllAchats(code, page, size);
	}
	@RequestMapping(value="/societes/{code}/ventes",method=RequestMethod.GET)
	public Page<Ordre> findAllVentes(@PathVariable("code")String code,int page,int size){
		return iom.findAllVentes(code, page, size);
	}
	@RequestMapping(value="/achats/{num}",method=RequestMethod.PUT)
	public Ordre updateAchat(@PathVariable("code")String code,@PathVariable("num")Long num,@RequestBody Achat a){
		a.setNum(num);
		a.setSociete(ism.get(code));
		return iom.update(a);
	}
	@RequestMapping(value="/ventes/{num}",method=RequestMethod.PUT)
	public Ordre updateVente(@PathVariable("code")String code,@PathVariable("num")Long num,@RequestBody Vente v){
		v.setNum(num);
		v.setSociete(ism.get(code));
		return iom.update(v);
	}
	@RequestMapping(value="/ordres/{num}",method=RequestMethod.DELETE)
	public void deleteOrdre(@PathVariable("num")Long num){
		iom.delete(num);
	}
	@RequestMapping(value="/ordres/{num}",method=RequestMethod.GET)
	public Ordre getOrdre(@PathVariable("num")Long num){
		return iom.get(num);
	}
	@RequestMapping("/societes/{code}/achats/total")
	public Long totalAchats(@PathVariable("code")String code){
		return iom.totalAchats(code);
	}
	@RequestMapping("/societes/{code}/ventes/total")
	public Long totalVentes(@PathVariable("code")String code){
		return iom.totalVentes(code);
	}
	@RequestMapping("/societes/{code}/achats/avgPrix")
	public Double avgPrixAchats(@PathVariable("code")String code){
		return iom.avgPrixAchats(code);
	}
	@RequestMapping("/societes/{code}/ventes/avgPrix")
	public Double avgPrixVentes(@PathVariable("code")String code){
		return iom.avgPrixVentes(code);
	}
	@RequestMapping("/societes/{code}/estimation")
	public Double estimationVentes(@PathVariable("code")String code){
		return iom.estimation(code);
	}

}
