package com.crm.portal.ExcelUpload;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.crm.portal.Accounts.Account;
import com.crm.portal.Accounts.AccountService;
import com.crm.portal.Contacts.Contact;
import com.crm.portal.Contacts.ContactService;
import com.crm.portal.Deals.Deal;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;

@Controller
public class ExcelController {

	
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private ContactService contactservice;
	@Autowired
	private AccountService accServic;
	
	@Autowired
	private ExcelReader excelreader;
	@Autowired
	private ExcelReaderAccount excelreaderacc;
	
	
	@GetMapping("/excelupload")
	public String Excel(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);

//		model.addAttribute("OwnerList", ownserivce.listAll());
	      // System.out.println("user"+request.getUserPrincipal().getName());
	       EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		  // System.out.println("user1"+employee.getCompanyid());
		   model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
       model.addAttribute("employee", employee);
	
		return "Lead/ExcelUpload";
	}
	
//	@PostMapping("/leadsave")
//	public String LeadSave(@RequestParam("readingexcelFile")  MultipartFile readingexcelFile) throws IOException{
//		excelreader.excelToTutorials(readingexcelFile.getInputStream());
//	
//		return "redirect:/excelupload";
//	}
//	
	
	@GetMapping("/accountexcelupload")
	public String ExcelAccount(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("OwnerList", ownserivce.listAll());
	      // System.out.println("user"+request.getUserPrincipal().getName());
	       EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		  // System.out.println("user1"+employee.getCompanyid());
		   model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
           model.addAttribute("employee", employee);
		return "Accounts/AccountExcel";
	}
	
	@PostMapping("/accountsave1")
	public String accountSave(@RequestParam("readingPdfFile")  MultipartFile readingexcelFile,@RequestParam("accountowner") String accountowner) throws IOException{
//		excelreaderacc.excelToTutorials(readingexcelFile.getInputStream(), accountowner);
		
		EmployeeDetails l1= employeeserivce.get(Long.parseLong(accountowner));
		String accountowner1 = l1.getFirstname() + " " + l1.getLastname();
       
		excelreaderacc.excelToTutorials1(readingexcelFile.getInputStream(), accountowner1, l1.getId());
		
//		LocalDateTime mydate = LocalDateTime.now();
//		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		    
//		    Account a = new Account();
//		    a.setCurrentdate(mydate.format(myformatter));
//		    accServic.save(a);
		    return "redirect:/accountexcelupload";
	}
	
	@GetMapping("/contactexcelupload")
	public String Excelcontact(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
	       EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		   model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
           model.addAttribute("employee", employee);
	     return "Contacts/ContactExcel";
	}
	
	@PostMapping("/contactsave")
	public String ContactSave(@RequestParam("readingexcelFile")  MultipartFile readingexcelFile,@RequestParam("contactowner") String contactowner) throws IOException{
		EmployeeDetails l1= employeeserivce.get(Long.parseLong(contactowner));
		String contactowner1 = l1.getFirstname() + " " + l1.getLastname();
		excelreader.excelToTutorials(readingexcelFile.getInputStream(), contactowner1, l1.getId());
		   
		
//		    LocalDateTime mydate = LocalDateTime.now();
//		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
		    
//		    Contact c = new Contact();
//		    c.setEmployee_id(l1.getId());
//		    c.setCurrentdate_time(mydate.format(myformatter));
//		    contactservice.save(c);
		
////		EmployeeDetails l1= employeeserivce.get(Long.parseLong(dealowner));
////		Contact ce = new Contact();
//		    System.out.println("1");
//		    for(Contact c:ExcelReader.readExcel(readingexcelFile.getInputStream(), contactowner)) {
//			System.out.println(c.getFirstname());
//			contactservice.save(c);
//		}
////		contactservice.save(ExcelReader.readExcel(readingexcelFile.getInputStream(), contactowner));
//		System.out.println("2");
		return "redirect:/contactexcelupload";
	}
	
}
