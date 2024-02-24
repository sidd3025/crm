package com.crm.portal.Invoice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceService {

	@Autowired 
	InvoiceRepo repo;
	
	
	public List<Invoice> listAll() {
		return repo.findAll();
	}
	public Invoice get(int id) {
		return repo.findById(id).get();
	}
	public List<Invoice> getItems(){
		return repo.findAll();
	}
	public void saveItems(Invoice items) {
		repo.save(items);
	}
	
	
	
}
