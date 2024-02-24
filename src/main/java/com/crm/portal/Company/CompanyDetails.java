package com.crm.portal.Company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="company_details")
public class CompanyDetails {

	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
//	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
//	@GenericGenerator(name = "native",strategy = "native")
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Integer id;
	private String company_name;
	private Integer total_employee;
	private String firstname;
	private String lastname;
	@Column(unique=true,length=50)
	private String email;
	private String PresentAddress1;
	private String PresentAddress2;
	private String MobileNumber1;
	private String MobileNumber2;
	private String city;
	private String state;
	private String country;
	private String pin_code;
	private String mcode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Integer getTotal_employee() {
		return total_employee;
	}
	public void setTotal_employee(Integer total_employee) {
		this.total_employee = total_employee;
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
	
	public String getPresentAddress1() {
		return PresentAddress1;
	}
	public void setPresentAddress1(String presentAddress1) {
		PresentAddress1 = presentAddress1;
	}
	public String getPresentAddress2() {
		return PresentAddress2;
	}
	public void setPresentAddress2(String presentAddress2) {
		PresentAddress2 = presentAddress2;
	}
	public String getMobileNumber1() {
		return MobileNumber1;
	}
	public void setMobileNumber1(String mobileNumber1) {
		MobileNumber1 = mobileNumber1;
	}
	public String getMobileNumber2() {
		return MobileNumber2;
	}
	public void setMobileNumber2(String mobileNumber2) {
		MobileNumber2 = mobileNumber2;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPin_code() {
		return pin_code;
	}
	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
}