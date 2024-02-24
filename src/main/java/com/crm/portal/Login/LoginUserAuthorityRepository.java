package com.crm.portal.Login;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginUserAuthorityRepository  extends JpaRepository < LoginUserAuthority , Integer >{
	
//	@Query("select a.user_id from LoginUserAuthority a where a.authority_id = ?1")
//	  Integer GetUserID(Integer authority_id);
//	
//	@Modifying
//	@Query("delete from LoginUserAuthority a where a.authority_id = ?1")
//	void DeleteLoginUserAuthority(Integer authority_id);
	
	@Query("select a.authority_id from LoginUserAuthority a where a.user_id = ?1")
	  Integer GetAuthorityID(Integer user_id);
	
	@Query("select count(a.user_id) from LoginUserAuthority a where a.authority_id = ?1")
	  Integer GetCountID(Integer authority_id);
}
