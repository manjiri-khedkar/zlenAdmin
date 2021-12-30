package com.zlenadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.ReviewComments;
import com.zlenadmin.api.entity.UserFeedBack;
import com.zlenadmin.dto.ReviewCommentDto;
import com.zlenadmin.dto.UserFeedBackDto;

@Repository
public interface UserFeedBackRepository extends JpaRepository<UserFeedBack, Long> {

//	@Query(value = "SELECT ufb.id as id, ufb.user_id as user_id, ufb.title as title, ufb.data as data, "
//			+ "count(ufb.media_url) as media_count, ufb.media_type as media_type FROM public.user_feedback ufb "
//			+ "group by ufb.id ", nativeQuery = true)

//	List<UserFeedBackDto> getUserFeedBack();

	
}