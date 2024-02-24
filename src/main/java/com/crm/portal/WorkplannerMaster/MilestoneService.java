package com.crm.portal.WorkplannerMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MilestoneService {

	@Autowired
	private AddmilestoneRepository milerepo;
	
	public List<Addmilestone> listAll() {
		return milerepo.findAll();
	}
	 
	public Addmilestone get(Integer id) {
		return milerepo.findById(id).get();
	}
	 
	public void save(Addmilestone std) {
		milerepo.save(std);
	}
	 
	public void deleteAuthority(Integer id) {
		milerepo.deleteById(id);
	}
	
	public List<Addmilestone> findMilestone(Integer client_id, Integer project_id){
		return milerepo.findMilestone(client_id, project_id);
	}
	
}
