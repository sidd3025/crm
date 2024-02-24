package com.crm.portal.Contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Lead.EmpRepository;
import com.crm.portal.Lead.Employee;
import com.crm.portal.Lead.Lead;

@Service
@Transactional
public class ContactService {
	
	@Autowired
	private ContactRepository contactrepo;

	public List<Contact> listAll() {
		return contactrepo.findAll();
	}
	  
	public Contact get(long id) {
		return contactrepo.findById(id).get();
	}
	 
	public void save(Contact std) {
		contactrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		contactrepo.deleteById(id);
	}
	public void save(List<Contact> c) {
		contactrepo.saveAll(c);
	}
	public List<Contact> GetIDById(Long employee_id) {
		return contactrepo.GetIDById(employee_id);
		}
	 public Integer GetContactIDCount(String email) {
		 return contactrepo.GetContactIDCount(email);
	 }
	 
	 public Contact GetContact_ownerdate(String contactowner, String currentdate_time ) {
		 return contactrepo.GetContact_ownerdate(contactowner, currentdate_time);
	 }

	
}
