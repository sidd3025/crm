package com.crm.portal.Sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Contacts.Contact;
import com.crm.portal.Contacts.ContactRepository;

@Service
@Transactional
public class SalesService {

	@Autowired
	private SalesRepo salerepo;

	public List<Sales> listAll() {
		return salerepo.findAll();
	}
	  
	public Sales get(long id) {
		return salerepo.findById(id).get();
	}
	 
	public void save(Sales std) {
		salerepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		salerepo.deleteById(id);
	}
	public void save(List<Sales> c) {
		salerepo.saveAll(c);
	}
	 List<Sales> GetIDById(Long employee_id){
		 return salerepo.GetIDById(employee_id);
	 }
	
}
