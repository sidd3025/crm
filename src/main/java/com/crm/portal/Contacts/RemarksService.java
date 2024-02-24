package com.crm.portal.Contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RemarksService {

	
	@Autowired
	private RemarksRepo remarkrepo;

	public List<RemarksModel> listAll() {
		return remarkrepo.findAll();
	}
	  
	public RemarksModel get(long id) {
		return remarkrepo.findById(id).get();
	}
	 
	public void save(RemarksModel std) {
		remarkrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		remarkrepo.deleteById(id);
	}
	public void save(List<RemarksModel> c) {
		remarkrepo.saveAll(c);
	}
	
	public List<RemarksModel> getFilterByContactID(String contact_id){
	  return remarkrepo.getFilterByContactID(contact_id);
	}
}
