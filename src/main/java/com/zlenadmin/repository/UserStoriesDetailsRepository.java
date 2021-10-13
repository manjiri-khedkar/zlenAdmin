package com.zlenadmin.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserStoriesDetails;
import com.zlenadmin.dto.StoriesDto;

@Repository
public interface UserStoriesDetailsRepository extends JpaRepository<UserStoriesDetails, Long> {

	UserStoriesDetails findByUserId(String userId);
	
	public List<UserStoriesDetails> findAll();
	
	@Query(value="select count(usd.id) as count, DATE(usd.uploaded_date_time) as createDate, usd.video_type as mime_type from public.user_stories_details usd where usd.uploaded_date_time >= :date group by DATE(usd.uploaded_date_time), usd.video_type  order by DATE(usd.uploaded_date_time) desc  ", nativeQuery=true)
	List<Object[]> getStoriesGraphQuery(@org.springframework.data.repository.query.Param("date") Date date);
	
	@Query(value="select count(po.id) as pollcount,Date(po.created_at) as Date from public.poll po where created_at >= :date group by DATE(created_at) ", nativeQuery=true )
	List<Object[]> getPollGraphQuery(@org.springframework.data.repository.query.Param("date") Date date);
	
	@Query(value="select usd.mime_type, DATE(usd.uploaded_date_time), usd.uploaded_path, ud.user_name from public.user_stories_details usd inner join user_details ud on usd.user_id = ud.user_id", nativeQuery=true)
	List<Tuple> getStoriesDetails();
}
