package com.crm.portal.Lead;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeadService {
	
	@Autowired
	private LeadRepository repo;
	 
	public List<Lead> listAll() {
		return repo.findAll();
	}
	 
	public Lead get(long id) {
		return repo.findById(id).get();
	}
	 
	public void save(Lead std) {
		repo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		repo.deleteById(id);
	}
	public List<Lead> GetIDById(Long employee_id) {
		return repo.GetIDById(employee_id);
		}
	public Integer getLeadID(int lead_id) {
		return repo.getLeadID(lead_id);
	}
	public List<Lead> getFilterByLeadtoContactID(String contactid){
		return repo.getFilterByLeadtoContactID(contactid);
	}
	
	 public List<Lead> getAllDocuments() {
	        return repo.findAll();
	    }
	 public Integer UpdateLead(String emailid) {
		 return repo.UpdateLead(emailid);
	 }
	 
	 public Lead getFilterByLeadAssigntoContactID(Integer contactid){
		 return repo.getFilterByLeadAssigntoContactID(contactid);
	 }
}
