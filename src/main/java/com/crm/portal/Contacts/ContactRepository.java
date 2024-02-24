package com.crm.portal.Contacts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Lead.Lead;

@Repository
public interface ContactRepository extends JpaRepository <Contact , Long>{

	@Query("select l from Contact l where l.employee_id = ?1")
	  List<Contact> GetIDById(Long employee_id);
	
	@Query("select count(c.id) from Contact c where c.email=?1")
	 Integer GetContactIDCount(String email);
	
	@Query("select  l from Contact l where l.contactowner = ?1 and l.currentdate_time=?2")
	Contact GetContact_ownerdate(String contactowner, String currentdate_time );
}
