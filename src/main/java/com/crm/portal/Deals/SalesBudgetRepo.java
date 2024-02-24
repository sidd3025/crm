package com.crm.portal.Deals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesBudgetRepo extends JpaRepository <SaleBudget , Long> {

	
	@Query("select c.estimateamount from SaleBudget c where c.employee_id = ?1 and c.monthly= ?2 and c.yearly=?3")
	String getcountsalebudgetmotlyyearly(int employee_id, String monthly,String yearly);
	
	@Query("select c.targetamount from SaleBudget c where c.employee_id = ?1 and c.monthly= ?2 and c.yearly=?3")
	String getcountsalebudgetmotlyyearly1(int employee_id, String monthly,String yearly);
	
}
