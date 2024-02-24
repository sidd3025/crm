package com.crm.portal.Contacts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarksRepo extends JpaRepository <RemarksModel , Long> {

	@Query(value="select r from RemarksModel r where r.contact_id=?1")
	public List<RemarksModel> getFilterByContactID(String contact_id);
}
