package com.crm.portal.Contacts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="contactStatus_master")
public class ContactStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private String contactstatus;
	private Long compnayid;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContactstatus() {
		return contactstatus;
	}
	public void setContactstatus(String contactstatus) {
		this.contactstatus = contactstatus;
	}
	public Long getCompnayid() {
		return compnayid;
	}
	public void setCompnayid(Long compnayid) {
		this.compnayid = compnayid;
	}
	

	
	
	
}
