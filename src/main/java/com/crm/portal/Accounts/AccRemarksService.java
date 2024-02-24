package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Contacts.RemarksModel;
import com.crm.portal.Contacts.RemarksRepo;

@Service
@Transactional
public class AccRemarksService {

	
	@Autowired
	private AccRemarksRepo remarkrepo;

	public List<AccountRemarks> listAll() {
		return remarkrepo.findAll();
	}
	  
	public AccountRemarks get(long id) {
		return remarkrepo.findById(id).get();
	}
	 
	public void save(AccountRemarks std) {
		remarkrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		remarkrepo.deleteById(id);
	}
	public void save(List<AccountRemarks> c) {
		remarkrepo.saveAll(c);
	}
	
	public List<AccountRemarks> getFilterByAccountID(String account_id){
	  return remarkrepo.getFilterByAccountID(account_id);
	}
}
