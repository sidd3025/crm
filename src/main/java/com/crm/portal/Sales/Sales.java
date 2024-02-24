package com.crm.portal.Sales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="sales_budget")
public class Sales {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	private Long company_id;
	private Long employee_id;
	private String from_date;
	private String to_date;
//	private String annualbudget;
	private double targetamount;
	private String q1fdate;
	private String q1tdate;
	private String q2fdate;
	private String q2tdate;
	private String q3fdate;
	private String q3tdate;
	private String q4fdate;
	private String q4tdate;
	private double q1percentage;
	private double q2percentage;
	private double q3percentage;
	private double q4percentage;
	private String Q1value;
	private String Q2value;
	private String Q3value;
	private String Q4value;

	
	
	public double getTargetamount() {
		return targetamount;
	}
	public void setTargetamount(double targetamount) {
		this.targetamount = targetamount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
//	public String getAnnualbudget() {
//		return annualbudget;
//	}
//	public void setAnnualbudget(String annualbudget) {
//		this.annualbudget = annualbudget;
//	}
	public String getQ1fdate() {
		return q1fdate;
	}
	public void setQ1fdate(String q1fdate) {
		this.q1fdate = q1fdate;
	}
	public String getQ1tdate() {
		return q1tdate;
	}
	public void setQ1tdate(String q1tdate) {
		this.q1tdate = q1tdate;
	}
	public String getQ2fdate() {
		return q2fdate;
	}
	public void setQ2fdate(String q2fdate) {
		this.q2fdate = q2fdate;
	}
	public String getQ2tdate() {
		return q2tdate;
	}
	public void setQ2tdate(String q2tdate) {
		this.q2tdate = q2tdate;
	}
	public String getQ3fdate() {
		return q3fdate;
	}
	public void setQ3fdate(String q3fdate) {
		this.q3fdate = q3fdate;
	}
	public String getQ3tdate() {
		return q3tdate;
	}
	public void setQ3tdate(String q3tdate) {
		this.q3tdate = q3tdate;
	}
	public String getQ4fdate() {
		return q4fdate;
	}
	public void setQ4fdate(String q4fdate) {
		this.q4fdate = q4fdate;
	}
	public String getQ4tdate() {
		return q4tdate;
	}
	public void setQ4tdate(String q4tdate) {
		this.q4tdate = q4tdate;
	}
    
	public double getQ1percentage() {
		return q1percentage;
	}
	public void setQ1percentage(double q1percentage) {
		this.q1percentage = q1percentage;
	}
	public double getQ2percentage() {
		return q2percentage;
	}
	public void setQ2percentage(double q2percentage) {
		this.q2percentage = q2percentage;
	}
	public double getQ3percentage() {
		return q3percentage;
	}
	public void setQ3percentage(double q3percentage) {
		this.q3percentage = q3percentage;
	}
	public double getQ4percentage() {
		return q4percentage;
	}
	public void setQ4percentage(double q4percentage) {
		this.q4percentage = q4percentage;
	}
	public String getQ1value() {
		return Q1value;
	}
	public void setQ1value(String q1value) {
		Q1value = q1value;
	}
	public String getQ2value() {
		return Q2value;
	}
	public void setQ2value(String q2value) {
		Q2value = q2value;
	}
	public String getQ3value() {
		return Q3value;
	}
	public void setQ3value(String q3value) {
		Q3value = q3value;
	}
	public String getQ4value() {
		return Q4value;
	}
	public void setQ4value(String q4value) {
		Q4value = q4value;
	}
	
	
	
	
}
