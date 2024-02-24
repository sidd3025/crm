package com.crm.portal.Lead;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="lead_details")
public class Lead {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")

	private Long id;
	private Long employee_id;
	private Integer product_id;
	private String fax;
	private String leadsource;
	private String leadstatus;
	private String industry;
	private String noofemployee;
	private String annualrevenue;
	private Integer contactid;
    private String probablility;;
	private String leadowner;
	private String currentdate;
	private String accountname;
	private String firstname;
	private String lastname;
	private String email;
	private String title;
	private String phone;
	private String phone2;
	private String mobile1;
	private String mobile2;
	private String skypeid;
	private String secoundaryemail;
	private String twitter;
	private String leadname;
	private String readingmaterial;
	private String approval;
	private String remarks;
	private String stagestatus;
	private String amount;
	private String campign;
	
	
	public String getCampign() {
		return campign;
	}
	public void setCampign(String campign) {
		this.campign = campign;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getStagestatus() {
		return stagestatus;
	}
	public void setStagestatus(String stagestatus) {
		this.stagestatus = stagestatus;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getReadingmaterial() {
		return readingmaterial;
	}
	public void setReadingmaterial(String readingmaterial) {
		this.readingmaterial = readingmaterial;
	}
	public String getLeadname() {
		return leadname;
	}
	public void setLeadname(String leadname) {
		this.leadname = leadname;
	}
	public String getProbablility() {
		return probablility;
	}
	public void setProbablility(String probablility) {
		this.probablility = probablility;
	}
	
	public String getLeadowner() {
		return leadowner;
	}
	public void setLeadowner(String leadowner) {
		this.leadowner = leadowner;
	}
	
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLeadstatus() {
		return leadstatus;
	}
	public void setLeadstatus(String leadstatus) {
		this.leadstatus = leadstatus;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getNoofemployee() {
		return noofemployee;
	}
	public void setNoofemployee(String noofemployee) {
		this.noofemployee = noofemployee;
	}
//	public int getAnnualrevenue() {
//		return annualrevenue;
//	}
//	public void setAnnualrevenue(int annualrevenue) {
//		this.annualrevenue = annualrevenue;
//	}
	
	public String getAnnualrevenue() {
		return annualrevenue;
	}
	public void setAnnualrevenue(String annualrevenue) {
		this.annualrevenue = annualrevenue;
	}
	
	public Integer getContactid() {
		return contactid;
	}
		public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}
	public String getLeadsource() {
		return leadsource;
	}
	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSkypeid() {
		return skypeid;
	}
	public void setSkypeid(String skypeid) {
		this.skypeid = skypeid;
	}
	public String getSecoundaryemail() {
		return secoundaryemail;
	}
	public void setSecoundaryemail(String secoundaryemail) {
		this.secoundaryemail = secoundaryemail;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
}
