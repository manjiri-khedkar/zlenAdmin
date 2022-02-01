package com.zlenadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

	Poll findByid(long id);

}
