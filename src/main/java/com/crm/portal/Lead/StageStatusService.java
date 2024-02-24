package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageStatusService {

	@Autowired
	private StagestatusRepo stage;
	
	public List<StageStatus> listAll() {
		return stage.findAll();
	}
	 
	public StageStatus get(long id) {
		return stage.findById(id).get();
	}
	 
	public void save(StageStatus std) {
		stage.save(std);
	}
	 
	public void deleteAuthority(long id) {
		stage.deleteById(id);
	}
	
}
