package com.crm.portal.WorkplannerMaster;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.MainsidebarauthorityService;



@Controller
public class TaskController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private TaskServiceclass taskService;
	@Autowired
	private MilestoneService  milestoneService;
	@Autowired
	private MilestoneService  milestoneservice;
	@Autowired
	private projectService ProjectService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProjectNameAuthoritiesService projectNameAuthoritiesService;
	
	
	
	@GetMapping("/addtask")
	public String showTask(Model model) {
		//create model attribute to bind from data
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<com.crm.portal.Login.Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid
				(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("tasklist",taskService.listAll());
		AddTask task=new AddTask();
		model.addAttribute("task", task);
		List<AddCustomer> cc=customerService.listAll();
		model.addAttribute("Customerlist", cc);
		List<projectName> p= ProjectService.getAllprojectName();
		model.addAttribute("Projectlist", p);
		List<Category> c1 = categoryService.listAll();
		model.addAttribute("Categorylist", c1);
		model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
//		remove duplicate records
		HashMap<String, Addmilestone> uniqueMap = new HashMap<>();
        for (Addmilestone m : milestoneService.listAll()) {
            uniqueMap.putIfAbsent(m.getAdd_milestone(), m);
        }
        List<Addmilestone> m1 = new ArrayList<>(uniqueMap.values());
        model.addAttribute("milestonelist", m1);
        model.addAttribute("milestonelist1", milestoneService.listAll());
		return "WorkPlanerMaster/addtask";
	}
	
	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute("task") AddTask  task, HttpServletRequest request) {
		//save to database
		LocalDateTime mydate = LocalDateTime.now();
		DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		task.setCurrentdate(mydate.format(myformatter));
		EmployeeDetails c = employeeserivce.getEmployeeDataByEmailID(1);
		task.setCompanyid(c.getCompanyid().intValue());
//		taskService.saveAllTask(task);
		taskService.save(task);
		
		return "redirect:/addtask";
		
	}   
	
	@GetMapping("/TaskEditForm/{id}")
	public String TaskEditForm(@PathVariable(value="id")Integer id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<com.crm.portal.Login.Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid
				(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		AddTask task=taskService.get(id);
		model.addAttribute("task", task);
		List<AddCustomer> cc=customerService.listAll();
		model.addAttribute("Customerlist", cc);
		List<projectName> p= ProjectService.getAllprojectName();
		model.addAttribute("Projectlist", p);
		List<Addmilestone> m= milestoneService.listAll();
		model.addAttribute("list", m);
		List<Category> c1 = categoryService.listAll();
		model.addAttribute("Categorylist", c1);
		model.addAttribute("CompanyID",employeeserivce.getCompanyID(auth.getName()));
		return "editTask";

	}
	
	@GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable(value="id")Integer id) {
		//call delete  method
		this.taskService.deleteAuthority(id.intValue());
		return "redirect:/addtask";
		
	}
	@GetMapping("/getprojectNamedropdown")
	public @ResponseBody HashSet<projectName> Viewprojectname(HttpServletRequest request, @RequestParam(name="name",required=false)Long name, @RequestParam(name="name1",required=false)String name1) {
	    // code to fetch the project names based on the selected company name and category
		HashSet<projectName> p = new HashSet<projectName>();
		p.addAll(ProjectService.findProjectNameByCompanyNameCategoryName(name, name1));
//		System.out.println("P : " + p);
//		System.out.println(" P : " + p);
//		Employee employee = employeeService.getEmployeeDataByEmailID(request.getUserPrincipal().getName());
////		List<Integer> a = projectNameAuthoritiesService.findProjectIdsByEmployeeId(employee.getId());
//		for(projectName pn:ProjectService.findProjectNameByCompanyNameCategoryName(name, name1)) {
//			for(Integer i : a) {
//				if(pn.getId() == i) {
//					p.add(pn);
//				}
//			}
//		}
	    return p;
	}
	
	
	@GetMapping("/getdropdown")
	public @ResponseBody HashSet<projectName> Viewprojectcodepage(@RequestParam(name="name",required=false)String name,@RequestParam(name="name1",required=false)Integer name1, Model model) {
		HashSet<projectName> p = new HashSet<projectName>();
		p.addAll(ProjectService.findprojectcodeByCategoryProjectName(name, name1));
//		System.out.println("P : " + p);
	return p;				
	}
	
	@GetMapping("/getTaskDropDown")
	public @ResponseBody HashSet<AddTask> ViewTask(@RequestParam(name="name",required=false)Integer name,@RequestParam(name="name1",required=false)Integer name1,@RequestParam(name="name2",required=false)Integer name2,Model model) {
		HashSet<AddTask> p = new HashSet<AddTask>();
		p.addAll(taskService.findTaskByCompanyIdProjectIdMilestoneId(name, name1, name2));
	return p;				
	}
	
	@GetMapping("/getMileStonedropdown")
	public @ResponseBody HashSet<Addmilestone> ViewlevelofMileStone(@RequestParam(name="name",required=false)Integer name,@RequestParam(name="name1",required=false)Integer name1, Model model) {
		HashSet<Addmilestone> p = new HashSet<Addmilestone>();
		p.addAll(milestoneservice.findMilestone(name, name1));
	return  p;				
 
	}

}
