package com.crm.portal.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualRepository extends JpaRepository <AnnualRevenue , Long> {

}