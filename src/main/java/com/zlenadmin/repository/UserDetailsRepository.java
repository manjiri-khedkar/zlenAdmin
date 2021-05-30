package com.zlenadmin.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByUserMobile(String userMobile);
	
	UserDetails findByZlenCode(String zlenCode);
	
	@Query(value="select count(ud.id) as count, DATE(ud.created_on) as createDate from user_details ud group by DATE(ud.created_on) order by DATE(ud.created_on) desc", nativeQuery = true)
	List<Object[]> getGraphQuery();
	
	List<UserDetails> findAll();
	
	@Query("SELECT u.userName, u.userMobile, u.zlenCode, u.deviceType FROM UserDetails u")
	ArrayList<UserDetails> getUserDetails();
	

	@Query("SELECT u FROM UserDetails u "
			+ "WHERE u.userName LIKE  %:userName% "
			+ "and ( u.userMobile LIKE %:userMobile% or :userMobile is null ) "
			+ "and (( u.zlenCode  LIKE %:zlenCode% or :zlenCode is null )) "
			+ "and (u.deviceType LIKE %:deviceType% or :deviceType is null) ")
	ArrayList<UserDetails> getUserDetails(@Param("userName") String userName, @Param("userMobile") String userMobile, @Param("zlenCode") String zlenCode, @Param("deviceType") String deviceType);
	
}
