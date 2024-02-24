package com.crm.portal.Deals;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Accounts.Account;
import com.crm.portal.Lead.Lead;

@Repository
public interface DealRepository extends JpaRepository <Deal , Long> {

	@Query("select l from Deal l where l.employee_id = ?1")
	  List<Deal> GetIDById(Long employee_id);
	
}
