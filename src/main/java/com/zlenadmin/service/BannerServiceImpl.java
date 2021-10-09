package com.zlenadmin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlenadmin.api.entity.Banner;
import com.zlenadmin.dto.BannerDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.BannerRepository;

@Service("bannerService")
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;
	
	@Override
	public Banner save(BannerDto bannerDto) {
		// TODO Auto-generated method stub
		
		Banner banner = new Banner();	     
		banner.setStart_date(bannerDto.getStart_date());
		banner.setEnd_date(bannerDto.getEnd_date());
		banner.setFrequency(bannerDto.getFrequency());
		banner.setContent(bannerDto.getContent());
		return bannerRepository.save(banner);
	}

	@Override
	public Banner update(BannerDto bannerDto) {
	
		// TODO Auto-generated method stub
		
		Banner banner = bannerRepository.findBanner(bannerDto.getBanner_id());
		banner.setBanner_id(bannerDto.getBanner_id());
		banner.setStart_date(bannerDto.getStart_date());
		banner.setEnd_date(bannerDto.getEnd_date());
		banner.setFrequency(bannerDto.getFrequency());
		banner.setContent(bannerDto.getContent());		
		return bannerRepository.save(banner);
	}

}
