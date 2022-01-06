package com.zlenadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.ReviewComments;
import com.zlenadmin.dto.ReviewCommentDto;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComments, Long> {
	
	ReviewComments findByFeedbackId(Long feedbackId);

//	ReviewComments findByusername(String username);
	
}
