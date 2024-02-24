package com.crm.portal.Accounts;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="accountStatus_master")
public class AccountStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private String accountstatus;
	private Long compnayid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAccountstatus() {
		return accountstatus;
	}
	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}
	
	public Long getCompnayid() {
		return compnayid;
	}
	public void setCompnayid(Long compnayid) {
		this.compnayid = compnayid;
	}

	

	
	
}
