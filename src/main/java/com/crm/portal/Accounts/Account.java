package com.crm.portal.Accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="account_details")
public class Account {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private Long employee_id;
	private String accoutowner;
	private String accountname;
	private String phone;
	private String mobile;
	private String phone2;
	private String mobile2;
	private String Accountsite;
	private String accountgroup;
	private String fax;
	private String Parentaccount;
	private String website;
	private String accountnumber;
	private String tickersymbol;
	private String accounttype;
	private String ownership;
	private String industry;
	private String employee;
	private String AnnualRevenue;
	private String sic_code;
	private String addresslin1;
	private String addresslin2;
	private String country;
	private String city;
	private String state;
	private String pincode;
	private String currentdate;
	private String duns;
	private String email;
	private String Accountstatus;
	private String description;
	private String remarks;
	private String geography;
	private String software_email;
	private String hardware_email;
	private String software_ERP;
	private String software_CRM;
	private String software_HRMS;
	private String software_manufacture;
	private String software_Accandficc;
	private String software_inverntory;
	private String software_operation;
	private String software_payroll;
	private String hardware_Accandficc;
	private String hardware_crm;
	private String hardware_hrms;
	private String hardware_manufacture;
	private String hardware_inverntory;
	private String hardware_operation;
	private String hardware_erp;
	private String hardware_payroll;
	private String email2;
	
	
	
	
	
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getSoftware_email() {
		return software_email;
	}
	public void setSoftware_email(String software_email) {
		this.software_email = software_email;
	}
	public String getHardware_email() {
		return hardware_email;
	}
	public void setHardware_email(String hardware_email) {
		this.hardware_email = hardware_email;
	}
	public String getSoftware_ERP() {
		return software_ERP;
	}
	public void setSoftware_ERP(String software_ERP) {
		this.software_ERP = software_ERP;
	}
	public String getSoftware_CRM() {
		return software_CRM;
	}
	public void setSoftware_CRM(String software_CRM) {
		this.software_CRM = software_CRM;
	}
	public String getSoftware_HRMS() {
		return software_HRMS;
	}
	public void setSoftware_HRMS(String software_HRMS) {
		this.software_HRMS = software_HRMS;
	}
	public String getSoftware_manufacture() {
		return software_manufacture;
	}
	public void setSoftware_manufacture(String software_manufacture) {
		this.software_manufacture = software_manufacture;
	}
	public String getSoftware_Accandficc() {
		return software_Accandficc;
	}
	public void setSoftware_Accandficc(String software_Accandficc) {
		this.software_Accandficc = software_Accandficc;
	}
	public String getSoftware_inverntory() {
		return software_inverntory;
	}
	public void setSoftware_inverntory(String software_inverntory) {
		this.software_inverntory = software_inverntory;
	}
	public String getSoftware_operation() {
		return software_operation;
	}
	public void setSoftware_operation(String software_operation) {
		this.software_operation = software_operation;
	}
	public String getSoftware_payroll() {
		return software_payroll;
	}
	public void setSoftware_payroll(String software_payroll) {
		this.software_payroll = software_payroll;
	}
	public String getHardware_Accandficc() {
		return hardware_Accandficc;
	}
	public void setHardware_Accandficc(String hardware_Accandficc) {
		this.hardware_Accandficc = hardware_Accandficc;
	}
	public String getHardware_crm() {
		return hardware_crm;
	}
	public void setHardware_crm(String hardware_crm) {
		this.hardware_crm = hardware_crm;
	}
	public String getHardware_hrms() {
		return hardware_hrms;
	}
	public void setHardware_hrms(String hardware_hrms) {
		this.hardware_hrms = hardware_hrms;
	}
	public String getHardware_manufacture() {
		return hardware_manufacture;
	}
	public void setHardware_manufacture(String hardware_manufacture) {
		this.hardware_manufacture = hardware_manufacture;
	}
	public String getHardware_inverntory() {
		return hardware_inverntory;
	}
	public void setHardware_inverntory(String hardware_inverntory) {
		this.hardware_inverntory = hardware_inverntory;
	}
	public String getHardware_operation() {
		return hardware_operation;
	}
	public void setHardware_operation(String hardware_operation) {
		this.hardware_operation = hardware_operation;
	}
	public String getHardware_erp() {
		return hardware_erp;
	}
	public void setHardware_erp(String hardware_erp) {
		this.hardware_erp = hardware_erp;
	}
	public String getHardware_payroll() {
		return hardware_payroll;
	}
	public void setHardware_payroll(String hardware_payroll) {
		this.hardware_payroll = hardware_payroll;
	}
	public String getGeography() {
		return geography;
	}
	public void setGeography(String geography) {
		this.geography = geography;
	}
	public String getAccountstatus() {
		return Accountstatus;
	}
	public void setAccountstatus(String accountstatus) {
		Accountstatus = accountstatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDuns() {
		return duns;
	}
	public void setDuns(String duns) {
		this.duns = duns;
	}
	
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
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
	
	public String getAccountgroup() {
		return accountgroup;
	}
	public void setAccountgroup(String accountgroup) {
		this.accountgroup = accountgroup;
	}
	
	public String getAccoutowner() {
		return accoutowner;
	}
	public void setAccoutowner(String accoutowner) {
		this.accoutowner = accoutowner;
	}
	
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccountsite() {
		return Accountsite;
	}
	public void setAccountsite(String accountsite) {
		Accountsite = accountsite;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getParentaccount() {
		return Parentaccount;
	}
	public void setParentaccount(String parentaccount) {
		Parentaccount = parentaccount;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getTickersymbol() {
		return tickersymbol;
	}
	public void setTickersymbol(String tickersymbol) {
		this.tickersymbol = tickersymbol;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getAnnualRevenue() {
		return AnnualRevenue;
	}
	public void setAnnualRevenue(String annualRevenue) {
		AnnualRevenue = annualRevenue;
	}
	public String getSic_code() {
		return sic_code;
	}
	public void setSic_code(String sic_code) {
		this.sic_code = sic_code;
	}
	public String getAddresslin1() {
		return addresslin1;
	}
	public void setAddresslin1(String addresslin1) {
		this.addresslin1 = addresslin1;
	}
	public String getAddresslin2() {
		return addresslin2;
	}
	public void setAddresslin2(String addresslin2) {
		this.addresslin2 = addresslin2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
