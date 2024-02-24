package com.crm.portal.Login;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.crm.portal.Company.CompanyDetails;
import com.crm.portal.Company.CompanyService;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Lead.Lead;

@Controller
public class LoginUserController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService empservice;
	@Autowired
	private CompanyService comservice;
	@Autowired
	private EmailForgetPasswordService emailforgetpasswordservice;

//	 Registeration page controller
	
//	  @GetMapping("/regist") 
//	  public String LeadAdd(Model model) {
//	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	  List<Mainsidebarauthority> listmainsidebarauthority =
//	  mainsidebarauthorityService.Mainsidebarauthoritybyauthid(
//	  loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//	  model.addAttribute("listMainsidebarAuthority",listmainsidebarauthority);
//	  model.addAttribute("listEmployee",empservice.listAll());
////	  model.addAttribute("EmployeeDetails",new EmployeeDetails());
//	  return "registration"; 
//	  }
//	  
//	  @PostMapping("/register/save")
//	  public String LeadSave(@RequestParam("firstname") String Firstname,@RequestParam("lastname") String Lastname,
//			  @RequestParam("email") String Email,@RequestParam("number") String Phone,@RequestParam("companyname") String companyname,@RequestParam("mcode") String mcode,
//			  @RequestParam("noofemployee") String noofemployee ){
//		  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//		  
//		  LoginUser l = new LoginUser();
//		  l.setUsername(Email);
//		  l.setPassword(bCryptPasswordEncoder.encode("123456"));
//		  loginuserService.save(l);
//		  
//		  LoginUserAuthority la = new LoginUserAuthority();
//		  la.setAuthority_id(3);
//		  la.setUser_id(loginuserService.GetAuthorityID(Email));
//		  loginuserauthorityService.save(la);
//		  
// 		  CompanyDetails com=new CompanyDetails();
//		  com.setCompany_name(companyname);
//		  comservice.save(com);
//		  
//		  EmployeeDetails e = new EmployeeDetails();
//		  e.setFirstname(Firstname);
//		  e.setLastname(Lastname);
//		  e.setEmail(Email);
//		  e.setNumber(Phone);
//		  e.setMcode(mcode);
//		  e.setCompanyid(comservice.GetIDBycompany_name(companyname));
//		  e.setAuthorityid(3);
//		  empservice.save(e);
//		  
//		  CompanyDetails c=comservice.get(comservice.GetIDBycompany_name(companyname));
////		  c.setEmployee_id(empservice.GetIDByEmail(Email));
//		  comservice.save(c);
//		  return "redirect:/login";
//		
//		}
	  
	
	 

	@RequestMapping({ "/", "/login" })
	public String LoginPage(Model model) {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//		System.out.println("Manager : " + bCryptPasswordEncoder.encode("123456"));
//		System.out.println("Mail" + bCryptPasswordEncoder.encode("123456"));
		return "login";
	}

	@GetMapping("/checkusername")
	public @ResponseBody String CheckUsername(@RequestParam(name = "name") String name) {
		if (loginuserService.findByUser(name) == 0) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping("/home")
	public String MainPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int n = loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName()));
		if (n == 1 || n == 2 || n == 3) {
			return "redirect:/AdminHome";
		} else {
			return "redirect:/UserHome";
		}
	}

	@RequestMapping("/AdminHome")
	public String home(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(
				loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		int n = loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName()));
		String status = "false";
		if (n == 1 || n == 2 || n == 3) {
			status = "true";
		}
		model.addAttribute("status", status);
		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("UserName", request.getUserPrincipal().getName());
//		model.addAttribute("CompanyID",employeeService.getCompanyID(auth.getName()));
		return "home";
	}
	
	
// original code
	@RequestMapping("/UserHome")
	public String home1(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(
				loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		int n = loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName()));
		String status = "false";
		if (n == 4) {
			status = "true";
		}
		model.addAttribute("status", status);
		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("loginuser", loginuserService.getUserName(request.getUserPrincipal().getName()));
//    	model.addAttribute("CompanyID",employeeService.getCompanyID(auth.getName()));
		return "home1";
	}
	

//	@RequestMapping("/UserHome")
//	public String home1(Model model, HttpServletRequest request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(
//				loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		int n = loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName()));
//		String status = "false";
//		if (n == 4) {
//			status = "true";
//		}
//		model.addAttribute("status", status);
//		model.addAttribute("username", request.getUserPrincipal().getName());
//		model.addAttribute("loginuser", loginuserService.getUserName(request.getUserPrincipal().getName()));
////    	model.addAttribute("CompanyID",employeeService.getCompanyID(auth.getName()));
//		return "dashboard";
//	}

	@Value("${spring.mail.username}")
	private String mail_username;

	@RequestMapping("/forgetpasswordpage")
	public String EmailForgetPasswordpage(Model model) {
		return "forgetpasswordpage";
	}

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public String Forgetpassword(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
		Random rand = new Random();
		int password = rand.nextInt(1000000);
		String error = "false";
		try {
			String text = "Hi " + username + ", Your One Time Password (OTP) is " + password
					+ " for Online HR Portal. Please do not share password with anyone.";
			Mail mail = new Mail();
			mail.setFrom(mail_username);
			mail.setMailTo(username);
			mail.setSubject("Forget Password");
			emailforgetpasswordservice.sendforgetpasswordEmail(mail, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addAttribute("password", password);
		redirectAttributes.addAttribute("error", error);
		return "redirect:/otp";
	}

	@RequestMapping("/otp")
	public String Otp(@RequestParam("password") String password, @RequestParam("error") String error, Model model) {
		model.addAttribute("password", password);
		model.addAttribute("error", error);
		return "otp";
	}

	@RequestMapping(value = "/checkotp", method = RequestMethod.POST)
	public String Checkotp(@RequestParam("password") String password, @RequestParam("otp") String otp,
			RedirectAttributes redirectAttributes) {
		if (password.equals(otp)) {
			return "changepassword";
		} else {
			String error = "true";
			redirectAttributes.addAttribute("password", password);
			redirectAttributes.addAttribute("error", error);
			return "redirect:/otp";
		}
	}

	@RequestMapping(value = "/savechangepassword", method = RequestMethod.POST)
	public String Savechangepassword(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("repassword") String repassword) {
		if (password.equals(repassword)) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
			loginuserService.UpdatePasswordByUsername(username, bCryptPasswordEncoder.encode(password));
			return "login";
		} else {
			return "changepassword";
		}
	}
}