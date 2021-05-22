package com.zlenadmin.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.UserDetailsDto;
import com.zlenadmin.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByUserMobile(String userMobile);
	
	UserDetails findByZlenCode(String zlenCode);
	
	@Query(value="select count(ud.id) as count, DATE(ud.created_on) as createDate from user_details ud group by DATE(ud.created_on) order by DATE(ud.created_on) desc", nativeQuery = true)
	List<Object[]> getGraphQuery();
		
}
