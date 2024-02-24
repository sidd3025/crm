package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RemarksLeadService {

	
	@Autowired
	private RemarksLeadrepo remarkrepo;
	
	
	public List< RemarksLeadModel> listAll() {
		return remarkrepo.findAll();
	}
	 
	public  RemarksLeadModel get(long id) {
		return remarkrepo.findById(id).get();
	}
	 
	public void save( RemarksLeadModel std) {
		remarkrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		remarkrepo.deleteById(id);
	}
	
	public List<RemarksLeadModel> getFilterByLead(String lead_id){
		return remarkrepo.getFilterByLead(lead_id);
	}
	
	public List<RemarksLeadModel> getFilterByLeadID(String lead_id){
        return remarkrepo.getFilterByLead(lead_id);	
	}
}
