package com.crm.portal.Campagin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.portal.Lead.Employee;

@Repository
public interface CampaginRepo extends JpaRepository <Campagin , Long> {

	
	
}
