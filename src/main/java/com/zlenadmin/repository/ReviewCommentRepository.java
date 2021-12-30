package com.zlenadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zlenadmin.api.entity.ReviewComments;
import com.zlenadmin.dto.ReviewCommentDto;

public interface ReviewCommentRepository extends JpaRepository<ReviewComments, Long> {
	

}
