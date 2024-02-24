package com.crm.portal.Sales;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.portal.Accounts.AccountStatus;
import com.crm.portal.Company.CompanyService;
import com.crm.portal.Contacts.Contact;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;

@Controller
public class SalesController {

	
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private CompanyService companyservice;
	@Autowired
	private SalesService saleservice;
	
	
	@GetMapping("/sale/add")
	public String ContactAdd(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());

        model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	
        model.addAttribute("employee", employee);
		
		return "Sales/SaleBudgetForm";
	}
	
	
	@PostMapping("/sale/save")
	public String contactSave(@RequestParam("annualfromdate") String annualfromdate,@RequestParam("annualtodate") String annualtodate,
			@RequestParam double targetAmount,@RequestParam("q1fdate") String q1fdate,
			@RequestParam("q1tdate") String q1tdate,@RequestParam("q2fdate") String q2fdate,@RequestParam("q2tdate") String q2tdate,
			@RequestParam("q3fdate") String q3fdate,@RequestParam("q3tdate") String q3tdate,
            @RequestParam("q4fdate") String q4fdate,@RequestParam("q4tdate") String q4tdate,
            @RequestParam("q1perctange") double quarter1Percentage,
            @RequestParam("q2perctange") double quarter2Percentage,
            @RequestParam("q3perctange") double quarter3Percentage,
            @RequestParam("q4perctange") double quarter4Percentage,
//            @RequestParam("q1perctange") String q1perctange,@RequestParam("q2perctange") String q2perctange,
//            @RequestParam("q3perctange") String q3perctange,@RequestParam("q4perctange") String q4perctange,
            @RequestParam("q1value") String q1value,@RequestParam("q2value") String q2value,
            @RequestParam("q3value") String q3value,@RequestParam("q4value") String q4value,HttpServletRequest request,Model model) {
	
//		 Long EmployeeDetails  = employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		 Long companyid= companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());	  
		Sales s =new Sales();
		
//		s.setAnnualbudget(annualbudget);
		s.setTargetamount(targetAmount);
		s.setFrom_date(annualfromdate);
		s.setTo_date(annualtodate);
		s.setQ1fdate(q1fdate);
		s.setQ1tdate(q1tdate);
		s.setQ2fdate(q2fdate);
		s.setQ2tdate(q2tdate);
		s.setQ3fdate(q3fdate);
		s.setQ3tdate(q3tdate);
		s.setQ4fdate(q4fdate);
		s.setQ4tdate(q4tdate);
		s.setQ1value(q1value);
		s.setQ2value(q2value);
		s.setQ3value(q3value);
		s.setQ4value(q4value);
		s.setQ1percentage(quarter1Percentage);
		s.setQ2percentage(quarter2Percentage);
		s.setQ3percentage(quarter3Percentage);
		s.setQ4percentage(quarter4Percentage);
//		s.setQ1percentage(q1perctange);
//		s.setQ2percentage(q2perctange);
//		s.setQ3percentage(q3perctange);
//		s.setQ4percentage(q4perctange);
		s.setCompany_id(companyid);
		s.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
		
		
	        double[] percentages = {quarter1Percentage, quarter2Percentage, quarter3Percentage, quarter4Percentage};

	        double totalPercentage = 0;
	        for (double percentage : percentages) {
	            totalPercentage += percentage;
	        }

	        double targetSales = targetAmount * (totalPercentage / 100);

	        double[] sales = new double[4];
	        for (int i = 0; i < 4; i++) {
	            sales[i] = targetSales * (percentages[i] / totalPercentage);
	        }

	        double totalSales = 0;
	        for (double sale : sales) {
	            totalSales += sale;
	        }

	        double averageSales = totalSales / 4;

	        model.addAttribute("sales", sales);
	        model.addAttribute("totalSales", totalSales);
	        model.addAttribute("averageSales", averageSales);
		
		saleservice.save(s);
		
	    return "redirect:/sale/add";
	}
	
	@GetMapping("/salelist")
	public String annualEmp(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("SaleList", saleservice.listAll());
		return "Sales/SaleBudgetList";
	}	
	
	
}
