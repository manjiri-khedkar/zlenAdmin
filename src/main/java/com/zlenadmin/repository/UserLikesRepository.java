package com.zlenadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.Likes;

@Repository
public interface UserLikesRepository extends JpaRepository<Likes, Long> {

}
