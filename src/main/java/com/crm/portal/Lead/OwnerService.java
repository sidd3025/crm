package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OwnerService {

	@Autowired
	private OwnerRepository owner;
	
	public List<OwnerShip> listAll() {
		return owner.findAll();
	}
	 
	public OwnerShip get(long id) {
		return owner.findById(id).get();
	}
	 
	public void save(OwnerShip std) {
		owner.save(std);
	}
	 
	public void deleteAuthority(long id) {
		owner.deleteById(id);
	}
}
