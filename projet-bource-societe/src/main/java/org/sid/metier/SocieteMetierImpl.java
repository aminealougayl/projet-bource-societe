package org.sid.metier;

import java.util.List;

import org.sid.dao.SocieteRepository;
import org.sid.entities.Societe;
import org.sid.service.SendAMQPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SocieteMetierImpl implements ISocieteMetier {

	@Autowired
	private SocieteRepository sr;
	
	@Autowired
	private SendAMQPService amqpService;
	
	@Override
	public List<Societe> findAll() {
		// TODO Auto-generated method stub
		return sr.findAll();
	}

	@Override
	public Societe get(String code) {
		// TODO Auto-generated method stub
		return sr.findOne(code);
	}

	@Override
	public Societe save(Societe s) {
		// TODO Auto-generated method stub
		try {
			amqpService.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sr.save(s);
	}

	@Override
	public Page<Societe> findByMC(String mc,int page,int size) {
		// TODO Auto-generated method stub
		return sr.findByMC(mc,new PageRequest(page, size));
	}


	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		sr.delete(code);
	}

	@Override
	public Page<Societe> findAll(int page,int size) {
		// TODO Auto-generated method stub
		return sr.findAll(new PageRequest(page, size));
	}

	@Override
	public Societe update(Societe s) {
		// TODO Auto-generated method stub
		return sr.saveAndFlush(s);
	}

	@Override
	public List<Societe> findByMC(String mc) {
		// TODO Auto-generated method stub
		return sr.findByMC(mc);
	}

	
}
