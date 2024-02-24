 package com.crm.portal.Deals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="deal_details")
public class Deal {
     
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)

	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private String dealowner;
	private String dealname;
	private String Ammount;
	private String closingdate;
	private String accountname;
	private String stage;
	private String type;
	private String probablility;
	private String currentdate;
	private String leadsource;
	private String campaignsource;
	private Integer leadid;
	private Integer contact_id;
	private Long employee_id;
	private String month;
	private String year;
	private String readingmaterial;
	private String discount;
	private String discountamount;
	private String approval;
	private String contactperson;
	private String product;
	
	
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getContact_id() {
		return contact_id;
	}
	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getLeadid() {
		return leadid;
	}
	public void setLeadid(Integer leadid) {
		this.leadid = leadid;
	}
	public Long getId() {  
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDealowner() {
		return dealowner;
	}
	public void setDealowner(String dealowner) {
		this.dealowner = dealowner;
	}
	public String getDealname() {
		return dealname;
	}
	public void setDealname(String dealname) {
		this.dealname = dealname;
	}
	
	public String getAmmount() {
		return Ammount;
	}
	public void setAmmount(String ammount) {
		Ammount = ammount;
	}
	
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getClosingdate() {
		return closingdate;
	}
	public void setClosingdate(String closingdate) {
		this.closingdate = closingdate;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProbablility() {
		return probablility;
	}
	public void setProbablility(String probablility) {
		this.probablility = probablility;
	}
	public String getLeadsource() {
		return leadsource;
	}
	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}
	public String getCampaignsource() {
		return campaignsource;
	}
	public void setCampaignsource(String campaignsource) {
		this.campaignsource = campaignsource;
	}
	
	
	
}
