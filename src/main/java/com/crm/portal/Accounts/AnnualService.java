package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Lead.EmpRepository;
import com.crm.portal.Lead.Employee;

@Service
@Transactional
public class AnnualService {

	@Autowired
	private AnnualRepository anu;
	
	public List<AnnualRevenue> listAll() {
		return anu.findAll();
	}
	 
	public AnnualRevenue get(long id) {
		return anu.findById(id).get();
	}
	 
	public void save(AnnualRevenue std) {
		anu.save(std);
	}
	 
	public void deleteAuthority(long id) {
		anu.deleteById(id);
	}
}
