package com.crm.portal.Lead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account_TypeRepository extends JpaRepository <AccountType , Long> {

}
