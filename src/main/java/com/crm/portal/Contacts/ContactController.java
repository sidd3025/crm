package com.crm.portal.Contacts;

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

import com.crm.portal.Accounts.AccountService;
import com.crm.portal.Accounts.AnnualService;
import com.crm.portal.Campagin.CampaginService;
import com.crm.portal.Company.CompanyService;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Invoice.ProductsServices;
import com.crm.portal.Lead.AccountTypeService;
import com.crm.portal.Lead.EmpService;
import com.crm.portal.Lead.IndService;
import com.crm.portal.Lead.LeadMasterService;
import com.crm.portal.Lead.LeadService;
import com.crm.portal.Lead.OwnerService;
import com.crm.portal.Lead.StageService;
import com.crm.portal.Lead.StageStatusService;
import com.crm.portal.Lead.Status;
import com.crm.portal.Lead.StatusService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;
import com.crm.portal.Report.ReportLeadService;

@Controller
public class ContactController {
	
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private LeadMasterService leadmasterservice;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private AccountService accountserivce;
	@Autowired
	private IndService indservice;
	@Autowired
	private StatusService statusservice;
	@Autowired
	private AnnualService annualservice;
	@Autowired
	private EmpService empserivce;
	@Autowired
	private OwnerService ownserivce;
	@Autowired
	private AccountTypeService accserivce;
	@Autowired
	private CompanyService companyservice;
	@Autowired
	private ReportLeadService reportleadserivce;
	@Autowired
	private StageService stageserivce;
	@Autowired
	private AccountService accService;
	@Autowired
	private RemarksService remarkservice;
	@Autowired 	
    private ContactStatusService contstatusservice;
	@Autowired
	private StageStatusService stagestatusservice;
	@Autowired
	private ProductsServices productservice;
	@Autowired
	private CampaginService campservice;
	
	@GetMapping("/contact/add")
	public String ContactAdd(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("ContactList", contactService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("AccountList", accountserivce.listAll());
		
		model.addAttribute("IndustryList", indservice.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("contactList",contstatusservice.listAll());
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		model.addAttribute("AccountList", accountserivce
        model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	
        model.addAttribute("employee", employee);
		
		return "Contacts/ContactsAdd";
	}
	
	@GetMapping("/contactduplicaterefence")
	public @ResponseBody String HomeGraphCharts(@RequestParam("name") String name) {
		if(contactService.GetContactIDCount(name)!=0) {
		
			return "true";
		}else{
			return "false";	
		}
		
	}
	
	@PostMapping("/contact/add/save")
	public String contactSave(@RequestParam("Contactowner") String contactowner,@RequestParam("Firstname") String Firstname,@RequestParam("Lastname") String Lastname,@RequestParam("Accountname") String accname,
			@RequestParam("Title") String Title,@RequestParam("Email") String Email,@RequestParam("Department") String depart,@RequestParam("Phone") String Phone,@RequestParam("MobileNumber") String mobile,
            @RequestParam(name="ContactSource",required=false) String csource,@RequestParam("linked") String linkedin,@RequestParam("SkypeID") String SkypeID,@RequestParam("Secondaryemail") String Secondaryemail,
            @RequestParam("Twitter") String Twitter,@RequestParam("address1") String add1,@RequestParam("address2") String add2,@RequestParam("country") String country,@RequestParam("state") String state,
            @RequestParam("city") String city,@RequestParam("pincode") String pincode,@RequestParam("executive")String executive_level,@RequestParam("Contactstatus")String contactstatus,
            @RequestParam("Industry")String industry,@RequestParam("description")String description,@RequestParam("mobile2")String mobile2,
            @RequestParam("phone2")String phone2) {
	
		
		if(contactService.GetContactIDCount( Email)==0) {
//		  current date
		    LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = new Date();
//			String Current_Date = f.format(date);
		    EmployeeDetails l1= employeeserivce.get(Long.parseLong(contactowner));
		    
		Contact c = new Contact();
		c.setContactowner(l1.getFirstname()+" "+l1.getLastname());
//		c.setContactowner(contactowner);
		c.setEmployee_id(l1.getId());
		c.setFirst_name(Firstname);
		c.setLast_name(Lastname);
		c.setAccountname(accname);
		c.setTitle(Title);
		c.setEmail(Email);
		c.setDepartment(depart);
		c.setPhone_number(Phone);
		c.setMobile_number(mobile);
		c.setLinkedin_bio(linkedin);
		c.setExecutive_level(executive_level);
		c.setIndustry(industry);
	    c.setContactsource(csource);
	    c.setSkypeid(SkypeID);
	    c.setSecondaryemail(Secondaryemail);
	    c.setTwitter(Twitter);
	    c.setAddresslin1(add1);
	    c.setAddresslin2(add2);
	    c.setCountry(country);
	    c.setCurrentdate_time(mydate.format(myformatter));
	    c.setCity(city);
	    c.setState(state);
	    c.setPincode(pincode);
	    c.setContactstatus(contactstatus);
	    c.setDescription(description);
	    c.setPhone_number2(phone2);
	    c.setMobile_number2(mobile2);
//	    c.setCurrentdate(Current_Date);
	    contactService.save(c);
		}
	    return "redirect:/contactlist";
	}
	
	// Contact edit save method////
	@PostMapping("/contact/add/save1")
	public String contactSave(@ModelAttribute("ContactList") Contact cont, @RequestParam("contactstatus")String contactstatus
			) {
//		  current date
		    LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//		    save part remakr status //.....................
		    if(!(cont.getContactstatus().equals("-None-"))) {
		    	RemarksModel rm=new RemarksModel();
		    	rm.setContact_id(cont.getId().toString());
		    	rm.setContactstatus(cont.getContactstatus());
		    	rm.setCurrentdate_time(mydate.format(myformatter));
		    	rm.setEmployee_id(cont.getEmployee_id());
		    	rm.setDescription(cont.getDescription());
		    	remarkservice.save(rm);
		    }
		    		
//		    EmployeeDetails l1= employeeserivce.get(Long.parseLong(contactowner));
//		    Contact c = new Contact();
//		    c.setContactowner(l1.getFirstname()+" "+l1.getLastname());
//		    c.setEmployee_id(l1.getId());
//		    cont.setContactstatus(contactstatus);
		    cont.setCurrentdate_time(mydate.format(myformatter)); 
	    contactService.save(cont);
		return "redirect:/contactlist";
	}
	
//	@GetMapping("/contactlist")
//	public String Contactlist(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
////		model.addAttribute("LeadList", LeadService.listAll());
//		model.addAttribute("ContactList", contactService.listAll());
//		return "Contacts/ContactViewList";
//
//	}
	
	
//     list of employee 	
	@GetMapping("/contactlist")
	public String contactlistr(Model model,HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("ContactList", contactService.listAll());
		
//		///// authority check 
		if(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())) == 4) {
//		/// seraching employee details of login person/////////////
			EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		List <Contact> cont = new LinkedList<>();
	    
//		//// find the emp under employee menager.....
		for(EmployeeDetails emp: employeeserivce.FindNameByempID(employee.getId().intValue())) {
//			//// showing employee manager page 2 level
			cont.addAll(contactService.GetIDById(emp.getId()));
	    }
//   //// showing self details of employee......
		cont.addAll(contactService. GetIDById(employee.getId()));
		model.addAttribute("ContactList", cont);
		}
		else {
//			list all for admin.......
			EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
			List <Contact> cont = new LinkedList<>();
//			////// it will find according to company id to show details for amdin.....
		    for(EmployeeDetails emp: employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid())) {
//		    	it will show all employee under the company
		    	cont.addAll(contactService.GetIDById(emp.getId()));
		    }
//		    Self details of admin
			cont.addAll(contactService.GetIDById(employee.getId()));
			model.addAttribute("ContactList", cont);
		}
		return "Contacts/ContactViewList";
	}
	
	@GetMapping("/EditAllContact/{id}")
	public String EditAllCourses(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
    	Contact cont = contactService.get(id);
    	model.addAttribute("contact", cont);
    	System.out.println(contactService.get(id).getFirst_name());
    	model.addAttribute("ContactList", contactService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("AccountList", accountserivce.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("contactList",contstatusservice.listAll());
		
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
		
      	model.addAttribute("employee", employee);
		return "Contacts/ContactEdit";
	}
	
	@GetMapping("/contactconvertlead/{id}")
	public String contactconvertlead(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
    	Contact cont = contactService.get(id);
    	model.addAttribute("contact", cont);
    	
    	model.addAttribute("firstname", cont.getFirst_name());
    	model.addAttribute("lastname", cont.getLast_name());
    	model.addAttribute("lastname", cont.getLast_name());
    	model.addAttribute("accountname", cont.getAccountname()); 
    	
    	model.addAttribute("title", cont.getTitle());
    	model.addAttribute("email", cont.getEmail());
    	model.addAttribute("contactsource", cont.getContactsource());
    	model.addAttribute("contactstatus", cont.getContactstatus());
    	model.addAttribute("phone", cont.getPhone_number());
    	model.addAttribute("industry", cont.getIndustry());
    	model.addAttribute("secoundaryemail", cont.getSecondaryemail());
    	model.addAttribute("skypeid", cont.getSkypeid());
    	model.addAttribute("twitter", cont.getTwitter());
    	
    	
    	model.addAttribute("id", id);
    	
    	System.out.println(contactService.get(id).getFirst_name());
    	model.addAttribute("ContactList", contactService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("AccountList", accountserivce.listAll());
	    model.addAttribute("AnnualList", annualservice.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("IndustryMasterList", indservice.listAll()); 
		model.addAttribute("EmployeeList", empserivce.listAll()); 
		model.addAttribute("AccountTypeList", accserivce.listAll());
        model.addAttribute("OwnerList", ownserivce.listAll());
        model.addAttribute("StageList", stageserivce.listAll());
        model.addAttribute("Accountlist", accService.listAll()); 
        model.addAttribute("StagestatusList",stagestatusservice.listAll());
        model.addAttribute("productList", productservice.listAll());
        model.addAttribute("CampaginList", campservice.listAll());
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	model.addAttribute("employee", employee);
		return "Lead/LeadAdd";
	}
	
//	 For employee
	@GetMapping("/contactreportlist")
	public String ContactReportlistadmin(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		
		 EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		 model.addAttribute("ContactList", contactService.GetIDById(employee.getId()));
		 return "Report/ContactReport";

	}
	
//	for Admin report
	@GetMapping("/contactreportlistadmin")
	public String LeadReportlist(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("ContactList1", contactService.listAll());
		return "Contacts/ContactReportAdmin";

	}
	
//	contact view part
	
	@GetMapping("/ViewAllContact/{id}")
	public String ViewAllCourses(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
//    	"cont" is variable where all the data is stored of contact table.....
//    	and before showing contact table details we should call "id" contact...
    	Contact cont = contactService.get(id);
    	model.addAttribute("contact", cont);
//    	RemarksModel remarks = remarkservice.get(id);
//    	model.addAttribute("remark",remarks);
    	
//    	it is for filtering the contact_id to show the remarks status of particular id.....
    	
    	List<RemarksModel> r = remarkservice.getFilterByContactID(Integer.toString(id));
    	model.addAttribute("remark",r);
    	System.out.println(contactService.get(id).getFirst_name());
    	model.addAttribute("RemarkList", remarkservice.listAll());
    	model.addAttribute("ContactList", contactService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("AccountList", accountserivce.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
		
      	model.addAttribute("employee", employee);
		return "Contacts/ContactView";
	
	}

	@GetMapping("/contact/status")
	public String Leadstatus(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("ContactStatusList", contstatusservice.listAll());
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		  model.addAttribute("listEmployee",employeeserivce.GetListEmployeeDetailsByEmail(employee.getCompanyid()));
		  model.addAttribute("listCompany",companyservice.listAll());
		  
		return "Masters/ContactStatus";
	}

	@PostMapping("/contactstatus/save")
	public String LeadstatusSave(@RequestParam("contactstatus") String contactstatus,HttpServletRequest request) {
		Long companyid= companyservice.GetCompanyDetailsByEmail(request.getUserPrincipal().getName());
		
		ContactStatus cs = new ContactStatus();
		cs.setContactstatus(contactstatus);
		cs.setCompnayid(companyid);
		contstatusservice.save(cs);
		return "redirect:/contacts/status";
	}	
	
	
}
