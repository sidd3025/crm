package com.crm.portal.Contacts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="contact_details")
public class Contact {

	
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	private Long employee_id;
//	private String Date;
	private String contactowner;
	private String description;
	private String accountname;
	private String first_name;
	private String last_name;
	private String email;
	private String title;
	private String department;
	private String phone_number;
	private String executive_level;
	private String mobile_number; 
    private String contactstatus;
	private String contactsource;
    private String linkedin_bio;
	private String industry;
	private String skypeid;
	private String secondaryemail;
	private String twitter;
	private String addresslin1;
	private String addresslin2;
	private String country;
	private String city;
	private String state;
	private String pincode;
	private String currentdate_time;
	private String mobile_number2;
	private String phone_number2;
	
//	private String email_status;
//	private String reason;
//	private String status;
//	private String description;
//	private String opt_out;
//	private String executive_level;
//	private String lifecycle_stage;
//	private String contact_type;
//	private String score;
//	private String linkedin_bio;
//	private String facebook_bio;
//	private String dob;
//	private String Assitant;
//	private String fax;
	
	
	
	public Long getId() {
		return id;
	}
	public String getMobile_number2() {
		return mobile_number2;
	}
	public void setMobile_number2(String mobile_number2) {
		this.mobile_number2 = mobile_number2;
	}
	public String getPhone_number2() {
		return phone_number2;
	}
	public void setPhone_number2(String phone_number2) {
		this.phone_number2 = phone_number2;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContactowner() {
		return contactowner;
	}
	public void setContactowner(String contactowner) {
		this.contactowner = contactowner;
	}
//	public String getContact_owner() {
//		return contact_owner;
//	}
//	public void setContact_owner(String contact_owner) {
//		this.contact_owner = contact_owner;
//	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getExecutive_level() {
		return executive_level;
	}
	public void setExecutive_level(String executive_level) {
		this.executive_level = executive_level;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getContactsource() {
		return contactsource;
	}
	public void setContactsource(String contactsource) {
		this.contactsource = contactsource;
	}
	public String getLinkedin_bio() {
		return linkedin_bio;
	}
	public void setLinkedin_bio(String linkedin_bio) {
		this.linkedin_bio = linkedin_bio;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getSkypeid() {
		return skypeid;
	}
	public void setSkypeid(String skypeid) {
		this.skypeid = skypeid;
	}
	public String getSecondaryemail() {
		return secondaryemail;
	}
	public void setSecondaryemail(String secondaryemail) {
		this.secondaryemail = secondaryemail;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
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
	public String getCurrentdate_time() {
		return currentdate_time;
	}
	public void setCurrentdate_time(String currentdate_time) {
		this.currentdate_time = currentdate_time;
	}
	public String getContactstatus() {
		return contactstatus;
	}
	public void setContactstatus(String contactstatus) {
		this.contactstatus = contactstatus;
	}




}
