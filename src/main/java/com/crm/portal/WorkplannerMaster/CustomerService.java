package com.crm.portal.WorkplannerMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Deals.Deal;
import com.crm.portal.Deals.DealRepository;

@Service
@Transactional
public class CustomerService {


	@Autowired
	private AddCustomerRepository custrepo;
	
	public List<AddCustomer> listAll() {
		return custrepo.findAll();
	}
	 
	public AddCustomer get(long id) {
		return custrepo.findById(id).get();
	}
	 
	public void save(AddCustomer std) {
		custrepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		custrepo.deleteById(id);
	}
	
	public List<AddCustomer> getListByCurrentDate(String currentdate){
		return custrepo.getListByCurrentDate(currentdate);
	}
	public int findByCompanyName1(String companyname) {
		return custrepo.findByCompanyName1(companyname);
	}
	
	public String findCompanyNameById(Long id) {
		return custrepo.findCompanyNameById(id);
	}
	
	public Long findCompanyIDByCompanyName(String companyname) {
		return custrepo.findCompanyIDByCompanyName(companyname);
	}
	
	
}
