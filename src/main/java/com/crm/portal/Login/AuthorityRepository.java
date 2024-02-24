package com.crm.portal.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthorityRepository extends JpaRepository < Authority , Integer > {

}
