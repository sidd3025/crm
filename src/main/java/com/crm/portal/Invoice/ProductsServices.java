package com.crm.portal.Invoice;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.portal.Deals.Deal;

@Service
@Transactional
public class ProductsServices {

	@Autowired
	ProductsRepo prorepo;
	
	public List<ProductMaster> listAll() {
		return prorepo.findAll();
	}
	public ProductMaster get(int id) {
		return prorepo.findById(id).get();
	}
	public List<ProductMaster> getItems(){
		return prorepo.findAll();
	}
	public void saveItems(ProductMaster items) {
		prorepo.save(items);
	}
	public ProductMaster getItemById(int items_id) {
		return prorepo.findById(items_id).get();
	}
	public void deleteItem(int items_id) {
		prorepo.deleteById(items_id);
	}
	
}
