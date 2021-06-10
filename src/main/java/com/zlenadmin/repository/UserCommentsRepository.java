package com.zlenadmin.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.zlenadmin.api.entity.UserStoriesCommentDetails;

@Repository
public interface UserCommentsRepository extends JpaRepository<UserStoriesCommentDetails, Long> {
	
	List<UserStoriesCommentDetails> findAll();
	
	UserStoriesCommentDetails findById(Long id);
	
	UserStoriesCommentDetails findBySnapId(Long snapId);;
	

	@Query(value ="SELECT uscd.commenter_user_id, uscd.comment_message,uscd.snap_id, uscd.is_active "
			+ "FROM public.user_stories_comment_details uscd WHERE uscd.snap_id = :snapId", nativeQuery = true)
//   List<UserStoriesCommentDetails> getUserComments(@Param("snapId") Long snapId);

	ArrayList<UserStoriesCommentDetails> getUserComments(@Param("snapId") Long snapId);

		
}
