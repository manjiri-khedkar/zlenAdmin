package com.zlenadmin.service;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlenadmin.api.entity.ReviewComments;
import com.zlenadmin.dto.ReviewCommentDto;
import com.zlenadmin.repository.ReviewCommentRepository;

@Service
public class ReviewCommentImpl implements ReviewCommentService {
	
	@Autowired
	private ReviewCommentRepository reviewCommentRepository;
	

	@Override
	public ReviewComments save(ReviewCommentDto reviewCommentDto) {
		
		ReviewComments rc = new ReviewComments();
		rc.setReviewId(reviewCommentDto.getReviewId());
		rc.setFeedbackId(reviewCommentDto.getFeedbackId());
		rc.setComments(reviewCommentDto.getComments());
		
		return reviewCommentRepository.save(rc);
	}
	
	@Override
	public ReviewComments getReviewComment(long id) {
		return reviewCommentRepository.findByFeedbackId(id);
	}

}
