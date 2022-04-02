package com.zlenadmin.service;

import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.api.entity.TrendingHashTag;
import com.zlenadmin.dto.BannerDto;
import com.zlenadmin.dto.TrendingHashTagDto;

public interface TrendingHashTagService {
	
	TrendingHashTag save(TrendingHashTag trendingHashTagDto);

	TrendingHashTag update(TrendingHashTag trendingHashTagDto);

}
