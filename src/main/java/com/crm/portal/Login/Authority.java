package com.crm.portal.Login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="system_login_authority")
public class Authority {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="AUTHORITY_ID")
	private Integer authority_id;
	
	@Column(name="AUTHORITY_NAME")
	private String authority;
	
	@Column(name="STATUS")
	private String status;
	
//	@Column(name="PARENT_ID")
//	private Integer parent_id;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(Integer authority_id) {
		this.authority_id = authority_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
//
//	public Integer getParent_id() {
//		return parent_id;
//	}
//
//	public void setParent_id(Integer parent_id) {
//		this.parent_id = parent_id;
//	}
	
}
