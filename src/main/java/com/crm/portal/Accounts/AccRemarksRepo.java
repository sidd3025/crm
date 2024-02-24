package com.crm.portal.Accounts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.portal.Contacts.RemarksModel;
@Repository
public interface AccRemarksRepo extends JpaRepository <AccountRemarks , Long> {
	
	@Query(value="select r from AccountRemarks r where r.account_id=?1")
	public List<AccountRemarks> getFilterByAccountID(String account_id);

}
