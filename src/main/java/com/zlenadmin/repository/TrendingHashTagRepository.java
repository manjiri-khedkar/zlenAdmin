package com.zlenadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.api.entity.TrendingHashTag;

@Transactional
@Repository
public interface TrendingHashTagRepository extends JpaRepository<TrendingHashTag, Long>{

	TrendingHashTag save(TrendingHashTag trendingHashTag);
	
	 List<TrendingHashTag> findAll();

	 @Modifying
		@Query(value="delete from public.trending_hash_tags where id = :id",nativeQuery = true)
		 int deleteTrendinghashTag(@Param("id") int id);

	TrendingHashTag findById(long id);
	

}
