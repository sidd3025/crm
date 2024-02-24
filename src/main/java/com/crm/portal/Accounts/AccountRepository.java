package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Lead.Lead;

@Repository
public interface AccountRepository extends JpaRepository <Account , Long> {

	@Query("select a from Account a where a.employee_id = ?1")
	  List<Account> GetIDById(Long employee_id);

	
	@Query("select count(a.id) from Account a where a.accountname = ?1")
	 Integer GetIDCount(String accountname);
	
}
