package com.zlenadmin.service;

import com.zlenadmin.api.entity.ReviewComments;
import com.zlenadmin.dto.ReviewCommentDto;
import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.model.Role;

public interface ReviewCommentService {
	
	ReviewComments save(ReviewCommentDto reviewCommentDto);

	ReviewComments getReviewComment(long id);
	
	ReviewComments getReviewComment1(String username);
	

}
