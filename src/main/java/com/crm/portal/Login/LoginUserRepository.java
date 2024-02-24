package com.crm.portal.Login;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer>{

	public Optional<LoginUser> findByUsername(String username);
	
	@Query("select l.login_user_id from LoginUser l where l.username = ?1")
	  Integer GetAuthorityID(String username);
	
	@Modifying
	@Query("update LoginUser l set l.password=?2 where l.username =?1")
	 void UpdatePasswordByUsername(String username, String password);
	
	@Query("select l from LoginUser l where l.username = ?1")
	 public List<LoginUser> getUserName(String username);
	
	@Query("select count(l) from LoginUser l where l.username = ?1")
	 public int findByUser(String username);
}