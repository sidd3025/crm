//package com.crm.portal.WorkplannerMaster;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.crm.portal.Company.CompanyService;
//import com.crm.portal.Contacts.ContactStatus;
//import com.crm.portal.EmployeeDeatils.EmployeeDetails;
//import com.crm.portal.EmployeeDeatils.EmployeeService;
//import com.crm.portal.Login.LoginUserAuthorityService;
//import com.crm.portal.Login.LoginUserService;
//import com.crm.portal.Login.Mainsidebarauthority;
//import com.crm.portal.Login.MainsidebarauthorityService;
//
//@Controller
//public class WorkPlannerMasterController {
//
//	@Autowired
//	private MainsidebarauthorityService mainsidebarauthorityService;
//	@Autowired
//	private LoginUserAuthorityService loginuserauthorityService;
//	@Autowired
//	private LoginUserService loginuserService;
//	@Autowired
//	private CompanyService companyservice;
//	@Autowired
//	private EmployeeService employeeserivce;
//	@Autowired
//	private CustomerService customerserivce;
//	@Autowired
//	private AssignTaskService assigntaskserivce;
//	@Autowired
//	private CategoryService categoryserivce;
//	@Autowired
//	private projectService projectserivce;
//	
//	
//	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	@GetMapping("/addcategory")
//       public String Addclient(Model model,HttpServletRequest request) {
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("categoryList", categoryserivce.listAll());
//		
//		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//
//        model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
//      	
//        model.addAttribute("employee", employee);
//		
//		return "WorkPlanerMaster/category";
//	
//	}
//	
//	@PostMapping("/category/save")
//	public String addclinetSave(@RequestParam("category") String category,
//			HttpServletRequest request) {
//
//		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date();
//		String Current_Date = f.format(date);
//		
//		Long companyid = companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());
//		Category c =new Category();
//		
//		c.setCategory(category);
//		c.setCurrentdate(Current_Date);
//
//		categoryserivce.Save(c);
//		
//		return "redirect:/addcategory";
//	}	
//	
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	@GetMapping("/customerlist")
//	public String clientlist(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("customerList", customerserivce.listAll());
//		
//		
//		return "WorkPlanerMaster/AddCompany";
//	}
//	
//	//////////////////////////////////////////////////////////////////////////////////////////////////////////
//	@GetMapping("/assignwork")
//       public String Assignwork(Model model,HttpServletRequest request) {
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		
//		return "WorkPlanerMaster/AssignWork";
//	
//	}
//	
//	@PostMapping("/asignwork/save")
//	public String asignworkSave(@RequestParam("clinetname") String clinetname,@RequestParam("category") String category,
//			@RequestParam("projectname") String projectname,@RequestParam("projectcode") String projectcode,@RequestParam("milestone") String milestone,
//			@RequestParam("task") String task, @RequestParam("Taskstatus") String Taskstatus,@RequestParam("assignto") String assignto,
//			@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate,@RequestParam("priority") String priority,
//			@RequestParam("description") String description,HttpServletRequest request) {
//
//		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date();
//		String Current_Date = f.format(date);
//		
//		Long companyid= companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());
//		AssignTask at =new AssignTask();
//		
//		assigntaskserivce.save(at);
//		return "redirect:/addclinet";
//	}	
//	
//	@GetMapping("/assignlist")
//	public String assignlist(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("assignList", customerserivce.listAll());
//		
//		return "WorkPlanerMaster/AddCompany";
//	}
//	
//	
//	@GetMapping("/addproject")
//    public String addproject(Model model,HttpServletRequest request) {
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("assignList", customerserivce.listAll());
//		
//		return "WorkPlanerMaster/add_projectname";
//	
//	}
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	@PostMapping("/asignwork/save")
//	public String asignworkSave(@RequestParam("clinetname") String clinetname,@RequestParam("category") String category,
//			@RequestParam("projectname") String projectname,@RequestParam("assignemployee") String assignemployee
//			,HttpServletRequest request) {
//
//		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date();
//		String Current_Date = f.format(date);
//		
//		Long companyid= companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());
//		 projectName pn =new projectName();
//		
//		 projectserivce.save(pn);
//		return "redirect:/addclinet";
//	}	
//	
//	
//	
//	
//	
//}
