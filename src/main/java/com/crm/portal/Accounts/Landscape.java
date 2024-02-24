package com.crm.portal.Accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="landscape")
public class Landscape {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	
	private Long id;
	private String landscapesoftware;
	private String softwarestream;
	
	
	

	
	public String getSoftwarestream() {
		return softwarestream;
	}
	public void setSoftwarestream(String softwarestream) {
		this.softwarestream = softwarestream;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLandscapesoftware() {
		return landscapesoftware;
	}
	public void setLandscapesoftware(String landscapesoftware) {
		this.landscapesoftware = landscapesoftware;
	}
	
		
	
	
	
	
}
