package com.crm.portal.Lead;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="remarks_lead")
public class RemarksLeadModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	private Long employee_id;
	private String leadstatus;
	private String leadowner;
	private String description;
	private String currentdate_time;
	private String lead_id;
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
	public String getLeadstatus() {
		return leadstatus;
	}
	public void setLeadstatus(String leadstatus) {
		this.leadstatus = leadstatus;
	}
	public String getLeadowner() {
		return leadowner;
	}
	public void setLeadowner(String leadowner) {
		this.leadowner = leadowner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrentdate_time() {
		return currentdate_time;
	}
	public void setCurrentdate_time(String currentdate_time) {
		this.currentdate_time = currentdate_time;
	}
	public String getLead_id() {
		return lead_id;
	}
	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}
}
