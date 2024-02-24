package com.crm.portal.Report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Accounts.Account;
import com.crm.portal.Accounts.AccountRepository;

@Service
@Transactional
public class ReportLeadService {

	@Autowired
	private ReportLeadRepository rep;
	 
	public List<ReportLead> listAll() {
		return rep.findAll();
	}
	 
	public ReportLead get(long id) {
		return rep.findById(id).get();
	}
	 
	public void save(ReportLead std) {
		rep.save(std);
	}
	 
	public void deleteAuthority(long id) {
		rep.deleteById(id);
	}
}
