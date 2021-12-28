package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zlenadmin.dto.UserFeedBackDto;
import com.zlenadmin.dto.UsersDetailDto;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

@Repository
public class UserFeedBackImpl implements UserFeedBack {

	String userFeedBack = "SELECT ufb.id as id, ufb.user_id as user_id, ufb.title as title, ufb.data as data, "
			+ "ufb.media_url as media_url, ufb.media_type as media_type FROM public.user_feedback ufb "
			+ "group by ufb.id";

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<UserFeedBackDto> getUserFeedBack() {
		// TODO Auto-generated method stub
		SqlParameterSource namedParameters = new MapSqlParameterSource();
		
		return jdbcTemplate.query(userFeedBack, namedParameters, new RowMapper<UserFeedBackDto>() {
			public UserFeedBackDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserFeedBackDto ufb = new UserFeedBackDto();
				
						ufb.setId(rs.getLong("id"));
						ufb.setUser_id(rs.getString("user_id"));
						ufb.setTitle(rs.getString("title"));
						ufb.setData(rs.getString("data"));
						String url1 =rs.getString("media_url");
						System.out.println(url1);
						String aa  = url1.trim().replace("[", "").replace("]", "");
						String bb = aa.replace("\"", "");
						List<String> url = new  ArrayList<String>() ;
						url.add(bb);
						String cc =null;
						for(int i=0;i<url.size();i++) {
					
							 cc = url.get(i);
							 
						}
						ufb.setMedia_url(cc);
						ufb.setMedia_type(rs.getString("media_type"));
				return ufb;
			}
		});
	
	}
}

