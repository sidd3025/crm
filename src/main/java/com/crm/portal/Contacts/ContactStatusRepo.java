package com.crm.portal.Contacts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.portal.Contacts.ContactStatus;

public interface ContactStatusRepo extends JpaRepository <ContactStatus , Long> {

}
