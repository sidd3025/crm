package com.crm.portal.Campagin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Lead.EmpRepository;
import com.crm.portal.Lead.Employee;

@Service
@Transactional
public class CampaginService {

	@Autowired
	private CampaginRepo camp;
	
	public List<Campagin> listAll() {
		return camp.findAll();
	}
	 
	public Campagin get(long id) {
		return camp.findById(id).get();
	}
	 
	public void save(Campagin std) {
		camp.save(std);
	}
	 
	public void deleteAuthority(long id) {
		camp.deleteById(id);
	}
	
}
