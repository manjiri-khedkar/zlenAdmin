package com.zlenadmin.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.dto.BannerDto;


public interface BannerService { 
	
	
	Banner save(BannerDto bannerDto);
	
	Banner update(BannerDto bannerDto);
	

}
