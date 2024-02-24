package com.crm.portal.Lancapemaster;

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

import com.crm.portal.Contacts.RemarksService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;

@Controller
public class MasterController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LandscapeEmailService emailservice;

	@GetMapping("/email/add")
	public String Deal(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("EmailList", emailservice.listAll());
		
		return "Masters/Landscapeemail";
	}
	

	@PostMapping("/landscpae/save")
	public String landscapeSave(@RequestParam("landsoftemail") String softemail) {
		
		LandscapeEmail e = new LandscapeEmail();
		e.setEmail(softemail);
		
		emailservice.save(e);
		return "redirect:/email/add";
	}
	
	}
