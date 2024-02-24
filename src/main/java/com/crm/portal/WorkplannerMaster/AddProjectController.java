package com.crm.portal.WorkplannerMaster;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.crm.portal.Company.CompanyService;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;


@Controller
public class AddProjectController {
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private projectService ProjectService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProjectNameAuthoritiesService projectNameAuthoritiesService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private CompanyService comservice;
	
	//display list of project
			
		@GetMapping("/add_projectname")
		public String showNewProjectForm(Model model) {
			//create model attribute to bind from data
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
			model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
			model.addAttribute("listProjectName",ProjectService.getAllprojectName());
			projectName project=new projectName();
			model.addAttribute("project", project);
			List<AddCustomer> c=customerService.listAll();
			model.addAttribute("Customerlist", c);
			List<EmployeeDetails> e = employeeserivce.listAll();
			model.addAttribute("Employeelist", e);
			List<Category> c1 = categoryService.listAll();
			model.addAttribute("Categorylist", c1);
			ProjectNameAuthorities pAuthorities = new ProjectNameAuthorities();
			model.addAttribute("pAuthorities", pAuthorities);
			model.addAttribute("PAuth", projectNameAuthoritiesService.listAll());
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			
			return "WorkPlanerMaster/add_projectname";
		}
		
		@PostMapping("/saveProject")
		public String saveProject(@ModelAttribute("project") projectName  project, @RequestParam(name="ename",required=false)Integer[] empid, HttpServletRequest request) {
			//save project name to database
//			AddCustomer c1 = new AddCustomer();
			String c2 = customerService.findCompanyNameById(Long.parseLong(String.valueOf(project.getCompanyNameID())));
//			System.out.println("C2 : " + c2 );
			int count1 = ProjectService.CountProjectName(Long.valueOf(project.getCompanyNameID()),project.getCategoryName());
			if(count1 == 0) {
				String str1 = c2.substring(0, 3) ;
				char str2 =	project.getCategoryName().charAt(0);
//				System.out.println("str1 : " + str1);
//				System.out.println("str2 : " + str2);
					LocalDateTime mydate = LocalDateTime.now();
					DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					
					 Long companyid= comservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	
					EmployeeDetails c =employeeserivce.getEmployeeDataByEmailID(employeeserivce.Update(request.getUserPrincipal().getName()));
					project.setCurrentdate( mydate.format(myformatter));
					project.setCompanyid(companyid);
					project.setProjectcode(str1 + "-" + str2 + "-00" + 1);
					project.setStatus(true);
					ProjectService.save(project);
//					ProjectService.insertDataIntoProject(project.getCompanyName(), mydate.format(myformatter),project.getProjectName(),(str1 + "-" + str2 + "-00" + 1), c.getCompany_id(), project.getCategoryName());
			}
			else {
				String str1 = c2.substring(0, 3) ;
				char str2 =	project.getCategoryName().charAt(0);
				String h = ProjectService.getProjectCodeList(project.getCompanyNameID(), project.getCategoryName());
				String str = h.substring(6, 9);
//				System.out.println("str : " + str);
				int n1 = Integer.parseInt(str);
				int p= n1+1;
				String n = String.valueOf(p);
					LocalDateTime mydate = LocalDateTime.now();
					DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 Long companyid= comservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	
					EmployeeDetails c =employeeserivce.getEmployeeDataByEmailID(employeeserivce.Update(request.getUserPrincipal().getName()));
					project.setCurrentdate( mydate.format(myformatter));
					project.setCompanyid(companyid);
					project.setCompanyid(c.getCompanyid());
				if(n.length()==1) {
					project.setProjectcode(str1 + "-" + str2 + "-00" + p);
//					ProjectService.insertDataIntoProject(project.getCompanyName(), mydate.format(myformatter),project.getProjectName(),(str1 + '-' + str2 + "-00" + p), c.getCompany_id(), project.getCategoryName());
				}
				if(n.length()==2) {
					project.setProjectcode(str1 + "-" + str2 + "-0" + p);
//					ProjectService.insertDataIntoProject(project.getCompanyName(), mydate.format(myformatter),project.getProjectName(),(str1 + '-' + str2 + "-0" + p), c.getCompany_id(), project.getCategoryName());
				}
				if(n.length()==3) {
					project.setProjectcode(str1 + "-" + str2 + "-" + p);
//					ProjectService.insertDataIntoProject(project.getCompanyName(), mydate.format(myformatter),project.getProjectName(),(str1 + '-' + str2 +  "-" + p), c.getCompany_id(), project.getCategoryName());
				}
				project.setStatus(true);
				ProjectService.saveProject(project);
		
			} 
//			ProjectNameAuthorities pAuthorities = new ProjectNameAuthorities();
			for(Integer p:empid) {
//			System.out.println("empname" + p);
//				pAuthorities.setEmployee_id(p);
//				pAuthorities.setProject_id(project.getId());
				projectNameAuthoritiesService.insertDataIntoProjectAuthorities(project.getId(), p);
			}
			
			return "redirect:/add_projectname";
			
		}   
		
		
		@PostMapping("/saveProjectName")
		public String saveProjectName(@ModelAttribute("pAuthorities") ProjectNameAuthorities pAuthorities, @RequestParam(name="projectname",required=false)String projectname,
				@RequestParam(name="id",required=false)Integer id,@RequestParam(name="ename", required=false) Integer[] empid,HttpServletRequest request) {
			
		    int count1 = projectNameAuthoritiesService.CountProjectNameAuthorities(pAuthorities.getProject_id(),pAuthorities.getEmployee_id());
		    for (Integer p : empid) {
		        if (count1 > 0) {
//		            System.out.println("empid" + p);
		        	EmployeeDetails employee = employeeserivce.getEmployeeDataByEmailID(p);
		            int emp1 = employee.getId().intValue();
		            projectNameAuthoritiesService.insertDataIntoProjectAuthorities(pAuthorities.getProject_id(), emp1);
		        } 
		        else {
//		            System.out.println("empid" + p);
		        	EmployeeDetails employee = employeeserivce.get(p);
		            int emp1 = employee.getId().intValue();
		            projectNameAuthoritiesService.insertDataIntoProjectAuthorities(pAuthorities.getProject_id(), emp1);
		        }
		    }
		    projectName p = ProjectService.get(pAuthorities.getProject_id());
			p.setProjectName(projectname);
			p.setStatus(true);
			ProjectService.saveProject(p);
		    return "redirect:/add_projectname";
		}
		
		@PostMapping("/saveEditProject")
		public String saveEditProject(@ModelAttribute("pAuthorities") ProjectNameAuthorities  pAuthorities,
				@RequestParam(name="projectname",required=false)String projectname,
				@RequestParam(name="ename",required=false)Integer empid,@RequestParam(name="id",required=false)Integer id, Model model) {
//			model.addAttribute("pAuthorities1", projectNameAuthoritiesService.get(id));
			
			EmployeeDetails employee = employeeserivce.get(empid);
			pAuthorities.setEmployee_id(employee.getId().intValue());
			pAuthorities.setProject_id(projectNameAuthoritiesService.get(id).getProject_id());
			projectNameAuthoritiesService.save(pAuthorities);
			
			projectName p = ProjectService.get(pAuthorities.getProject_id());
			p.setProjectName(projectname);
			p.setStatus(true);
			ProjectService.saveProject(p);
			return "redirect:/add_projectname";
		}   
		
		@GetMapping("/Edit/{id}")
		public String showFormForEdit(@PathVariable(value="id")int id,Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
			List<projectName> project=ProjectService.getAllprojectName();
			model.addAttribute("ProjectList", project);
	    	ProjectNameAuthorities pAuthorities = projectNameAuthoritiesService.get(id);
			model.addAttribute("pAuthorities", pAuthorities);
			List<AddCustomer> c=customerService.listAll();
			model.addAttribute("Customerlist", c);
			List<EmployeeDetails> e = employeeserivce.listAll();
			model.addAttribute("Employeelist", e);
			List<Category> c1 = categoryService.listAll();
			model.addAttribute("Categorylist", c1);
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "edit_project";
		}
		
		@GetMapping("/EditEmployeeName/{id}")
		public String EditEmployeeName1(@PathVariable(value="id")int id, Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
	    	List<projectName> project=ProjectService.getAllprojectName();
			model.addAttribute("ProjectList", project);
	    	ProjectNameAuthorities pAuthorities = projectNameAuthoritiesService.get(id);
			model.addAttribute("pAuthorities", pAuthorities);
			List<AddCustomer> c=customerService.listAll();
			model.addAttribute("Customerlist", c);
			List<EmployeeDetails> e = employeeserivce.listAll();
			model.addAttribute("Employeelist", e);
			List<Category> c1 = categoryService.listAll();
			model.addAttribute("Categorylist", c1);
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "EditEmployeeName_ProjectName";

			
		}
		@GetMapping("/deleteProject/{id}")
		public String deleteProject(@PathVariable(value="id")int id) {
			//call delete project name method
			this.ProjectService.deleteprojectNameById(id);
			return "redirect:/add_projectname";
			
		}
		
		@RequestMapping(value = "/project_status/{status}/{id}", method = RequestMethod.GET)
		public String activeOrdeactive1(Model model, @PathVariable("status") String status, @PathVariable("id") Integer id) throws MessagingException, IOException {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    projectName p = ProjectService.get(id);
		    if (status.equals("activate")) {
		        p.setStatus(true);
		    }
		    else if(status.equals("deactivate")) {
		        p.setStatus(false);
		    }
		    ProjectService.saveProject(p);
		    return "redirect:/add_projectname";
		}
		
//////////////////////////////////////////////////////////////////////////////// ADD Company Name /////////////////////////////////////		
		
		//display Customer
		@GetMapping("/addcompanyname")
		public String customerviewpage(Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
			model.addAttribute("Customerlist",customerService.listAll());
			AddCustomer customer=new AddCustomer();
			model.addAttribute("customer", customer);
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "WorkPlanerMaster/AddCompany";
			
		}
		
		@PostMapping("/saveAllCustomer")
		public String saveCustomer(@Valid @ModelAttribute("customer") AddCustomer customer,@RequestParam("companyname") String companyname, HttpServletRequest request) {
			//save customer name to database
		    	LocalDateTime mydate = LocalDateTime.now();
				DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				customer.setCurrentdate(mydate.format(myformatter));
				
				EmployeeDetails c =employeeserivce.getEmployeeDataByEmailID(employeeserivce.Update(request.getUserPrincipal().getName()));
				customer.setCompanyName(companyname);
				customer.setCompanyid(c.getCompanyid());
				customer.setStatus(true);
		    	customerService.save(customer);
		        return "redirect:/addcompanyname";
		} 
			
		
		@GetMapping("/showCustomerEditForm/{id}")
		public String ShowCustomerEditForm(@PathVariable(value="id")int id,Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
			AddCustomer customer=customerService.get(id);
			model.addAttribute("customer", customer);
			LocalDateTime mydate = LocalDateTime.now();
			DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			customer.setCurrentdate(mydate.format(myformatter));
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "editcompanyname";
		}
		
		@GetMapping("/deleteCustomer/{id}")
		public String DeleteCustomer(@PathVariable(value="id")int id) {
			//call delete project name method
			this.customerService.deleteAuthority(id);
			return "redirect:/addcompanyname";
			
		}
		
		@RequestMapping(value = "/client_status/{status}/{id}", method = RequestMethod.GET)
		public String activeOrdeactive(Model model, @PathVariable("status") String status, @PathVariable("id") Long id) throws MessagingException, IOException {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    AddCustomer c = customerService.get(id);
		    if (status.equals("activate")) {
		        c.setStatus(true);
		    }
		    else if(status.equals("deactivate")) {
		        c.setStatus(false);
		    }
		    customerService.save(c);
		    return "redirect:/addcompanyname";
		}

	
		
//		@GetMapping("/CompanyNameListExcel")
//		public String ViewReportCustomerListExcel(Model model) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//			model.addAttribute("Customerlist",customerService.getAllCustomer());
//			model.addAttribute("CompanyID",employeeService.getCompanyID(auth.getName()));
//			return "CompanyNameListExcel";
//		}	

//		@GetMapping("/ProjectListExcel")
//		public String ViewReportProjectListExcel(Model model) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//			model.addAttribute("listProjectName",ProjectService.getAllprojectName());
//			List<AddCustomer> c=customerService.getAllCustomer();
//			model.addAttribute("Customerlist", c);
//			List<Employee> e = employeeService.getAllEmployees();
//			model.addAttribute("Employeelist", e);
//			List<Category> c1 = categoryService.listAll();
//			model.addAttribute("Categorylist", c1);
//			model.addAttribute("PAuth", projectNameAuthoritiesService.listAll());
//			model.addAttribute("CompanyID",employeeService.getCompanyID(auth.getName()));
//			return "ProjectListExcel";
//		}	

	///////////////////////////////////////////// Add Category ///////////////////////////////////////////
		
		
		@GetMapping("/category")
		public String addcategory(Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
			model.addAttribute("Categorylist",categoryService.listAll());
			Category category = new Category();
			model.addAttribute("Category1", category);
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "WorkPlanerMaster/category";
			
		}
		
		@PostMapping("/savecategory")
		public String saveCategory(@ModelAttribute("Category1") Category Category1, HttpServletRequest request) {
			//save customer name to database
		    	LocalDateTime mydate = LocalDateTime.now();
				DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				Category1.setCurrentdate(mydate.format(myformatter));
				
				 Long companyid= comservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	
					
				EmployeeDetails c = employeeserivce.getEmployeeDataByEmailID(employeeserivce.Update(request.getUserPrincipal().getName()));
				Category1.setCompanyid(c.getCompanyid().intValue());
		    	categoryService.Save(Category1);
		        return "redirect:/category";
		} 
			
		
		@GetMapping("/EditCategoryForm/{id}")
		public String EditCategoryForm(@PathVariable(value="id")int id,Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
			Category category = categoryService.get(id);
			model.addAttribute("Category1", category);
			model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "editcategory";
		}
		
		@GetMapping("/deleteCategory/{id}")
		public String DeleteCategory(@PathVariable(value="id")int id) {
			//call delete project name method
			this.categoryService.delete(id);
			return "redirect:/category";
			
		}
		
		
		@RequestMapping("/MyAssignedProject")
		public String TodayTask(Model model, HttpServletRequest request) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
	    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
	    	HashSet<EmployeeDetails> employees = new HashSet<EmployeeDetails>();
	    	
	    	List<EmployeeDetails> employees1 = employeeserivce.FilterByReportingManager(employeeserivce.Update(request.getUserPrincipal().getName()));
	    	for(EmployeeDetails e:employees1) {
	    		employees.add(e);
	    		List<EmployeeDetails> employees2 = employeeserivce.FilterByReportingManager(e.getId().intValue());
	    		for(EmployeeDetails e1:employees2) {
	    			employees.add(e1);
	    			List<EmployeeDetails> employees3 = employeeserivce.FilterByReportingManager(e1.getId().intValue());
	    			for(EmployeeDetails e2:employees3) {
	    				employees.add(e2);
	    				List<EmployeeDetails> employees4 = employeeserivce.FilterByReportingManager(e2.getId().intValue());
	    				for(EmployeeDetails e3:employees4) {
	    					employees.add(e3);
	    				}
	    			}
	    		}
	    	}
//	    	for(Employee e:employees) {
//	    		System.out.println(e.getEmail());
//	    	}
	    	model.addAttribute("ListUserName", employees);
	    	int n = loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName()));
	    	String status = "false";
	    	if(n==3 || n==4 || n==5) {
	    		status = "true";
	    	}
	    	model.addAttribute("status", status);
	    	model.addAttribute("EmployeeList", employeeserivce.listAll());
	    	model.addAttribute("UserName",employeeserivce.Update(request.getUserPrincipal().getName()));
	    	List<AddCustomer> c1 = customerService.listAll();
			model.addAttribute("CompanyList", c1);
			HashSet<projectName> t = new HashSet<projectName>();
			t.addAll(ProjectService.getAllprojectName());
			model.addAttribute("Projectlist", t);
			HashSet<Category> categories = new HashSet<Category>();
			categories.addAll(categoryService.listAll());
			model.addAttribute("Categorylist", categories);
			model.addAttribute("ProjectMap", projectNameAuthoritiesService.listAll());
	    	model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
			return "ViewProjectList";	
		}
		
		@GetMapping("/checkprojectname")
		public @ResponseBody String CheckProjectName(@RequestParam(name="name") Long name, @RequestParam(name="name2") String name2, @RequestParam(name="name3") String name3) {
			if(ProjectService.findByProjectNameCompAndCateg(name,name2,name3) == 0) {
				return "true";
			}
			else {
				return "false";
			}
		}
		
		
		@GetMapping("/checkcompanyname")
		public @ResponseBody String CheckCompanyName(@RequestParam(name="name") String name) {
			if(customerService.findByCompanyName1(name) == 0) {
				return "true";
			}
			else {
				return "false";
			}
		}
		
//		@GetMapping("/ProjectList_exportExcel")
//	    public void ProjectListexportToExcel(HttpServletResponse response) throws IOException {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	        response.setContentType("application/octet-stream");
//	        String headerKey = "Content-Disposition";
//	        String headerValue = "attachment; filename=Project List.xlsx";
//	        response.setHeader(headerKey, headerValue);
//	         
//	       List<projectName> List =ProjectService.getProjectNameDataByCompanyID(employeeserivce.getCompanyID(auth.getName()));
//	       ProjectDetailsExcel excelExporter = new ProjectDetailsExcel(List);
//	         
//	       excelExporter.export(response);    
//	    } 
}
