package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountTypeService {

	
	@Autowired
	private Account_TypeRepository acc;
	
	public List<AccountType> listAll() {
		return acc.findAll();
	}
	 
	public AccountType get(long id) {
		return acc.findById(id).get();
	}
	 
	public void save(AccountType std) {
		acc.save(std);
	}
	 
	public void deleteAuthority(long id) {
		acc.deleteById(id);
	}
}
