package com.crm.portal.Sales;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Accounts.Account;
import com.crm.portal.Contacts.Contact;

@Repository
public interface SalesRepo extends JpaRepository <Sales , Long> {

	@Query("select a from Sales a where a.employee_id = ?1")
	  List<Sales> GetIDById(Long employee_id);
	
}
