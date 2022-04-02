package com.zlenadmin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.api.entity.TrendingHashTag;
import com.zlenadmin.dto.BannerDto;
import com.zlenadmin.dto.TrendingHashTagDto;
import com.zlenadmin.repository.TrendingHashTagRepository;

@Service("TrendingHashTagService")
public class TrendingHashTagServiceImpl implements TrendingHashTagService{


	@Autowired
	private TrendingHashTagRepository trendingHashTagRepository;
	
	@Override
	public TrendingHashTag save(TrendingHashTag trendingHashTagDto) {
		trendingHashTagDto.setCreated_at(new Date());
		return trendingHashTagRepository.save(trendingHashTagDto);
		
	}

	@Override
	public TrendingHashTag update(TrendingHashTag trendingHashTagDto) {
		TrendingHashTag trendingHashTag = trendingHashTagRepository.findOne(trendingHashTagDto.getId());
		trendingHashTag.setId(trendingHashTagDto.getId());;
	
		trendingHashTag.setHash_tag(trendingHashTagDto.getHash_tag());
		trendingHashTag.setUrl(trendingHashTagDto.getUrl());	
		trendingHashTag.setSortOrder(trendingHashTagDto.getSortOrder());
		return trendingHashTagRepository.save(trendingHashTagDto);
	}

}
