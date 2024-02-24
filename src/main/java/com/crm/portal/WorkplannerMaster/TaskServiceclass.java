package com.crm.portal.WorkplannerMaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TaskServiceclass {

	
	@Autowired
	private TaskRepository taskrepo;
	
	public List<AddTask> listAll() {
		return taskrepo.findAll();
	}
	 
	public AddTask get(Integer id) {
		return taskrepo.findById(id).get();
	}
	 
	public void save(AddTask std) {
		taskrepo.save(std);
	}
	 
	public void deleteAuthority(Integer id) {
		taskrepo.deleteById(id);
	}
	
//	AddTask getTaskById(Integer id);
//	void deleteTaskById(Integer id);

	public List<AddTask> findTaskByCompanyIdProjectIdMilestoneId(Integer companyId, Integer projectId,Integer milestoneId){
	 return	taskrepo.findTaskByCompanyIdProjectIdMilestoneId(companyId, projectId, milestoneId);
	}
	
}
