package com.zlenadmin.service;

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
	public TrendingHashTag save(TrendingHashTagDto trendingHashTagDto) {
		TrendingHashTag trendingHashTag = new TrendingHashTag();	     
		trendingHashTag.setHash_tag(trendingHashTagDto.getHashtag());
		trendingHashTag.setCreated_at(trendingHashTagDto.getCreatedAt());
		trendingHashTag.setUrl(trendingHashTagDto.getUrl());
		return trendingHashTagRepository.save(trendingHashTag);
		
	}

	@Override
	public TrendingHashTag update(TrendingHashTagDto trendingHashTagDto) {
		TrendingHashTag trendingHashTag = trendingHashTagRepository.findOne(trendingHashTagDto.getId());
		trendingHashTag.setId(trendingHashTagDto.getId());;
		trendingHashTag.setHash_tag(trendingHashTagDto.getHashtag());
		trendingHashTag.setCreated_at(trendingHashTagDto.getCreatedAt());
		trendingHashTag.setUrl(trendingHashTagDto.getUrl());		
		return trendingHashTagRepository.save(trendingHashTag);
	}

}
