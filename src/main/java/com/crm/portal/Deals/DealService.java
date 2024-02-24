package com.crm.portal.Deals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Accounts.Account;
import com.crm.portal.Accounts.AccountRepository;
import com.crm.portal.Contacts.Contact;

@Service
@Transactional
public class DealService {

	
	@Autowired
	private DealRepository dealrepo;
	
	public List<Deal> listAll() {
		return dealrepo.findAll();
	}
	 
	public Deal get(long id) {
		return dealrepo.findById(id).get();
	}
	 
	public void save(Deal std) {
		dealrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		dealrepo.deleteById(id);
	}

	public List<Deal> GetIDById(Long employee_id) {
		return dealrepo.GetIDById(employee_id);
		}
}
