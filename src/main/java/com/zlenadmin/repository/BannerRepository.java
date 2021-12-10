package com.zlenadmin.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zlenadmin.api.entity.Banner;

@Transactional 
@Repository

public interface BannerRepository extends JpaRepository<Banner, Integer>{
	
	public List<Banner> findAll();
	
	@Query(value="select banner_id,start_date,end_date,is_active,frequency,content  from public.banner where banner_id = :id", nativeQuery = true)
	Banner findBanner(@Param("id") int id);
	
	@Modifying
	@Query(value="delete from public.banner where banner_id = :id",nativeQuery = true)
	 int deleteBanner(@Param("id") int id);
	
	@Modifying
	@Query(value="UPDATE public.banner set is_active = CASE banner_id WHEN :id Then true else false end",nativeQuery =  true)
	int isActive(@Param("id") int id);
	
	
	@Query(value=" select * from public.banner where is_active = true",nativeQuery = true)
	List<Banner> findActiveBanner();
	

	}
