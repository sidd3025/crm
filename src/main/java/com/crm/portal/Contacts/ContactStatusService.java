package com.crm.portal.Contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Accounts.AccStatusRepo;
import com.crm.portal.Accounts.AccountStatus;

@Service
@Transactional
public class ContactStatusService {

	
	@Autowired
	private ContactStatusRepo contstatus;
	
	public List<ContactStatus> listAll() {
		return contstatus.findAll();
	}
	 
	public ContactStatus get(long id) {
		return contstatus.findById(id).get();
	}
	 
	public void save(ContactStatus std) {
		contstatus.save(std);
	}
	 
	public void deleteAuthority(long id) {
		contstatus.deleteById(id);
	}
	
}
