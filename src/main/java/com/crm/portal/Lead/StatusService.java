package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class StatusService {
	
	@Autowired
	private StatusRepository status;
	
	public List<Status> listAll() {
		return status.findAll();
	}
	 
	public Status get(long id) {
		return status.findById(id).get();
	}
	 
	public void save(Status std) {
		status.save(std);
	}
	 
	public void deleteAuthority(long id) {
		status.deleteById(id);
	}


}
