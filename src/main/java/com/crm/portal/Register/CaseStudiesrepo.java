 package com.crm.portal.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseStudiesrepo extends JpaRepository<CaseStudies , Long>{

}
