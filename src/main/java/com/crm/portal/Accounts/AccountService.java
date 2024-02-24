package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Lead.Lead;
import com.crm.portal.Lead.LeadRepository;

@Service
@Transactional
public class AccountService {

	
	@Autowired
	private AccountRepository accrepo;
	 
	public List<Account> listAll() {
		return accrepo.findAll();
	}
	 
	public Account get(long id) {
		return accrepo.findById(id).get();
	}
	 
	public void save(Account std) {
		accrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		accrepo.deleteById(id);
	}
	public List<Account> GetIDById(Long employee_id) {
		return accrepo.GetIDById(employee_id);
		}
	
	public Integer GetIDCount(String accountname) {
		return accrepo.GetIDCount(accountname);
				}
}
