package com.crm.portal.Campagin;

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

import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Lead.StageStatus;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;

@Controller
public class CampaginController {

	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private CampaginService campservice;
	
	@GetMapping("/campagin")
	public String Stagestatus(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("CampaginList", campservice.listAll());
		return "Masters/Campagin";
	}
	
	@PostMapping("/campagin/save")
	public String Stagestatussave(@RequestParam("campagin") String campagin, HttpServletRequest request) {
		 Campagin c= new Campagin();
		 
		 c.setCampagin(campagin);
          campservice.save(c);
         
		 return "redirect:/campagin";
	}
	
	
}
