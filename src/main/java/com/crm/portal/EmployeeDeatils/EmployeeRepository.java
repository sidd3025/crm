package com.crm.portal.EmployeeDeatils;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Lead.IndustryMaster;
//import com.learningmodule.model.Student;


@Repository
public interface EmployeeRepository  extends JpaRepository <EmployeeDetails , Long> {
	@Query("select e.id from EmployeeDetails e where e.email = ?1")
	  Long GetIDByEmail(String email);
	
	@Query("select e from EmployeeDetails e where e.email = ?1")
	EmployeeDetails GetEmployeeDetailsByEmail(String email);

	@Query("select e from EmployeeDetails e where e.companyid = ?1")
	List<EmployeeDetails> GetListEmployeeDetailsByEmail(Long companyid);

	@Query(" select s from EmployeeDetails s where s.email=?1")
	public EmployeeDetails FindNameByEmail(String email);
	
	@Query(" select s from EmployeeDetails s where s.employeeid=?1")
	public  List<EmployeeDetails> FindNameByempID(int employeeid);
	

	@Query(nativeQuery = true, value = "select * from employee_details where id=?1")
	public EmployeeDetails getEmployeeDataByEmailID(Integer id);
	
	
	@Query(nativeQuery = true, value = "SELECT companyid from employee_details where email= ?1")
	public Integer getCompanyID(String emailid);
	
	@Query(" select id FROM EmployeeDetails where email=?1")
	public Integer Update(String emailid);

	@Query("select e from EmployeeDetails e where e.authorityid= 4 AND e.id=?1 OR e.authorityid= 3 AND e.employeeid=?1")
	public List<EmployeeDetails> FilterByReportingManager(Integer id);

	@Query(nativeQuery=true , value="select id from employee_details where email=?1")
	public EmployeeDetails getEmpID(String email);
	
}

