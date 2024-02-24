package com.crm.portal.Accounts;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.portal.Company.CompanyService;
import com.crm.portal.Contacts.Contact;
import com.crm.portal.Contacts.ContactService;
import com.crm.portal.Contacts.ContactStatus;
import com.crm.portal.Contacts.RemarksModel;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Lancapemaster.LandscapeEmailService;
import com.crm.portal.Lead.AccountTypeService;
import com.crm.portal.Lead.EmpService;
import com.crm.portal.Lead.Employee;
import com.crm.portal.Lead.IndService;
import com.crm.portal.Lead.Lead;
import com.crm.portal.Lead.OwnerService;
import com.crm.portal.Lead.StatusService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;
import com.crm.portal.Report.ReportLeadService;
//import com.learningmodule.AdminAddStructure.AddExternalTrainer;
//import com.learningmodule.Login.UserDetails.CompanyDetails;




@Controller
public class AccountController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private AccountService accService;
	@Autowired
	private IndService indservice;
	@Autowired
	private OwnerService ownserivce;
	@Autowired
	private AccountTypeService accserivce;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private EmpService empserivce;
	@Autowired
	private ReportLeadService reportleadserivce;
	@Autowired
	private FeedbackService feedbackserivce;
	@Autowired
	private CompanyService companyservice;
	@Autowired
	private EmployeeService empservice;
	@Autowired
	private AnnualService annualservice;
	@Autowired
	private StatusService statusservice;
	@Autowired
	private AccRemarksService accremarkservice;
	@Autowired
	private AccStatusService accstatusservice;
	@Autowired
	private LandscapeEmailService emailervice;
	
	
	@GetMapping("/account/add")
	public String Account(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("AccountList", accService.listAll());
		model.addAttribute("AccountstatusList", accstatusservice.listAll());
		model.addAttribute("IndustryMasterList", indservice.listAll()); 
		model.addAttribute("AccountTypeList", accserivce.listAll());
		model.addAttribute("EmployeeList", empserivce.listAll()); 
		model.addAttribute("OwnerList", ownserivce.listAll());
		model.addAttribute("AnnualList", annualservice.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("LandList", emailervice.listAll());
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
       
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	 
        model.addAttribute("employee", employee);
	
		return "Accounts/Account";
	}
	
	@GetMapping("/duplicaterefence")
	public @ResponseBody String HomeGraphCharts(@RequestParam("name") String name) {
		if(accService.GetIDCount(name)!=0) {
		
			return "true";
		}else{
			return "false";	
		}
		
	}
	
	@PostMapping("/account/save")
	public String LeadSave(@RequestParam("accountowner") String accountowner,@RequestParam("Accountname") String accname,
			@RequestParam("Accountgroup") String accgroup,@RequestParam("Phone") String phone,@RequestParam("Accountsite") String accsite,
			@RequestParam("Fax") String fax,@RequestParam("email") String email,@RequestParam("geography") String geography,
			@RequestParam("Parentaccount") String parentacc,@RequestParam("Website") String website,@RequestParam("Accountnumber") String accnumber,
            @RequestParam("Tickersymbol") String ticker,@RequestParam("accounttype") String acctype, @RequestParam("Industry") String Industry, 
            @RequestParam("ownership") String ownership, @RequestParam("SIC") String sic,
            @RequestParam("AnnualRevenue") String Annual,@RequestParam("employee") String employee,@RequestParam("address1") String add1,
            @RequestParam("address2") String add2,@RequestParam("country") String country,@RequestParam("state") String state,
            @RequestParam("city") String city,@RequestParam("pincode") String pincode,@RequestParam("duns") String duns,
            @RequestParam("mobile") String mobile,@RequestParam("remarks") String remarks,@RequestParam("description") String description,
            @RequestParam("accountstatus") String accountstatus,@RequestParam("mobile2") String mobile2,
            @RequestParam("phone2") String phone2,@RequestParam("email2") String email2){
//            @RequestParam("softemail") String softemail, @RequestParam("softerp") String softerp,
//            @RequestParam("softcrm") String softcrm,@RequestParam("softhrms") String softhrms,@RequestParam("softmanufacture") String softmanufacture,
//            @RequestParam("softinventory") String softinventory,@RequestParam("softaccandfin") String softaccandfin,@RequestParam("softoperation") String softoperation,
//            @RequestParam("softpayroll") String softpayroll,@RequestParam("hardemail") String hardemail, @RequestParam("harderp") String harderp,
//			@RequestParam("hardcrm") String hardcrm,@RequestParam("hardhrms") String hardhrms,@RequestParam("hardmanufacture") String hardmanufacture,
//			@RequestParam("hardinventory") String hardinventory,@RequestParam("hardaccandfin") String hardaccandfin,@RequestParam("hardoperation") String hardoperation,
//			@RequestParam("hardpayroll") String hardpayroll){
				
		
		if(accService.GetIDCount(accname)==0) {
		
//		  current date
		 LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
//			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = new Date();
//			String Current_Date = f.format(date);
		
			EmployeeDetails l1= employeeserivce.get(Long.parseLong(accountowner));
			
		Account a = new Account();
		
		    
		    a.setAccoutowner(l1.getFirstname()+" "+l1.getLastname());
//		    a.setAccoutowner(accountowner);
		    a.setEmployee_id(l1.getId());
		    a.setAccountname(accname);
		    a.setAccountgroup(accgroup);
		    a.setPhone(phone);
		    a.setMobile(mobile);
		    a.setAccountsite(accsite);
		    a.setFax(fax);
		    a.setEmail(email);
		    a.setParentaccount(parentacc);
		    a.setWebsite(website);
		    a.setAccountnumber(accnumber);
		    a.setTickersymbol(ticker);
		    a.setAccounttype(acctype);
		    a.setIndustry(Industry);
		    a.setOwnership(ownership);
		    a.setSic_code(sic);
		    a.setAnnualRevenue(Annual);
		    a.setEmployee(employee);
		    a.setAddresslin1(add1);  
		    a.setAddresslin2(add2);
		    a.setCity(city);
		    a.setState(state);
		    a.setCountry(country);		  
		    a.setCurrentdate(mydate.format(myformatter));
		    a.setDuns(duns);
		    a.setDescription(description);
		    a.setRemarks(remarks);
		    a.setAccountstatus(accountstatus);
		    a.setGeography(geography);
		    a.setPhone2(phone2);
		    a.setMobile2(mobile2);
		    a.setEmail2(email2);
		    //		    a.setSoftware_Accandficc(softaccandfin);
//		    a.setSoftware_CRM(softcrm);
//		    a.setSoftware_email(softemail);
//		    a.setSoftware_ERP(softerp);
//		    a.setSoftware_HRMS(softhrms);
//		    a.setSoftware_manufacture(softmanufacture);
//		    a.setSoftware_inverntory(softinventory);
//		    a.setSoftware_operation(softoperation);
//		    a.setSoftware_payroll(softpayroll);
//		    a.setHardware_Accandficc(hardaccandfin);
//		    a.setHardware_crm(hardcrm);
//		    a.setHardware_email(hardemail);
//		    a.setHardware_erp(harderp);
//		    a.setHardware_hrms(hardhrms);
//		    a.setHardware_inverntory(hardinventory);
//		    a.setHardware_manufacture(hardmanufacture);
//		    a.setHardware_operation(hardoperation);
//		    a.setHardware_payroll(hardpayroll);
		    
		    accService.save(a);
		}
		    
		    return "redirect:/accountlist";
	}
	
//	@GetMapping("/getlandscapeinsidedropdown")
//	public @ResponseBody List<Landscape> ViewTask(@RequestParam(name="name",required=false) String name) {
//		List<Landscape> l = new LinkedList<>();
//		for(Landscape la : landscapeService.listAll()) {
//			if(la.getSoftwarestream().equals(name)) {
//				l.add(la);
//			}
//		}
//	return l;				
//	}
	
	
	
	
	
//	@GetMapping("/accountlist")
//	public String accountlist(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
////		model.addAttribute("LeadList", LeadService.listAll());
//		model.addAttribute("AccountList1", accService.listAll());
////		model.addAttribute("AccountList1", accService.);
//		
//		return "Accounts/AccountViewList";
//	}
	
	@GetMapping("/accountlist")
	public String contactlistr(Model model,HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("AccountList", accService.listAll());
	
//		if(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())) == 4) {
////			r et = externalTrainerService.FindAddExternalTrainerNameByEmail(request.getUserPrincipal().getName());
//		 EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		 model.addAttribute("AccountList1", accService.GetIDById(employee.getId()));
//		}
//		else {
//			model.addAttribute("AccountList", accService.listAll());
//		}
//		return "Accounts/AccountViewList";
		
//////////////////// this part is for employe can see his or her own data..///////////////////////		
		
		if(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())) == 4) {
//			/// seraching employee details of login person/////////////
				EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
			List <Account> acc = new LinkedList<>();
		    
//			//// find the emp under employee menager.....
			for(EmployeeDetails emp: employeeserivce.FindNameByempID(employee.getId().intValue())) {
//				//// showing employee manager page 2 level
				acc.addAll(accService.GetIDById(emp.getId()));
		    }
	//   //// showing self details of employee......
			acc.addAll(accService.GetIDById(employee.getId()));
			model.addAttribute("AccountList1", acc);
			}
			else {
//				list all for admin.......
				EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
				List <Account> acc = new LinkedList<>();
//				////// it will find according to company id to show details for amdin.....
			    for(EmployeeDetails emp: employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid())) {
//			    	it will show all employee under the company
			    	acc.addAll(accService.GetIDById(emp.getId()));
			    }
//			    Self details of admin
			    acc.addAll(accService.GetIDById(employee.getId()));
				model.addAttribute("AccountList1", acc);
			}
		return "Accounts/AccountViewList";
	}
	
	
	@GetMapping("/EditAllAccount/{id}")
	public String EditAllAccount(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
    	Account acc1 = accService.get(id);
    	model.addAttribute("account", acc1);
    	model.addAttribute("IndustryMasterList", indservice.listAll()); 
		model.addAttribute("AccountTypeList", accserivce.listAll());
		model.addAttribute("EmployeeList", empserivce.listAll()); 
		model.addAttribute("OwnerList", ownserivce.listAll());
		model.addAttribute("AnnualList", annualservice.listAll());
		model.addAttribute("AccountList", accService.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("AccountstatusList", accstatusservice.listAll());
		
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
     	model.addAttribute("employee", employee);
		return "Accounts/AccountEdit";
	}
	
	@PostMapping("/account/add/save1")
	public String contactSave(@ModelAttribute("AccountList1") Account acc2 , @RequestParam("accountstatus")String accountstatus) {
//		  current date
		    LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//		    save part remakr status //.....................
		    if(!(acc2.getAccountstatus().equals("-None-"))) {
		    	AccountRemarks ar=new AccountRemarks();
		    	ar.setAccount_id(acc2.getId().toString());
		    	ar.setAccountstatus(acc2.getAccountstatus());
		    	ar.setCurrentdate_time(mydate.format(myformatter));
		    	ar.setEmployee_id(acc2.getEmployee_id());
		    	ar.setDescription(acc2.getDescription());
		    	ar.setRemarks(acc2.getRemarks());
		    	accremarkservice.save(ar);
		    }		
//		    EmployeeDetails l1= employeeserivce.get(Long.parseLong(contactowner));
//		    Contact c = new Contact();
//		    c.setContactowner(l1.getFirstname()+" "+l1.getLastname());
//		    c.setEmployee_id(l1.getId());
//		    cont.setContactstatus(contactstatus);
		    acc2.setCurrentdate(mydate.format(myformatter));
		    accService.save(acc2);
		return "redirect:/accountlist";
	}

//	Report list............ for admin
	@GetMapping("/reportlistaccount")
	public String LeadReportlist(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("AccountList1", accService.GetIDById(employee.getId()));
		
		model.addAttribute("AccountList", accService.listAll());
		return "Report/AccountReport";
	}
	                           
//   for employee
	@GetMapping("/accountreportlistadmin")
	public String accountReportlist(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("LeadList", LeadService.listAll());
		
//		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		model.addAttribute("AccountList1", accService.GetIDById(employee.getId()));
		
		model.addAttribute("AccountList1", accService.listAll());
		return "Accounts/AccountReports";

	}
	
	@GetMapping("/ViewAllAccount/{id}")
	public String ViewAllaccount(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
//    	"cont" is variable where all the data is stored of contact table.....
//    	and before showing contact table details we should call "id" contact...
    	Account acc = accService.get(id);
    	model.addAttribute("account", acc);
//    	RemarksModel remarks = remarkservice.get(id);
//    	model.addAttribute("remark",remarks);
    	
//    	it is for filtering the contact_id to show the remarks status of particular id.....
    	
    	List<AccountRemarks> r = accremarkservice.getFilterByAccountID(Integer.toString(id));
    	model.addAttribute("remark",r);
//    	System.out.println(accService.get(id).getFirst_name());
    	model.addAttribute("RemarkList", accremarkservice.listAll());
//    	model.addAttribute("ContactList", contactService.listAll());
//		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("AccountList", accService.listAll());
//		model.addAttribute("StatusList", statusservice.listAll());
		
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
		
      	model.addAttribute("employee", employee);
		return "Accounts/AccountView";
	}
	
	
	/////////////////////////////////Feedback Part///////////////////////////////////
	
	@GetMapping("/feedback/add")
	public String Feedback(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("FeedbackList", feedbackserivce.listAll());
//		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		Feedback feedback = new Feedback();
		model.addAttribute("feedback", feedback);
		model.addAttribute("FeedbackList", feedbackserivce.listAll());
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
       
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	 
        model.addAttribute("employee", employee);
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("UserName", employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
		return "Feedback_Form";
	}
	
	@PostMapping("/feeback/save")
	public String FeedBackSave(@RequestParam("feedbackowner") String feedbackowner,@RequestParam("type") String type,@RequestParam("description") String description,
			HttpServletRequest request){
		
//		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//		String Current_Date = f.format(date);
		
		LocalDateTime mydate = LocalDateTime.now();
		DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
		
		EmployeeDetails l1= employeeserivce.get(Long.parseLong(feedbackowner));
		EmployeeDetails c= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		System.out.print("comp : " + c);
		
		Feedback feed = new Feedback();
		
		feed.setDescription(description);
		feed.setType(type);
		feed.setFeedbackowner(l1.getFirstname()+" "+l1.getLastname());
		feed.setEmployee_id(l1.getId().intValue());
		feed.setCompany_id(c.getCompanyid());
		feed.setCurrentdate(mydate.format(myformatter));
		feedbackserivce.Save(feed);
		return"redirect:/feedback/add";
	}
	

	
	@GetMapping("/FeedbackList")
	public String ApplyFormList(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("FeedbackList", feedbackserivce.listAll());
		model.addAttribute("ListUserName", employeeserivce.listAll());
		
		return "FeedbackList";
	}
	
//	   Annual Revenu Part ///
	@PostMapping("/annualrevenue/save")
	public String annualSave(@RequestParam("annual") String annual) {
		AnnualRevenue a=new AnnualRevenue();
	      a.setAnnual_revenu(annual);
	      annualservice.save(a);
		return "redirect:/master/annualrevenu ";
	}
	
	@GetMapping("/master/annualrevenu")
	public String annualEmp(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("AnnualList", annualservice.listAll());
		return "Masters/AnnualRevenu";
	}	

	

	  
	@GetMapping("/account/status")
	public String accountstatus(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("AccountStatusList", accstatusservice.listAll());
		
		EmployeeDetails employee= empservice.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		  model.addAttribute("listEmployee",empservice.GetListEmployeeDetailsByEmail(employee.getCompanyid()));
		  model.addAttribute("listCompany",companyservice.listAll());
		return "Masters/AccountStatus";
	}

	@PostMapping("/accountstatus/save")
	public String accountstatusSave(@RequestParam("accountstatus") String accountstatus, HttpServletRequest request) {
		
		Long companyid= companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());
		
		AccountStatus as = new AccountStatus();
		as.setAccountstatus(accountstatus);
		as.setCompnayid(companyid);
		accstatusservice.save(as);
		return "redirect:/account/status";
	}	
	
}
