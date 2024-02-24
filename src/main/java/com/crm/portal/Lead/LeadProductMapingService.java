package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeadProductMapingService {

	@Autowired
	private ProductmapingRepo productrepo;
	
	public List<LeadProductMaping> listAll() {
		return productrepo.findAll();
	}
	 
	public LeadProductMaping get(long id) {
		return productrepo.findById(id).get();
	}
	 
	public void save(LeadProductMaping std) {
		productrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		productrepo.deleteById(id);
	}
}
