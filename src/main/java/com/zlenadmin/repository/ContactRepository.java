package com.zlenadmin.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	List<Contact> findAll();
	
	Contact findById(long id);

	
	@Query(value ="SELECT ct.created_at, ct.updated_at, ct.user_mobile From contact ct Where ct.user_id=ct.user_id", nativeQuery = true)
	ArrayList<Contact> getContactTable(@Param("user_id") String user_id);

}

