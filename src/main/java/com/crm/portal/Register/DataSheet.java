package com.crm.portal.Register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="datasheet")
public class DataSheet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	private Long employee_id;
	private String filedescription1;
	private String readingmaterials1;
	private String type1;
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
	public String getFiledescription1() {
		return filedescription1;
	}
	public void setFiledescription1(String filedescription1) {
		this.filedescription1 = filedescription1;
	}
	public String getReadingmaterials1() {
		return readingmaterials1;
	}
	public void setReadingmaterials1(String readingmaterials1) {
		this.readingmaterials1 = readingmaterials1;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}

	
	
}
