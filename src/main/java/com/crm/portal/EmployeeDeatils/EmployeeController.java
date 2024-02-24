package com.crm.portal.EmployeeDeatils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.portal.Company.CompanyDetails;
import com.crm.portal.Company.CompanyService;
import com.crm.portal.Login.EmailForgetPasswordService;
import com.crm.portal.Login.LoginUser;
import com.crm.portal.Login.LoginUserAuthority;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;



@Controller
public class EmployeeController {

	
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService empservice;
	@Autowired
	private CompanyService comservice;
	
	

	 @GetMapping("/employee") 
	  public String LeadAdd(Model model, HttpServletRequest request) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  List<Mainsidebarauthority> listmainsidebarauthority =
	  mainsidebarauthorityService.Mainsidebarauthoritybyauthid(
	  loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	  model.addAttribute("listMainsidebarAuthority",listmainsidebarauthority);
//	  model.addAttribute("listEmployee",empservice.listAll());
//	  find the employee name by email 
	  EmployeeDetails employee= empservice.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//get companyid by using variable employee in GetListEmployeeDetailsByEmail method so we can get employee name according to company only...   	 
	  model.addAttribute("listEmployee",empservice.GetListEmployeeDetailsByEmail(employee.getCompanyid()));
	  model.addAttribute("listCompany",comservice.listAll());
//	  model.addAttribute("EmployeeDetails", EmployeeDetails());
	  return "AddEmployee"; 
	  }
	  
	  @PostMapping("/employee/save")
	  public String employeeSave(@RequestParam("firstname") String Firstname,@RequestParam("lastname") String Lastname,
			  @RequestParam("Empmanager") String empmanager,
			  @RequestParam("email") String Email,@RequestParam("number") String Phone,@RequestParam("department") String department,
			  @RequestParam("mcode") String mcode, HttpServletRequest request ){
		  		  
//			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);		
//			std.setMobileNumber1(MobileNumber1);
//			std.setMobileNumber2(MobileNumber2);
//			StdService.save(std);
//			LoginUser m = new LoginUser();
//			m.setUsername(std.getEmail());
//			m.setPassword(bCryptPasswordEncoder.encode("123456"));
//			loginuserService.save(m);
//			LoginUserAuthority a = new LoginUserAuthority();
//			a.setAuthority_id(5);
//			a.setUser_id(loginuserService.GetAuthorityID(std.getEmail()));
//			loginuserauthorityService.save(a);
		  
		  Long companyid= comservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	  
		  EmployeeDetails e = new EmployeeDetails();
		  e.setFirstname(Firstname);
		  e.setLastname(Lastname);
		  e.setEmail(Email);
		  e.setNumber(Phone);
		  e.setMcode(mcode);
		  e.setDepartment(department);
		  e.setEmployeeid(Integer.parseInt(empmanager));
		  e.setCompanyid(companyid);
		  e.setAuthorityid(4);
		  empservice.save(e);
		  
		  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		  LoginUser l = new LoginUser();
		  l.setUsername(Email);
		  l.setPassword(bCryptPasswordEncoder.encode("123456"));
		  loginuserService.save(l);
		  LoginUserAuthority la = new LoginUserAuthority();
		  la.setAuthority_id(4);
		  la.setUser_id(loginuserService.GetAuthorityID(Email));
		  loginuserauthorityService.save(la);
		  
		  return "redirect:/home";
		
		}
	
	
}
