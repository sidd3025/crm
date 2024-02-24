package com.crm.portal.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.portal.Accounts.AccountStatus;

@Repository
public interface AccStatusRepo extends JpaRepository <AccountStatus , Long> {

}
