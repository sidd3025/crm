package com.crm.portal.Report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Reportlead_details")
public class ReportLead_Details {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long report_id;
	private Long lead_id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReport_id() {
		return report_id;
	}
	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}
	public Long getLead_id() {
		return lead_id;
	}
	public void setLead_id(Long lead_id) {
		this.lead_id = lead_id;
	}
	
}
