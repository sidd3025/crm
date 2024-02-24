package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IndService {
	
	@Autowired
	private IndRepository ind;
	
	
	public List<IndustryMaster> listAll() {
		return ind.findAll();
	}
	 
	public IndustryMaster get(long id) {
		return ind.findById(id).get();
	}
	 
	public void save(IndustryMaster std) {
		ind.save(std);
	}
	 
	public void deleteAuthority(long id) {
		ind.deleteById(id);
	}
}
