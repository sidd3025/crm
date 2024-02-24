package com.crm.portal.Lead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagestatusRepo  extends JpaRepository <StageStatus , Long> {

}
