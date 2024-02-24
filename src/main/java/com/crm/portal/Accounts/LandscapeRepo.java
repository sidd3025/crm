package com.crm.portal.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandscapeRepo extends JpaRepository <Landscape , Long> {

}
