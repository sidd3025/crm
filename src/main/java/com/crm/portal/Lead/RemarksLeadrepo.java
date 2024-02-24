package com.crm.portal.Lead;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Contacts.RemarksModel;

@Repository
public interface RemarksLeadrepo extends JpaRepository <RemarksLeadModel , Long> {

	
	@Query(value="select r from RemarksLeadModel r where r.lead_id=?1")
	public List<RemarksLeadModel> getFilterByLead(String lead_id);
	
	@Query(value="select r from RemarksLeadModel r where r.lead_id=?1")
	public List<RemarksLeadModel> getFilterByLeadID(String lead_id);
}
