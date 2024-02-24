package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpService {


	@Autowired
	private EmpRepository empl;
	
	public List<Employee> listAll() {
		return empl.findAll();
	}
	 
	public Employee get(long id) {
		return empl.findById(id).get();
	}
	 
	public void save(Employee std) {
		empl.save(std);
	}
	 
	public void deleteAuthority(long id) {
		empl.deleteById(id);
	}
}
