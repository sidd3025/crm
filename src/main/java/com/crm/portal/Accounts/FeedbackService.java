package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FeedbackService {

	@Autowired
	private FeedBackRepo repo;
	
	
	public List<Feedback> listAll(){
		return repo.findAll();
	}

	public void Save(Feedback feedback) {
		repo.save(feedback);
	}
	
	public Feedback get(int id) {
		return repo.findById(id).get();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	
}
