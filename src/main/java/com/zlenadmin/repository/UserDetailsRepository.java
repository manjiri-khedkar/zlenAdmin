package com.zlenadmin.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByUserMobile(String userMobile);
	
	UserDetails findByZlenCode(String zlenCode);
	
	@Query(value="select count(ud.id) as count, DATE(ud.created_on) as createDate "
			+ "from public.user_details ud "
			+ "where DATE(ud.created_on) >= :varDate "
			+ "group by DATE(ud.created_on) "
			+ "order by DATE(ud.created_on) desc", nativeQuery = true)
	List<Object[]> getGraphQuery(@Param("varDate") Date varDate);
	
	List<UserDetails> findAll();
	
	@Query("SELECT u.userName, u.userMobile, u.zlenCode, u.deviceType FROM UserDetails u")
	ArrayList<UserDetails> getUserDetails();
	
	UserDetails findById(long id);

	@Query("SELECT u FROM UserDetails u "
			+ "WHERE (u.userName LIKE  %:userName% or :userName is null )"
			+ "and ( u.userMobile LIKE %:userMobile% or :userMobile is null ) "
			+ "and (( u.zlenCode  LIKE %:zlenCode% or :zlenCode is null )) "
			+ "and (u.deviceType = :deviceType or :deviceType is null) "
			+ "and (Date(u.createdOn) = :createdOn  or cast(:createdOn as date) is null) ")
	ArrayList<UserDetails> getUserDetails(@Param("userName") String userName, @Param("userMobile") String userMobile, @Param("zlenCode") String zlenCode, @Param("deviceType") String deviceType, @Param("createdOn") @Temporal  Date createdOn);
	
	
	@Query(value ="SELECT ud.user_name, ud.user_mobile, ud.zlen_code, ud.device_Type, ud.longitude, ud.latitude  FROM user_details ud where ud.user_id =? ", nativeQuery = true)
    List<UserDetails> getUserDetails(@Param("userId") String userId);
	
	
	
	@Query(value = "SELECT u.* FROM public.user_details u "
			+ "inner join public.user_friends_details ufd on ufd.friend_user_id=u.user_id "
			+ "WHERE ufd.user_id = :userId "
			+ "union "
			+"SELECT u.* FROM public.user_details u "
			+ "inner join public.user_friends_details ufd on ufd.user_id=u.user_id "
			+ "WHERE ufd.friend_user_id = :userId ", nativeQuery = true)
	ArrayList<UserDetails> getUserFriends(@Param("userId") String userId);
	
	@Query(value="select count(ud.id) as count from public.user_details ud where ud.created_on=ud.created_on", nativeQuery= true)
	Integer getUserDetails1();
	
	@Query(value="select count(ud.id) as count from public.user_details ud where ud.created_on >= :createdDate", nativeQuery= true)
	Integer getUserDetails2(@Param("createdDate") Date createdDate);

	
	

	
}
