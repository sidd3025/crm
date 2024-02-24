package com.crm.portal.Contacts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="remarks_status")
public class RemarksModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	private Long employee_id;
	private String contactstatus;
	private String contactowner;
	private String description;
	private String currentdate_time;
	private String contact_id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactstatus() {
		return contactstatus;
	}
	public void setContactstatus(String contactstatus) {
		this.contactstatus = contactstatus;
	}
	public String getCurrentdate_time() {
		return currentdate_time;
	}
	public void setCurrentdate_time(String currentdate_time) {
		this.currentdate_time = currentdate_time;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getContactowner() {
		return contactowner;
	}
	public void setContactowner(String contactowner) {
		this.contactowner = contactowner;
	}
	
	

}
