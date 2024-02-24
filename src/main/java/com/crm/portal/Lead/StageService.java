package com.crm.portal.Lead;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageService {

	@Autowired
	private StageRepository stage;
	
	public List<Stage> listAll() {
		return stage.findAll();
	}
	 
	public Stage get(long id) {
		return stage.findById(id).get();
	}
	 
	public void save(Stage std) {
		stage.save(std);
	}
	
	public void deleteAuthority(long id) {
		stage.deleteById(id);
	}
}
