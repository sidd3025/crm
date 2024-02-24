package com.crm.portal.Deals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="budget_sale")
public class SaleBudget {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)

	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private int employee_id;
	private String monthly;
	private String yearly;
	private String estimateamount;
	private String targetamount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getMonthly() {
		return monthly;
	}
	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}
	public String getYearly() {
		return yearly;
	}
	public void setYearly(String yearly) {
		this.yearly = yearly;
	}
	public String getEstimateamount() {
		return estimateamount;
	}
	public void setEstimateamount(String estimateamount) {
		this.estimateamount = estimateamount;
	}
	public String getTargetamount() {
		return targetamount;
	}
	public void setTargetamount(String targetamount) {
		this.targetamount = targetamount;
	}	
}
