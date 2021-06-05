package com.zlenadmin.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.dto.StoriesDto;

@Repository
public interface UserStoriesDetailsRepository extends JpaRepository<UserStoriesDetails, Long> {

	UserStoriesDetails findByUserId(String userId);
	
	List<UserStoriesDetails> findAll();
	
	@Query(value="select count(usd.id) as count, DATE(usd.uploaded_date_time) as createDate, usd.mime_type from public.user_stories_details usd group by DATE(usd.uploaded_date_time), usd.mime_type order by DATE(usd.uploaded_date_time) desc ", nativeQuery=true)
	List<Object[]> getStoriesGraphQuery();
	
	@Query(value="select usd.mime_type, DATE(usd.uploaded_date_time), usd.uploaded_path, ud.user_name from public.user_stories_details usd inner join user_details ud on usd.user_id = ud.user_id", nativeQuery=true)
	List<Tuple> getStoriesDetails();
}
