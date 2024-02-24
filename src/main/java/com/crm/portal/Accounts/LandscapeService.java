package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LandscapeService {

	

	@Autowired
	private LandscapeRepo landrepo;
	 
	public List<Landscape> listAll() {
		return landrepo.findAll();
	}
	 
	public Landscape get(long id) {
		return landrepo.findById(id).get();
	}
	 
	public void save(Landscape std) {
		landrepo.save(std);
	}
	
}
