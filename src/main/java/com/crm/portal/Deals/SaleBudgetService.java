package com.crm.portal.Deals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaleBudgetService {

	
	
	@Autowired
	private SalesBudgetRepo salerepo;
	
	public List<SaleBudget> listAll() {
		return salerepo.findAll();
	}
	 
	public SaleBudget get(long id) {
		return salerepo.findById(id).get();
	}
	 
	public void save(SaleBudget std) {
		salerepo.save(std);
	}
	 
	public void deleteAuthority(long id) {
		salerepo.deleteById(id);
	}

	 public String getcountsalebudgetmotlyyearly(int employee_id, String monthly,String yearly) {
		 return salerepo.getcountsalebudgetmotlyyearly(employee_id, monthly, yearly);
	 }
	 
	 public String getcountsalebudgetmotlyyearly1(int employee_id, String monthly,String yearly) {
		 return salerepo.getcountsalebudgetmotlyyearly1(employee_id, monthly, yearly);
	 }
		
}
