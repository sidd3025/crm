package com.crm.portal.Company;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Lead.EmpService;
import com.crm.portal.Lead.Lead;
import com.crm.portal.Login.LoginUser;
import com.crm.portal.Login.LoginUserAuthority;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;

@Controller
public class Company_Controller {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private CompanyService companyservice;
	@Autowired
	private EmployeeService empserivce;
	
	@GetMapping("/create_company")
	public String CompanyName(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	model.addAttribute("CompanyNameList",companyservice.listAll());
    	model.addAttribute("EmployeeList", empserivce.listAll());
    	CompanyDetails company = new CompanyDetails();
    	model.addAttribute("CompanyDetails", company);
    	 
		return "AddCompanys";
	}
	
	@GetMapping("/Showcreate_company")
	public String ShowCompany(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	model.addAttribute("CompanyNameList",companyservice.listAll());
    	CompanyDetails company = new CompanyDetails();
    	model.addAttribute("CompanyDetails", company);
		return "create_company";
	}
	
	
	

	@PostMapping("/Savecreate_company")
	public String SaveCompany(@ModelAttribute("CompanyNameList") CompanyDetails company, 
			@RequestParam(value="mcode",required=false) String mcode, 
			@RequestParam(value="MobileNumber1",required=false) String MobileNumber1,
			@RequestParam(value="MobileNumber2",required=false) String MobileNumber2,
			@RequestParam("firstname") String Firstname,@RequestParam("lastname") String Lastname,
		    @RequestParam("email") String Email,
		    @RequestParam("mcode") String code,@RequestParam ("NoofEmployee") String employee,
					  HttpServletRequest request) throws ParseException {
		
		company.setMobileNumber1(MobileNumber1);
		company.setMobileNumber2(MobileNumber2);
		company.setTotal_employee(Integer.parseInt(employee));
		companyservice.save(company);
	    
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);		
		LoginUser m = new LoginUser();
		m.setUsername(company.getEmail());
		m.setPassword(bCryptPasswordEncoder.encode("123456"));
		loginuserService.save(m);
		LoginUserAuthority a = new LoginUserAuthority();
		a.setAuthority_id(3);
		a.setUser_id(loginuserService.GetAuthorityID(company.getEmail()));
		loginuserauthorityService.save(a);
		
		 Long companyid= companyservice.GetCompanyDetailsByEmail(company.getEmail());	  
		  EmployeeDetails ed = new EmployeeDetails();
		  ed.setFirstname(Firstname);
		  ed.setLastname(Lastname);
		  ed.setEmail(Email);
		  ed.setNumber(MobileNumber1);
		  ed.setMcode(code);
		  ed.setEmployeeid(0);
		  ed.setCompanyid(companyid);
		  ed.setAuthorityid(3);
		  empserivce.save(ed);
		
		return "redirect:/create_company";
		}
	
//	@PostMapping("/Savecreate_company")
//	public String SaveCompanyEmployee(@RequestParam("firstname") String Firstname,@RequestParam("lastname") String Lastname,
//			  @RequestParam("Empmanager") String empmanager,
//			  @RequestParam("email") String Email,@RequestParam("number") String Phone,
//			  @RequestParam("mcode") String mcode, HttpServletRequest request) throws ParseException {
//		
//		
//		 Long companyid= companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	  
//		  EmployeeDetails e = new EmployeeDetails();
//		  e.setFirstname(Firstname);
//		  e.setLastname(Lastname);
//		  e.setEmail(Email);
//		  e.setNumber(Phone);
//		  e.setMcode(mcode);
//		  e.setEmployeeid(Integer.parseInt(empmanager));
//		  e.setCompanyid(companyid);
//		  e.setAuthorityid(4);
//		  empserivce.save(e);
//		  
//		  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//		  LoginUser l = new LoginUser();
//		  l.setUsername(Email);
//		  l.setPassword(bCryptPasswordEncoder.encode("123456"));
//		  loginuserService.save(l);
//		  LoginUserAuthority la = new LoginUserAuthority();
//		  la.setAuthority_id(4);
//		  la.setUser_id(loginuserService.GetAuthorityID(Email));
//		  loginuserauthorityService.save(la);
//		return "redirect:/create_company";
//		}
	
	
}
