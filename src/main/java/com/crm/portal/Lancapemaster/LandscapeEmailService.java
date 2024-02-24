package com.crm.portal.Lancapemaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Deals.Deal;
import com.crm.portal.Deals.DealRepository;


@Service
@Transactional
public class LandscapeEmailService {

	
	@Autowired
	private LandscapeEmail_Repo emailrepo;
	
	public List<LandscapeEmail> listAll() {
		return emailrepo.findAll();
	}
	 
	public LandscapeEmail get(long id) {
		return emailrepo.findById(id).get();
	}
	 
	public void save(LandscapeEmail std) {
		emailrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		emailrepo.deleteById(id);
	}
}
