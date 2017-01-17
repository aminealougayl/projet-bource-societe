package org.sid.metier;

import java.util.List;

import org.sid.dao.OrdreRepository;
import org.sid.entities.Ordre;
import org.sid.service.SendAMQPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrdreMetierImpl implements IOrdreMetier{

	@Autowired
	private OrdreRepository or; 
	
	@Autowired
	private SendAMQPService amqpService;
			
	@Override
	public List<Ordre> findAll() {
		// TODO Auto-generated method stub
		return or.findAll();
	}

	@Override
	public Ordre get(Long num) {
		// TODO Auto-generated method stub
		return or.findOne(num);
	}

	@Override
	public void delete(Long num) {
		// TODO Auto-generated method stub
		or.delete(num);
	}

	@Override
	public Page<Ordre> findAllAchats(String code,int page,int size) {
		// TODO Auto-generated method stub
		return or.pageAchats(code,new PageRequest(page, size));
	}

	@Override
	public Page<Ordre> findAllVentes(String code,int page,int size) {
		// TODO Auto-generated method stub
		return or.pageVentes(code,new PageRequest(page, size));
	}

	@Override
	public Page<Ordre> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return or.findAll(new PageRequest(page, size));
	}

	@Override
	public Long totalVentes(String code) {
		// TODO Auto-generated method stub
		return or.totalVentes(code);
	}

	@Override
	public Long totalAchats(String code) {
		// TODO Auto-generated method stub
		return or.totalAchats(code);
	}

	@Override
	public Double avgPrixVentes(String code) {
		// TODO Auto-generated method stub
		return or.avgPrixVentes(code);
	}

	@Override
	public Double avgPrixAchats(String code) {
		// TODO Auto-generated method stub
		return or.avgPrixAchats(code);
	}

	@Override
	public Page<Ordre> findAll(String code, int page, int size) {
		// TODO Auto-generated method stub
		return or.findAll(code, new PageRequest(page, size));
	}

	@Override
	public List<Ordre> findAll(String code) {
		// TODO Auto-generated method stub
		return or.findAll(code);
	}

	@Override
	public Ordre save(Ordre o) {
		// TODO Auto-generated method stub
		try {
			amqpService.setMessage("New Ordre!");
			amqpService.setQUEUE_NAME("ordre_queue");
			amqpService.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return or.save(o);
	}

	@Override
	public Ordre update(Ordre o) {
		// TODO Auto-generated method stub
		return or.saveAndFlush(o);
	}

	@Override
	public List<Ordre> findAllAchats(String code) {
		// TODO Auto-generated method stub
		return or.listeAchats(code);
	}

	@Override
	public List<Ordre> findAllVentes(String code) {
		// TODO Auto-generated method stub
		return or.listeVentes(code);
	}

	@Override
	public Double estimation(String code) {
		// TODO Auto-generated method stub
		return or.estimation(code);
	}

	@Override
	public Page<Ordre> findByPrix(Double p, int page, int size) {
		// TODO Auto-generated method stub
		return or.findByPrix(p,new PageRequest(page, size));
	}

	@Override
	public List<Ordre> findByPrix(Double p) {
		// TODO Auto-generated method stub
		return or.findByPrix(p);
	}
	
}
