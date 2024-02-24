package com.crm.portal.Lead;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobRequestConditions;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import com.crm.portal.Accounts.AccountService;
import com.crm.portal.Accounts.AnnualService;
import com.crm.portal.Campagin.CampaginService;
import com.crm.portal.Contacts.Contact;
import com.crm.portal.Contacts.ContactService;
import com.crm.portal.Contacts.RemarksModel;
import com.crm.portal.Contacts.RemarksService;
import com.crm.portal.Deals.Deal;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Invoice.ProductMaster;
import com.crm.portal.Invoice.ProductsServices;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;
import com.crm.portal.Report.ReportLead;
import com.crm.portal.Report.ReportLeadService;



@Controller
public class LeadController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LeadService LeadService;
	@Autowired
	private LeadMasterService leadmasterservice;
	@Autowired
	private StatusService statusservice;
	@Autowired
	private IndService indservice;
	@Autowired
	private EmpService empserivce;
	@Autowired
	private OwnerService ownserivce;
	@Autowired
	private AccountTypeService accserivce;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private ReportLeadService reportleadserivce;
	@Autowired
	private StageService stageserivce;
	@Autowired
	private AccountService accService;
	@Autowired
	private ContactService contactservice;
	@Autowired
	private AnnualService annualservice;
	@Autowired
	private RemarksService remarkservice;
	@Autowired
	private StageStatusService stagestatusservice;
	@Autowired
	private ProductsServices productservice;
	@Autowired
	private LeadProductService leadproductmapservice;
	@Autowired
	private RemarksLeadService leadremarkservice;
	@Autowired
	private CampaginService campservice;
	
	
	
	@GetMapping("/lead/add")
	public String LeadAdd(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("IndustryMasterList", indservice.listAll()); 
		model.addAttribute("EmployeeList", empserivce.listAll()); 
		model.addAttribute("AccountTypeList", accserivce.listAll());
        model.addAttribute("OwnerList", ownserivce.listAll());
        model.addAttribute("StageList", stageserivce.listAll());
        model.addAttribute("StagestatusList",stagestatusservice.listAll());
        model.addAttribute("Accountlist", accService.listAll());  
        model.addAttribute("ContactList", contactservice.listAll());
        model.addAttribute("AnnualList", annualservice.listAll());
        model.addAttribute("productList", productservice.listAll());
        model.addAttribute("CampaginList", campservice.listAll());
        model.addAttribute("id", 0);
//        ProductMaster product = productservice.get(id);
//    	model.addAttribute("product", product);
    	
	       EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		   model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
           model.addAttribute("employee", employee);
		return "Lead/LeadAdd";
	}
	
	@GetMapping("/getmultipledropdown_amount")
	public @ResponseBody String EcommercePortalProductAjaxProductTypeDetails(@RequestParam(name = "name") List<String> name) throws IOException {
		double amount=0;
		for(String s:name) {
			ProductMaster p= productservice.get(Integer.parseInt(s));
		    amount=amount+p.getItem_rate();
		}
		System.out.println("hi");
		
		return String.valueOf(amount);
	}
	
	
	@PostMapping("/leadall/save")
	public String LeadallSave(@ModelAttribute("lead") Lead lead,
			@RequestParam(name = "contact_id", required = false) String contact_id,@RequestParam("leadowner") String leadowner,
			@RequestParam("AccountName") String accname,@RequestParam(name="product_id",required =false) Integer[] product_id,
			@RequestParam("leadname") String leadname,
			@RequestParam("Firstname") String Firstname,@RequestParam("Lastname") String Lastname,@RequestParam("Title") String Title,
			@RequestParam("Email") String Email,@RequestParam("Phone") String Phone,@RequestParam("Phone2") String Phone2,
			@RequestParam("Fax") String Fax,@RequestParam("mobile1") String mobile1,@RequestParam("mobile2") String mobile2,
			@RequestParam("stage") String stage,@RequestParam("LeadSource") String lsource,@RequestParam("LeadStatus") String lstatus,
			@RequestParam("Industry") String Industry,@RequestParam("NoofEmployee") String employee,@RequestParam("AnnualRevenue") String Annual,@RequestParam("SkypeID") String SkypeID,
            @RequestParam("Secoundaryemail") String Secoundaryemail,@RequestParam("Twitter") String Twitter,@RequestParam("probability") String probability,
             @RequestParam("amount") String amount,@RequestParam("campagin") String campign,
            @RequestParam("readingmaterail") MultipartFile[] readingmaterail)throws IOException{
		EmployeeDetails l1= employeeserivce.get(Long.parseLong(leadowner));
		LocalDateTime mydate = LocalDateTime.now();
	    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
     	Integer contact=Integer.parseInt(contact_id);
    	if(contact_id.equals("0")) {
		  Contact con = new Contact();
			con.setContactowner(l1.getFirstname()+" "+l1.getLastname());
			con.setEmployee_id(l1.getId());
		    con.setAccountname(accname);
		    con.setFirst_name(Firstname);
		    con.setLast_name(Lastname);
		    con.setContactsource(lsource);
		    con.setEmail(Email);
		    con.setSecondaryemail(Secoundaryemail);
		    con.setTitle(Title);
		    con.setIndustry(Industry);
		    con.setSkypeid(SkypeID);
		    con.setTwitter(Twitter);
		    con.setContactstatus(lstatus);
		    con.setPhone_number(Phone);
		    con.setPhone_number2(Phone2);
		    con.setMobile_number(mobile1);
		    con.setMobile_number2(mobile2);
		    con.setCurrentdate_time(mydate.format(myformatter));
		    contactservice.save(con);
		
		    Contact cont = contactservice.GetContact_ownerdate(l1.getFirstname()+" "+l1.getLastname(), mydate.format(myformatter));
			 
		    contact=cont.getId().intValue();
	}
		  
//		    current date
//			System.out.println(leadowner);
//			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = new Date();
//			String Current_Date = f.format(date);
			
//			  LocalDateTime mydate = LocalDateTime.now();
//			    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
			
			    
			    for (MultipartFile file : readingmaterail) {
					if(file.getSize() != 0) {
//			        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
					String containerName = "product-resources";
					BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
			        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
					
					String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//					String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
			        BlobClient blobClient = containerClient.getBlobClient(fileName);
			        
			        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
			                .setContentType(MediaType.APPLICATION_PDF_VALUE);
			            BinaryData data = BinaryData.fromStream(file.getInputStream());   
			            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
			                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
			            blobClient.uploadWithResponse(options, null, Context.NONE);
			       System.out.println(" ");
			       lead.setReadingmaterial(fileName);
					}
			    }
			    
			    
//			EmployeeDetails l1= employeeserivce.get(Long.parseLong(leadowner));
		Lead ls = new Lead();
		
		ls.setLeadowner(l1.getFirstname()+" "+l1.getLastname());
		ls.setEmployee_id(l1.getId());
		ls.setAccountname(accname);
		ls.setFirstname(Firstname);
		ls.setLastname(Lastname);
		ls.setTitle(Title);
		ls.setEmail(Email);
		ls.setPhone(Phone);
		ls.setFax(Fax);
//		ls.setProduct_id(Integer.parseInt(product_id));
//		ls.setProduct_id(Integer.parseInt(product_id));    
		ls.setLeadsource(lsource);
		ls.setLeadstatus(lstatus);
		ls.setIndustry(Industry);
		ls.setNoofemployee(employee);
		ls.setAnnualrevenue(Annual);
		ls.setSkypeid(SkypeID);
		ls.setSecoundaryemail(Secoundaryemail);
		ls.setTwitter(Twitter);	
		ls.setCurrentdate(mydate.format(myformatter));
		ls.setContactid(contact);
		ls.setProbablility(probability);
		ls.setLeadname(leadname);
		ls.setMobile1(mobile1);
		ls.setMobile2(mobile2);
		ls.setPhone2(Phone2);
		ls.setReadingmaterial(lead.getReadingmaterial());
		ls.setStagestatus(stage);
		ls.setAmount(amount);
		ls.setCampign(campign);
     	LeadService.save(ls);
     	
     	for(Integer p:product_id) { 
     	    LeadProductMaping pm= new LeadProductMaping();
     	    
     	    pm.setLeadid(ls.getId());
     	    pm.setProductid(p);
     	   leadproductmapservice.save(pm);
     	        
     	}
     	
		return "redirect:/leadlist";
	}
	
//	@PostMapping("/lead/save")
//	public String LeadSave(@RequestParam(name = "contact_id", required = false) String contact_id,@RequestParam("leadowner") String leadowner,@RequestParam("AccountName") String accname,@RequestParam("CompanyName") String Companyname,
//			@RequestParam("Firstname") String Firstname,@RequestParam("Lastname") String Lastname,@RequestParam("Title") String Title,
//			@RequestParam("Email") String Email,@RequestParam("Phone") String Phone,@RequestParam("Fax") String Fax,
//            @RequestParam("LeadSource") String lsource,@RequestParam("LeadStatus") String lstatus, @RequestParam("Industry") String Industry, @RequestParam("NoofEmployee") String employee,
//            @RequestParam("AnnualRevenue") String Annual,@RequestParam("SkypeID") String SkypeID,@RequestParam("Secoundaryemail") String Secoundaryemail,
//            @RequestParam("Twitter") String Twitter){
//	
////		  current date
//			System.out.println(leadowner);
//			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = new Date();
//			String Current_Date = f.format(date);
//			
//			EmployeeDetails l1= employeeserivce.get(Long.parseLong(leadowner));
//		Lead l = new Lead();
//		
//		l.setLeadowner(l1.getFirstname()+" "+l1.getLastname());
//		l.setEmployee_id(l1.getId());
////		l.setAccountname(accname);
//		l.setCompany(Companyname);
////		l.setFirstname(Firstname);
////		l.setLastname(Lastname);
////		l.setTitle(Title);
////		l.setEmail(Email);
////		l.setPhone(Phone);
//		l.setFax(Fax);
////		l.setLeadsource(lsource);
//		l.setLeadstatus(lstatus);
//		l.setIndustry(Industry);
//		l.setNoofemployee(employee);
//		l.setAnnualrevenue(Integer.parseInt(Annual));
////		l.setSkypeid(SkypeID);
////		l.setSecoundaryemail(Secoundaryemail);
////		l.setTwitter(Twitter);	
//		l.setCurrentdate(Current_Date);
//		l.setContactid(Integer.parseInt(contact_id));
//
//     	LeadService.save(l);
//     	
//     	
////     	for(int i=0;i<contact_id.length;i++) {
////			Master_Trainer_Course tc=new Master_Trainer_Course();
////			tc.setCourse_id(LeadService.getCourseID(courses1.getCourse()).toString());
////			tc.setTrainer_id(Integer.parseInt(contact_id[i]));
////			trainer_course_Service.save(tc);
//		return "redirect:/leadlist";
//	}
	
	@GetMapping("/leadlist")
	public String Leadlistr(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("ContactList", contactservice.listAll());
//		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		 model.addAttribute("LeadList", LeadService.GetIDById(employee.getId()));
//		  model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
//		  model.addAttribute("employee", employee);
		///// authority check 
			if(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())) == 4) {
//			/// seraching employee details of login person/////////////
				EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
			List <Lead> lead = new LinkedList<>();
		    
//			//// find the emp under employee menager.....
			for(EmployeeDetails emp: employeeserivce.FindNameByempID(employee.getId().intValue())) {
//				//// showing employee manager page 2 level
				lead.addAll(LeadService.GetIDById(emp.getId()));
		    }
	//   //// showing self details of employee......
			lead.addAll(LeadService.GetIDById(employee.getId()));
			model.addAttribute("LeadList", lead);
			}
			else {
//				list all for admin.......
				EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
				List <Lead> lead = new LinkedList<>();
//				////// it will find according to company id to show details for amdin.....
			    for(EmployeeDetails emp: employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid())) {
//			    	it will show all employee under the company
			    	lead.addAll(LeadService.GetIDById(emp.getId()));
			    }
//			    Self details of admin
			    lead.addAll(LeadService.GetIDById(employee.getId()));
				model.addAttribute("LeadList", lead);
			}
		return "Lead/LeadViewList";
	}

//	Report list............ for admin
	@GetMapping("/reportlistadmin")
	public String LeadReportlist(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("ContactList", contactservice.listAll());
		return "Lead/LeadReport";
//		return "Report/LeadReportAdmin";
	}
//   for employee
	@GetMapping("/reportlist")
	public String LeadReportlistadmin(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("ContactList", contactservice.listAll());
		 EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		 model.addAttribute("LeadList", LeadService.GetIDById(employee.getId()));
//		 model.addAttribute("ownername",employee.getFirstname());
		return "Report/LeadReportAdmin";
//		 return "Lead/LeadReport";
	}
	
	
	// Lead Edit part /////////////// 
	@GetMapping("/EditAllLead/{id}")
	public String EditAllCourses(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	Lead ld = LeadService.get(id);
    	model.addAttribute("lead", ld);
    	model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("StagestatusList",stagestatusservice.listAll());
		model.addAttribute("productList", productservice.listAll());
		 model.addAttribute("LeadproductList", leadproductmapservice.listAll());
//		model.addAttribute("productMapList", leadproductmapservice.GetIDById(LeadService.get(id));
		
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	model.addAttribute("employee", employee);
		return "Lead/LeadEdit";
	}
	
	
	
	// lead edit save ////
	@PostMapping("/lead/add/save1")
	public String contactSave(@ModelAttribute("LeadList") Lead ld, @RequestParam("leadstatus")String leadstatus) {
//		  current date 
		    LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

//		    save part remakr status //.....................
		    if(!(ld.getLeadstatus().equals("-None-"))) {
		    	 RemarksLeadModel rm=new  RemarksLeadModel();
		    	rm.setLead_id(ld.getId().toString());
		    	rm.setLeadstatus(ld.getLeadstatus());
		    	rm.setCurrentdate_time(mydate.format(myformatter));
		    	rm.setEmployee_id(ld.getEmployee_id());
		    	rm.setDescription(ld.getRemarks());
		    	leadremarkservice.save(rm);
		    }
		     
//		    ld.setCurrentdate(mydate.format(myformatter)); 
//		    ld.setStagestatus(leadstage);
            LeadService.save(ld);
		return "redirect:/leadlist";
	}
	
	@GetMapping("/contactconvertdeal/{id}")
	public String contactconvertlead(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
    	Lead lead = LeadService.get(id);
    	model.addAttribute("lead", lead);
    	
    	
    	model.addAttribute("accountname", lead.getAccountname());
    	model.addAttribute("leadsource", lead.getLeadsource());
//    	model.addAttribute("contactperson", lead.getFirstname());
//    	model.addAttribute("contactperson", lead.getLastname());
    	model.addAttribute("contactperson", lead.getFirstname() + " " + lead.getLastname());
    	model.addAttribute("dealname", lead.getLeadname());
    	model.addAttribute("Ammount", lead.getAmount());
//    	model.addAttribute("Product",);
    	System.out.println(LeadService.get(id).getLastname());
    	
    	model.addAttribute("id", id);
    	
//    	System.out.println(LeadService.get(id).getFirst_name());
    	model.addAttribute("ContactList", contactservice.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
//		model.addAttribute("AccountList", accountserivce.listAll());//
		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		model.addAttribute("IndustryMasterList", indservice.listAll()); 
		model.addAttribute("EmployeeList", empserivce.listAll()); 
		model.addAttribute("AccountTypeList", accserivce.listAll());
        model.addAttribute("OwnerList", ownserivce.listAll());
        model.addAttribute("StageList", stageserivce.listAll());
        model.addAttribute("Accountlist", accService.listAll()); 
        model.addAttribute("ProductMaplist", leadproductmapservice.listAll()); 
//        model.addAttribute("leads", LeadService.getFilterByLeadAssigntoContactID(id));
        model.addAttribute("productList", productservice.listAll());
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	model.addAttribute("employee", employee);
		return "Deals/DealAdd";
	}

	@GetMapping("/ViewBothLead/{id}")
	public String ViewAllLead(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
//    	"cont" is variable where all the data is stored of contact table.....
//    	and before showing contact table details we should call "id" contact...
//    	Lead lead = LeadService.get(id);
//    	model.addAttribute("lead", lead);
//    	model.addAttribute("file_name", lead.getReadingmaterial());
//    	System.out.println(lead.getReadingmaterial());
//    	Lead lead = new Lead();
    	model.addAttribute("lead", LeadService.getFilterByLeadAssigntoContactID(id));
    	
//    	it is for filtering the contact_id to show the remarks status of particular id.....
    	
//    	List<RemarksModel> r = remarkservice.getFilterByContactID(Integer.toString(id));
//    	model.addAttribute("remark",r);
////    	System.out.println(contactService.get(id).getFirst_name());
//    	model.addAttribute("RemarkList", leadremarkservice.listAll());
//    	model.addAttribute("ContactList", contactservice.listAll());
//    	model.addAttribute("LeadList", LeadService.listAll());
////		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
////		model.addAttribute("AccountList", accountserivce.listAll());
////		model.addAttribute("StatusList", statusservice.listAll());
//		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
//		model.addAttribute("employee", employee);
		return "Lead/AssignLeadViewBoth";
	}
	
	@GetMapping("/ViewAllLead/{id}")
	public String ViewAllCourses(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
//    	"cont" is variable where all the data is stored of contact table.....
//    	and before showing contact table details we should call "id" contact...
    	Lead lead = LeadService.get(id);
    	model.addAttribute("lead", lead);
    	model.addAttribute("file_name", lead.getReadingmaterial());
    	System.out.println(lead.getReadingmaterial());
    	
    	model.addAttribute("id", id);
//    	RemarksModel remarks = remarkservice.get(id);
//    	model.addAttribute("remark",remarks);
//    	List<Lead> l = LeadService.getAllDocuments();
//	    model.addAttribute("lead", l);
//    	it is for filtering the contact_id to show the remarks status of particular id.....
    	
    	List<RemarksLeadModel> r = leadremarkservice.getFilterByLead(Integer.toString(id));
    	model.addAttribute("remark",r);
//    	System.out.println(contactService.get(id).getFirst_name());
    	model.addAttribute("RemarkList", leadremarkservice.listAll());
    	model.addAttribute("ContactList", contactservice.listAll());
    	model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
//		model.addAttribute("AccountList", accountserivce.listAll());
		model.addAttribute("StatusList", statusservice.listAll());
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
		model.addAttribute("employee", employee);
		return "Lead/LeadView";
	}
	
	@PostMapping("/lead/doc/save1")
	public String leaddocSave(@ModelAttribute("LeadList") Lead lead, @RequestParam("approval")String approval) {
//		  current date 
		    LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = new Date();
//			String Current_Date = f.format(date);
//		Contact c
		    Lead ld = LeadService.get(lead.getId());
		  
		    ld.setCurrentdate(mydate.format(myformatter)); 
		    ld.setApproval(approval);
//		    ld.setLeadstatus(leadstatus);
		    
//	    c.setCurrentdate(Current_Date);
		    LeadService.save(ld);
		return "redirect:/leadlist";
	}
	
	
//	it is for only one field or all field ........................................................
//	@GetMapping("/lead/masteradmin")
//	public String Leadadmin(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		
//		return "Lead/LeadAdmin";
//	}
////	
//	@PostMapping("/lead/masteradmin/save")
//	public String LeadmasteradminSave(@RequestParam("Leadsource") String source,@RequestParam("Leadstatus") String Leadstatus,@RequestParam("Industry") String ind,@RequestParam("Employee") String emp,
//			@RequestParam("AccountType") String acctype,@RequestParam("Ownership") String owner) {
//		System.out.println("Source : " + source);
//		System.out.println("Leadstatus : " + Leadstatus);
//		System.out.println("ind : " + ind);
//		System.out.println("emp : " + emp);
//		System.out.println("acctype : " + acctype);
//		System.out.println("owner : " + owner);
//		if(!(source.equals(""))) {
//			LeadMaster lm=new LeadMaster();
//			lm.setLeadsource(source);
//			leadmasterservice.save(lm);
//		}
//		
//		if(!(Leadstatus.equals(""))) {
//			Status ls=new Status();
//			ls.setLeadstatus(Leadstatus);
//			statusservice.save(ls);
//		}
//		
//		if(!(ind.equals(""))) {
//			IndustryMaster i=new IndustryMaster();
//			i.setIndustry(ind);
//			indservice.save(i);
//		}
//		
//		if(!(emp.equals(""))) {
//			Employee e=new Employee();
//			e.setEmp(emp);
//			empserivce.save(e);
//		}	
//		
//		if(!(acctype.equals(""))) {
//			AccountType acc=new AccountType();
//			acc.setAccount_type(acctype);
//			accserivce.save(acc);
//			
//		}
//		
//		if(!(owner.equals(""))) {
//			OwnerShip own=new OwnerShip();
//			own.setOwnership(owner);
//			ownserivce.save(own);
//		}
//		return "redirect:/lead/masteradmin";
//	}	
	
//	@RequestMapping("/lead/master")
//	public String LeadMaster() {
//		return "Lead/LeadMaster";
//	}    ............................................................
	
//	lead owner
	
	@GetMapping("/lead/owner")
	public String Leadowner(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		 model.addAttribute("EmployeeDeatilsList", employeeserivce.listAll());	
		return "Lead/LeadMaster";
	}
	
	@PostMapping("/leadowner/save")
	public String LeadownerSave(@RequestParam("leadowner") String lowner) {
		EmployeeDetails ed=new EmployeeDetails();
		ed.setFirstname(lowner);
		employeeserivce.save(ed);
		return "redirect:/lead/owner";
	}	
	
	
//	lead Sourece ..................................
	
	@PostMapping("/leadmaster/save")
	public String LeadmasterSave(@RequestParam("Leadsource") String source) {
		LeadMaster lm=new LeadMaster();
		lm.setLeadsource(source);
		leadmasterservice.save(lm);
		return "redirect:/lead/master";
	}	
	@GetMapping("/lead/master")
	public String Leadsource(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		return "Lead/LeadMaster";
	}
	
// Lead status.........................................
	
	@PostMapping("/leadstatus/save")
	public String LeadstatusSave(@RequestParam("Leadstatus") String status) {
		Status ls = new Status();
		ls.setLeadstatus(status);
		statusservice.save(ls);
		return "redirect:/lead/status";
	}	
	@GetMapping("/lead/status")
	public String Leadstatus(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("StatusList", statusservice.listAll());
		return "Lead/Status";
	}
	
//	industry ..........................................
	
	@PostMapping("/leadindustry/save")
	public String LeadIndSave(@RequestParam("industry") String indust) {
		IndustryMaster i=new IndustryMaster();
		i.setIndustry(indust);
		indservice.save(i);
		return "redirect:/lead/industry";
	}
	
	@GetMapping("/lead/industry")
	public String LeadInd(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("IndustryMasterList", indservice.listAll());
		return "Lead/Industry";
	}
	
//	no of Employee ......................................
	
	@PostMapping("/leademployee/save")
	public String LeadEmpSave(@RequestParam("Employee") String emplo) {
		Employee e=new Employee();
		e.setEmp(emplo);
		empserivce.save(e);
		return "redirect:/lead/employee";
	}
	
	@GetMapping("/lead/employee")
	public String LeadEmp(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("EmployeeList", empserivce.listAll());
		return "Lead/NoofEmployee";
	}	
	
//	Account Type ..............................................
	
	@GetMapping("/accountType")
	public String Account(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("AccountTypeList", accserivce.listAll());
		return  "Lead/AccountType";
	}
	
	@PostMapping("/accountType/save")
	public String Accountsave(@RequestParam("accounttype") String account) {
		AccountType a= new AccountType();
		a.setAccount_type(account);
		accserivce.save(a);
		return "redirect:/accountType";
	}
	
//	 OwnerShip ................................................
	@GetMapping("/ownership")
	public String Owner(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("OwnerList", ownserivce.listAll());
		return  "Lead/ownership";
	}
	
	@PostMapping("/ownership/save")
	public String Ownersave(@RequestParam("owner") String own) {
		OwnerShip o= new OwnerShip();
		o.setOwnership(own);
		ownserivce.save(o);
		return "redirect:/ownership";
	}
	
	
//	 Stage ................................................
	@GetMapping("/stage")
	public String Stage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("StageList", stageserivce.listAll());
		return  "Masters/Stage";
	}
	
	@PostMapping("/stage/save")
	public String Stagesave(@RequestParam("stage") String stage) {
		Stage s= new Stage();
		s.setStage(stage);
		stageserivce.save(s);
		return "redirect:/stage";
	}
	
//	stage status
	@GetMapping("/stagestatus")
	public String Stagestatus(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("StagestatusList", stagestatusservice.listAll());
		return "Masters/Stagesstatus";
	}
	
	@PostMapping("/stagestatus/save")
	public String Stagestatussave(@RequestParam("stagestatus") String stagestatus, HttpServletRequest request) {
		 StageStatus s= new StageStatus();
         s.setStagestatus(stagestatus);
//         s.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
		 stagestatusservice.save(s);
		 return "redirect:/stagestatus";
	}
	
}