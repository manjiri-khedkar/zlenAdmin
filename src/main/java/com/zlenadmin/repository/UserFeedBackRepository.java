package com.zlenadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserFeedBack;
import com.zlenadmin.dto.UserFeedBackDto;

@Repository
public  interface UserFeedBackRepository extends JpaRepository<UserFeedBack, Long> {

	@Query(value="SELECT ufb.user_id, ufb.title, ufb.data, ufb.media_url, ufb.media_type FROM public.user_feedback ufb",nativeQuery = true)
	
	List<UserFeedBack> getUserFeedBack();

}
