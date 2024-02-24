package com.crm.portal.Lead;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="leadStatus_master")
public class Status {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private String leadstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {  
		this.id = id;
	}
	public String getLeadstatus() {
		return leadstatus;
	}
	public void setLeadstatus(String leadstatus) {
		this.leadstatus = leadstatus;
	}

}
