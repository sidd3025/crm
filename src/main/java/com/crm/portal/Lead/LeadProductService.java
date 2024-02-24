package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeadProductService {

	@Autowired
	private ProductmapingRepo maprepo;
	
	public List<LeadProductMaping> listAll() {
		return maprepo.findAll();
	}
	 
	public LeadProductMaping get(long id) {
		return maprepo.findById(id).get();
	}
	 
	public void save(LeadProductMaping std) {
		maprepo.save(std);
	}
	public void deleteAuthority(long id) {
		maprepo.deleteById(id);
	}
	List<LeadProductMaping> GetIDById(Integer leadid){
		return maprepo.GetIDById(leadid);
	}
	
	
}
