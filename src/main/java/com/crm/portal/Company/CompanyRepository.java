package com.crm.portal.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.EmployeeDeatils.EmployeeDetails;
//import com.learningmodule.model.Student;

@Repository
public interface CompanyRepository  extends JpaRepository <CompanyDetails , Integer>{
	@Query("select c.id from CompanyDetails c where c.company_name = ?1")
	  Long GetIDBycompany_name(String company_name);
	
	@Query("select e.id from CompanyDetails e where e.email = ?1")
	public Long GetCompanyDetailsByEmail(String email);
	
	@Query(" select s from CompanyDetails s where s.email=?1")
	public CompanyDetails FindCompanyNameByEmail(String email);
}
