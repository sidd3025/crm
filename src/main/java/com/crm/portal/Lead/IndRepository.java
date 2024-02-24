package com.crm.portal.Lead;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndRepository extends JpaRepository <IndustryMaster , Long>{


}
