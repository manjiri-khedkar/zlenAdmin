package com.zlenadmin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserUpdate;
import com.zlenadmin.dto.UserUpdateDto;

@Repository
public interface ActiveUserUpdateRepository extends JpaRepository<UserUpdate, Long> {
	
	@Query(value = "select count(distinct uup.user_id) as count from user_update uup  where uup.created_at >= :todaydate ", nativeQuery = true)
	Integer getTodayActiveUser(@Param("todaydate") Date todate);
	
	@Query(value = "select count(distinct uup.user_id) as count from user_update uup where uup.created_at >= :todate ", nativeQuery = true)
	Integer getMonthlyActiveUser(@Param("todate") Date todate);
	
	
	@Query(value = "select count(distinct uup.id)  as count from user_update uup where uup.created_at >= :todate ", nativeQuery = true)
	Integer getAverageTimeSpendOneUserPerDay(@Param("todate") Date todate);
	
//	@Query(value = "select cast(count(uup.event_type) as int) as count , et.status as event  from user_update uup " 
//	+ "inner join event_type et on uup.event_type = et.id where uup.created_at >= :todaydate and uup.created_at >= :fromdate "
//	+ "group by et.status", nativeQuery = true)
//	List<Object[]> getEventType(@Param("todaydate") Date todaydate, @Param("fromdate") Date fromdate);
	
	

}
