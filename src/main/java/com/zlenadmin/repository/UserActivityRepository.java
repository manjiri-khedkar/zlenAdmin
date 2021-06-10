package com.zlenadmin.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserActivityDetails;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivityDetails, Long> {
	
	
	//@Query(value="select ua.activity, ua.created_date,ud.user_name,ud.user_mobile,ud.zlen_code from user_activity_details ua inner join user_details ud on ua.user_id=ud.user_id",nativeQuery=true)
	
	@Query(value="select ua.user_id,ua.activity, ua.created_date,ud.user_id,ud.user_name,ud.user_mobile,ud.zlen_code from user_activity_details ua inner join user_details ud on ua.user_id = ud.user_id ",nativeQuery = true)
	List<Object[]> getUserActivity();
	
}

