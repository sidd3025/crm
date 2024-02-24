package com.crm.portal.Deals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.crm.portal.Contacts.Contact;
import com.crm.portal.Contacts.ContactService;
import com.crm.portal.Contacts.RemarksModel;
import com.crm.portal.Contacts.RemarksService;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Invoice.ProductsServices;
import com.crm.portal.Lead.EmpService;
import com.crm.portal.Lead.Lead;
import com.crm.portal.Lead.LeadMasterService;
import com.crm.portal.Lead.LeadProductService;
import com.crm.portal.Lead.LeadService;
import com.crm.portal.Lead.OwnerService;
import com.crm.portal.Lead.StageService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;
import com.crm.portal.Register.ProductResource;

@Controller
public class DealController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LeadService LeadService;
	@Autowired
	private DealService dealService;
	@Autowired
	private LeadMasterService leadmasterservice;
	@Autowired
	private ContactService contactservice;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private AccountService accService;
	@Autowired
	private StageService stageserivce;
	@Autowired
	private RemarksService remarkservice;
	@Autowired
	private SaleBudgetService saleservice;
	@Autowired
	private LeadProductService leadproductmapservice;
	@Autowired
	private ProductsServices productservice;
	
	@GetMapping("/deal/add")
	public String Deal(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("DealList", dealService.listAll());
		model.addAttribute("AccountList", accService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("ContactList", contactservice.listAll());
		 model.addAttribute("StageList", stageserivce.listAll());
		model.addAttribute("Deal", new Deal());
		
//		model.addAttribute("OwnerList", ownserivce.listAll());
	      // System.out.println("user"+request.getUserPrincipal().getName());
		
	       EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		  // System.out.println("user1"+employee.getCompanyid());
		
		   model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
   	
     model.addAttribute("employee", employee);
	
		return "Deals/DealAdd";
	}
	
	
	
	@PostMapping("/deal/save")
	public String DealSave(@ModelAttribute("deal") Deal deal,@RequestParam(name = "lead_id", required = false) String lead_id,@RequestParam("dealowner") String dealowner,
			@RequestParam("amount") String amount,@RequestParam("dealname") String dealname,
			@RequestParam("AccountName") String account,
			@RequestParam("stage") String stage,@RequestParam("type") String type,@RequestParam("probability") String probability,
			@RequestParam("LeadSource") String LeadSource,
            @RequestParam("campaign") String campaign,@RequestParam("closingdate") String cdate,@RequestParam("discountamount") String discountamount,
            @RequestParam("discountPercentage") String discount,@RequestParam("contactperson") String contactperson,@RequestParam("product") String product,
            @RequestParam("readingmaterail") MultipartFile[] readingmaterail) throws IOException{
		
//	  current date
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String Current_Date = f.format(date);
		
		for (MultipartFile file : readingmaterail) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
	       deal.setReadingmaterial(fileName);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	        deal.setReadingmaterial(fileName);
////	    	product.setReadingmaterials2(fileName2);
//	        System.out.println("Start : " );
//	    	
////	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
//	        ProductResource p= new ProductResource();
//	        p.setReadingmaterials1(deal.getReadingmaterial());
////	        p.setFiledescription1(product.getFiledescription1());
//	        p.setType1("pdf");
//	        p.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
//	        productservice.save(p);
//	    	System.out.println("End : " );
			}
		}
		
		
//		String currentdate ="";
//		EmployeeDetails l1= employeeserivce.get(Long.parseLong(dealowner));
		 EmployeeDetails l1= employeeserivce.get(Long.parseLong(dealowner));
		 Lead l = LeadService.get(Long.parseLong(lead_id));
				Deal d = new Deal();
//				d.setDealowner(dealowner);
				d.setDealowner(l1.getFirstname()+" "+l1.getLastname());
				d.setEmployee_id(l1.getId());
				d.setAccountname(account);
				d.setDealname(dealname);
				d.setAmmount(amount);
				d.setType(type);
				d.setProbablility(probability);
				d.setLeadsource(LeadSource);
				d.setStage(stage);
				d.setCampaignsource(campaign);
//				d.setContactname(contact);
//				d.setDealowner(dealname);
				d.setContact_id(l.getContactid());
	            d.setClosingdate(cdate);
	            d.setCurrentdate(Current_Date);
	            d.setLeadid(Integer.parseInt(lead_id));
	            d.setReadingmaterial(deal.getReadingmaterial());
	            d.setDiscount(discount);	
//	            d.setApproval(approval);
	            d.setDiscountamount(discountamount);
	            d.setContactperson(contactperson);
	            d.setProduct(product);
	            dealService.save(d);
		return "redirect:/deallist";
	}
	
	@GetMapping("/deallist")
	public String Leadlistr(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		model.addAttribute("DealList", dealService.listAll());
		model.addAttribute("LeadList", LeadService.listAll());

//		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		 model.addAttribute("EmployeeDeatilsList", employeeserivce.GetIDByEmail(employee.getId()));
		
//		///// authority check 
		if(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())) == 4) {
//		/// seraching employee details of login person/////////////
			EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		List <Deal> deal = new LinkedList<>();
	    
//		//// find the emp under employee menager.....
		for(EmployeeDetails emp: employeeserivce.FindNameByempID(employee.getId().intValue())) {
//			//// showing employee manager page 2 level
			deal.addAll(dealService.GetIDById(emp.getId()));
	    }
//   //// showing self details of employee......
		deal.addAll(dealService.GetIDById(employee.getId()));
		model.addAttribute("DealList", deal);
		}
		else {
//			list all for admin.......
			EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
			List <Deal> deal = new LinkedList<>();
//			////// it will find according to company id to show details for amdin.....
		    for(EmployeeDetails emp: employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid())) {
//		    	it will show all employee under the company
		    	deal.addAll(dealService.GetIDById(emp.getId()));
		    }
//		    Self details of admin
		    deal.addAll(dealService.GetIDById(employee.getId()));
			model.addAttribute("DealList", deal);
		}
		return "Deals/DealViewList";
	}
	
	
	@GetMapping("/EditAllDeal/{id}")
	public String EditAllCourses(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	Deal deal = dealService.get(id);
    	model.addAttribute("deal", deal);
		/*
		 * model.addAttribute("LeadList", LeadService.listAll());
		 * model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		 */
		model.addAttribute("DealList", dealService.listAll());
		model.addAttribute("AccountList", accService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
		model.addAttribute("ContactList", contactservice.listAll());
		 model.addAttribute("StageList", stageserivce.listAll());
//		model.addAttribute("StatusList", statusservice.listAll());
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	model.addAttribute("employee", employee);
		return "Deals/DealEdit";
	}
	
	
	@GetMapping("/ViewAllDeal/{id}")
	public String ViewAllCourses(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
//    	"cont" is variable where all the data is stored of contact table.....
//    	and before showing contact table details we should call "id" contact...
    	Deal deal = dealService.get(id);
    	model.addAttribute("deal", deal);
    	
//    	RemarksModel remarks = remarkservice.get(id);
//    	model.addAttribute("remark",remarks);
    	
//    	it is for filtering the contact_id to show the remarks status of particular id.....
    	
    	List<RemarksModel> r = remarkservice.getFilterByContactID(Integer.toString(id));
    	model.addAttribute("remark",r);
//    	System.out.println(contactService.get(id).getFirst_name());
    	model.addAttribute("RemarkList", remarkservice.listAll());
    	model.addAttribute("ContactList", contactservice.listAll());
    	model.addAttribute("LeadList", LeadService.listAll());
    	model.addAttribute("DealList", dealService.listAll());
    	
//		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
//		model.addAttribute("AccountList", accountserivce.listAll());
//		model.addAttribute("StatusList", statusservice.listAll());
		
        EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
		
      	model.addAttribute("employee", employee);
		return "Deals/DealView";
	}
	
	@PostMapping("/deal/doc/save1")
	public String leaddocSave(@ModelAttribute("dealList") Deal deal, @RequestParam("approval")String approval) {
//		  current date 
		    LocalDateTime mydate = LocalDateTime.now();
		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = new Date();
//			String Current_Date = f.format(date);
//		Contact c
		    Deal dl = dealService.get(deal.getId());
		  
		    dl.setCurrentdate(mydate.format(myformatter)); 
		    dl.setApproval(approval);
//		    ld.setLeadstatus(leadstatus);
		    
//	    c.setCurrentdate(Current_Date);
		    dealService.save(dl);
		return "redirect:/deallist";
	}
	
	
	
	@GetMapping("/reportlistdealadmin")
	public String LeadReportlist(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("DealList", dealService.listAll());
		model.addAttribute("LeadList", LeadService.listAll());
		return "Report/DealReport";
	}
	
                          
//   for employee
	@GetMapping("/reportlistdeal")
	public String LeadReportlistadmin(Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("DealList", dealService.listAll());
		
		 EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		 model.addAttribute("AccountList", accService.GetIDById(employee.getId()));
//		 model.addAttribute("ownername",employee.getFirstname());
		return "Deals/DealReportEmp";
	}
	
	@GetMapping("/createinvoice/{id}")
	public String contactconvertlead(@PathVariable(value="id")int id,Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
    	model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
    	
    	Deal deal = dealService.get(id);
    	model.addAttribute("deal", deal);
    	
    	model.addAttribute("customername", deal.getContactperson());
//    	model.addAttribute("productname", deal.getProduct());
    	model.addAttribute("finalAmount", deal.getDiscountamount());
    	model.addAttribute("accountname", deal.getAccountname()); 
    	model.addAttribute("discount", deal.getDiscount()); 
    	model.addAttribute("rate", deal.getAmmount());

    	model.addAttribute("id", id);
    	
//    	System.out.println(contactService.get(id).getFirst_name());
    	model.addAttribute("productList", productservice.listAll());
		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("AccountList", accService.listAll());
		model.addAttribute("LeadMasterList", leadmasterservice.listAll());
	    model.addAttribute("ProductMaplist", leadproductmapservice.listAll()); 

		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	model.addAttribute("employee", employee);
		return "Invoice/AddInvoice";
	}
	
//////////////////////////////////////////////////////////////////	
	@GetMapping("/salesbudget")
	public String sales(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		  
		return "SaleBudget";
	}
	
	
	@GetMapping("/crm-portal/home/chart/charts")
	public @ResponseBody List<Integer> HomeGraphCharts() {
		List<Integer> i = new LinkedList<>();
//		JAN
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly1(3, "JAN", "2024")));
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly(3, "JAN", "2024")));
//		FEB
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly1(3, "FEB", "2024")));
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly(3, "FEB", "2024")));
//		MAR
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly1(3, "MAR", "2024")));
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly(3, "MAR", "2024")));
//		APR
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly1(3, "APR", "2024")));
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly(3, "APR", "2024")));
//		MAy
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly1(3, "MAY", "2024")));
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly(3, "MAY", "2024")));
//		JUN
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly1(3, "JUN", "2024")));
		i.add(Integer.parseInt(saleservice.getcountsalebudgetmotlyyearly(3, "JUN", "2024")));
		return i;
	}
}
