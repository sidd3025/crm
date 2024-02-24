package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Lead.Status;
import com.crm.portal.Lead.StatusRepository;

@Service
@Transactional
public class AccStatusService {

	

	@Autowired
	private AccStatusRepo accstatus;
	
	public List<AccountStatus> listAll() {
		return accstatus.findAll();
	}
	 
	public AccountStatus get(long id) {
		return accstatus.findById(id).get();
	}
	 
	public void save(AccountStatus std) {
		accstatus.save(std);
	}
	 
	public void deleteAuthority(long id) {
		accstatus.deleteById(id);
	}


}
