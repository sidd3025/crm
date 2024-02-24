 package com.crm.portal.Lead;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository <Lead , Long> {

	@Query("select l from Lead l where l.employee_id = ?1")
	  List<Lead> GetIDById(Long employee_id);
	
	@Query(value="select id from Lead where lead_id=?1")
	public Integer getLeadID(int lead_id);
	
	@Query(value="select l from Lead l where l.contactid=?1")
	public List<Lead> getFilterByLeadtoContactID(String contactid);

	@Query(value = "SELECT id FROM Lead WHERE contactid = ?1", nativeQuery = true)
	public Integer UpdateLead(String contactid);

	@Query(value="select l from Lead l where l.contactid=?1")
	public Lead getFilterByLeadAssigntoContactID(Integer contactid);


}
