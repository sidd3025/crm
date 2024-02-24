package com.crm.portal.WorkplannerMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssignTaskService {

	@Autowired
	private AssignTaskRepository assrepo;
	
	public List<AssignTask> listAll() {
		return assrepo.findAll();
	}
	 
	public AssignTask get(Integer id) {
		return assrepo.findById(id).get();
	}
	 
	public void save(AssignTask std) {
		assrepo.save(std);
	}
	 
	public void deleteAuthority(Integer id) {
		assrepo.deleteById(id);
	}
	
	 public List<AssignTask> getTaskListLimit1(){
		 return assrepo.getTaskListLimit1();
	 }
	 
	 public List<AssignTask> getTaskList(){
		 return assrepo.getTaskList();
	 }

}
