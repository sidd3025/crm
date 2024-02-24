package com.crm.portal.WorkplannerMaster;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crm.portal.Company.CompanyService;
import com.crm.portal.Deals.Deal;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.MainsidebarauthorityService;



@Controller
public class MileStoneController {
	
	@Autowired
	private MilestoneService  milestoneService;
	@Autowired
	private projectService ProjectService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CompanyService companyservice;
	@Autowired
	private CompanyService comservice;
	
	@GetMapping("/addmilestone")
	public String showMilestoneForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<com.crm.portal.Login.Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid
				(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		//create model attribute to bind from data
		model.addAttribute("milestonelist",milestoneService.listAll());
		Addmilestone milestone=new Addmilestone();
		model.addAttribute("milestone", milestone);
		List<AddCustomer> cc=customerService.listAll();
		model.addAttribute("Customerlist", cc);
		List<projectName> p= ProjectService.getAllprojectName();
		model.addAttribute("Projectlist", p);
		List<Category> c1 = categoryService.listAll();
		model.addAttribute("Categorylist", c1);
		model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
		return "WorkPlanerMaster/addmilestone";
	}
	

	
	@PostMapping("/saveMilestone")
	public String saveMilestone(@ModelAttribute("milestone") Addmilestone  milestone, HttpServletRequest request) {
		//save project name to database
		LocalDateTime mydate = LocalDateTime.now();
		DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		milestone.setCurrentdate(mydate.format(myformatter));
		
		 Long companyid= comservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	
			EmployeeDetails c =employeeserivce.getEmployeeDataByEmailID(1);
		milestone.setCompanyid(companyid.intValue());
		milestoneService.save(milestone);
		return "redirect:/addmilestone";
		
	}   
	
	@GetMapping("/showMilestoneEditForm/{id}")
	public String showFormForEdit(@PathVariable(value="id")Integer id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<com.crm.portal.Login.Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		Addmilestone milestone=milestoneService.get(id);
		model.addAttribute("milestone", milestone);
		List<AddCustomer> cc=customerService.listAll();
		model.addAttribute("Customerlist", cc);
		List<projectName> p= ProjectService.getAllprojectName();
		model.addAttribute("Projectlist", p);
		List<Category> c1 = categoryService.listAll();
		model.addAttribute("Categorylist", c1);
		model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
		return "edit_milestone";
	}
	
//	@GetMapping("/EditMilestone/{id}")
//	public String EditMilestone(@PathVariable(value="id")Integer id,Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		Addmilestone milestone=milestoneService.getMileStoneById(id);
//		model.addAttribute("milestone", milestone);
////		List<projectName> p= ProjectService.getAllprojectName();
////		model.addAttribute("Projectlist", p);
////		List<AddCustomer> cc=customerService.getAllCustomer();
////		model.addAttribute("Customerlist", cc);
////		List<Category> c1 = categoryService.listAll();
////		model.addAttribute("Categorylist", c1);
//		model.addAttribute("CompanyID",employeeService.getCompanyID(auth.getName()));
//		return "EditMilestone";
//	}
	
	@GetMapping("/deleteMilestone/{id}")
	public String deleteMilestone(@PathVariable(value="id")Integer id) {
		//call delete  method
		this.milestoneService.deleteAuthority(id);
		return "redirect:/addmilestone";
		
	}
	
}
