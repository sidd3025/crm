package com.crm.portal.EmployeeDeatils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.portal.Lead.EmpRepository;
import com.crm.portal.Lead.Employee;


@Service
@Transactional
public class EmployeeService {

	
	@Autowired
	private EmployeeRepository empl;
	
	public List<EmployeeDetails> listAll() {
		return empl.findAll();
	}
	 
	public EmployeeDetails get(long id) {
		return empl.findById(id).get();
	}
	 
	public void save(EmployeeDetails std) {
		empl.save(std);
	}
	 
	public void deleteAuthority(long id) {
		empl.deleteById(id);
	}
	
	public Long GetIDByEmail(String email) {
		return empl.GetIDByEmail(email);
	}
	
	public EmployeeDetails GetEmployeeDetailsByEmail(String email) {
		return empl.GetEmployeeDetailsByEmail(email);
	}
	
	public List<EmployeeDetails> GetEmployeeDetailsByEmail(Long companyid) {
		return empl.GetListEmployeeDetailsByEmail(companyid);
	}
	
	public EmployeeDetails FindNameByEmail(String email) {
		return empl.FindNameByEmail(email);
	}
	public  List<EmployeeDetails> FindNameByempID(int employeeid){
		return empl.FindNameByempID(employeeid);
	}
	 public List<EmployeeDetails> GetListEmployeeDetailsByEmail(Long companyid){
		 return empl.GetListEmployeeDetailsByEmail(companyid);
	 }
	 
	 public EmployeeDetails getEmployeeDataByEmailID(Integer id) {
		 return empl.getEmployeeDataByEmailID(id);
	 }
	 
	 public Integer getCompanyID(String emailid) {
		 return empl.getCompanyID(emailid);
	 }
	 
	 public Integer Update(String emailid) {
		 return empl.Update(emailid);
	 }
	 
	 public List<EmployeeDetails> FilterByReportingManager(Integer id){
		  return empl.FilterByReportingManager(id);
	 }

	 public EmployeeDetails getEmpID(String username) {
		 return empl.getEmpID(username);
	 }
	 
	
	 

}
