package com.crm.portal.Report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReportDeatils_Service {

	
	@Autowired
	private ReportDetails_Repository rep;
	 
	public List<ReportLead_Details> listAll() {
		return rep.findAll();
	}
	 
	public ReportLead_Details get(long id) {
		return rep.findById(id).get();
	}
	 
	public void save(ReportLead_Details std) {
		rep.save(std);
	}
	 
	public void deleteAuthority(long id) {
		rep.deleteById(id);
	}
	
}
