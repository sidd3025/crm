package com.crm.portal.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.portal.Accounts.Account;

@Repository
public interface ReportLeadRepository extends JpaRepository <ReportLead , Long> {

}
