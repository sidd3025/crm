package com.crm.portal.Invoice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crm.portal.Accounts.AccountService;
import com.crm.portal.Accounts.Feedback;
import com.crm.portal.Deals.Deal;
import com.crm.portal.Deals.DealService;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Lead.LeadService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;
import com.crm.portal.Register.ProductService;



@Controller
public class InvoiceController {

	@Autowired
	public ProductsServices productService;
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
    private ProductsServices productserivce;
	@Autowired
	private DealService dealService;
	@Autowired
	private AccountService accService;
	@Autowired
	private LeadService LeadService;
	@Autowired
	private InvoiceService InvoiceService;

	
	@GetMapping("/item/add")
	public String Deal(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("productList", productserivce.listAll());
		model.addAttribute("DealList", dealService.listAll());
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
			  // System.out.println("user1"+employee.getCompanyid());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
	    model.addAttribute("employee", employee);
		model.addAttribute("ProductMaster", new ProductMaster());
	    model.addAttribute("UserName", employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	    return "Invoice/ProductMaster";
	}
	
	@PostMapping("/product/save")
	public String productSave(@ModelAttribute("product") ProductMaster product,@RequestParam("itemname") String itemname,
			@RequestParam("description") String description,@RequestParam("hsn") String hsn,
			@RequestParam("unitrate") String unitrate,
			@RequestParam("currency") String currency,@RequestParam("gst") String gst, HttpServletRequest request) throws IOException{
		
//	    current date
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String Current_Date = f.format(date);
		
	    ProductMaster pm = new ProductMaster();
		pm.setItem_name(itemname);
		pm.setItem_description(description);
		pm.setHsn(hsn);
		pm.setItem_rate(Integer.parseInt(unitrate));
		pm.setItem_GST(Integer.parseInt(gst));
		pm.setCurrency(currency);    
		pm.setEmployeeid(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
		productserivce.saveItems(pm);
		return"redirect:/item/add";
	}
	
	@GetMapping("/invoice")
	public String invoice(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("", productserivce.listAll());
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
			  // System.out.println("user1"+employee.getCompanyid());
		model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
	    model.addAttribute("employee", employee);
		model.addAttribute("ProductMaster", new ProductMaster());
	    model.addAttribute("UserName", employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	    return "Invoice/CreateInvoice";
	    
	}
	

	@GetMapping("/invoice/add")
	public String Invoice(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("DealList", dealService.listAll());
		model.addAttribute("AccountList", accService.listAll());
		model.addAttribute("LeadList", LeadService.listAll());
		model.addAttribute("InvoiceList", InvoiceService.listAll());
		model.addAttribute("Deal", new Deal());

	   EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
		
	   model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
   	
       model.addAttribute("employee", employee);
	
		return "Invoice/AddInvoice";
	}
	
}
