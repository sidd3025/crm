package com.crm.portal.Lead;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductmapingRepo extends JpaRepository <LeadProductMaping , Long> {

	@Query("select l from LeadProductMaping l where l.leadid= ?1")
	  List<LeadProductMaping> GetIDById(Integer leadid);
	
	
}
