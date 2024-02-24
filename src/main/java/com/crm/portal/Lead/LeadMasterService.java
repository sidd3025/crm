package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeadMasterService {
	
	@Autowired
	private LeadMasterRepository repomaster;
	 
	public List<LeadMaster> listAll() {
		return repomaster.findAll();
	}
	 
	public LeadMaster get(long id) {
		return repomaster.findById(id).get();
	}
	 
	public void save(LeadMaster std) {
		repomaster.save(std);
	}
	 
	public void deleteAuthority(long id) {
		repomaster.deleteById(id);
	}


}
