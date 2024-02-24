package com.crm.portal.Company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.crm.portal.EmployeeDeatils.EmployeeRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository com;
	
	public List<CompanyDetails> listAll() {
		return com.findAll();
	}
	 
	public CompanyDetails get(int id) {
		return com.findById(id).get();
	}
	 
	public void save(CompanyDetails std) {
		com.save(std);
	}
	 
	public void deleteAuthority(int id) {
		com.deleteById(id);
	}
	
	public Long GetIDBycompany_name(String company_name) {
		return com.GetIDBycompany_name(company_name);
	}
	
	public Long GetCompanyDetailsByEmail(String email) {
		return com.GetCompanyDetailsByEmail(email);
	}
	
	public CompanyDetails FindCompanyNameByEmail(String email) {
		return com.FindCompanyNameByEmail(email);
	}
	
}
