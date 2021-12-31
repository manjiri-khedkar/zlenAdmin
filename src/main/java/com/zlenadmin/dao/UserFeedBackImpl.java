package com.zlenadmin.dao;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zlenadmin.dto.UserFeedBackDto;
import com.zlenadmin.dto.UsersDetailDto;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

@Repository
public class UserFeedBackImpl implements UserFeedBack {

	String userFeedBack = "SELECT ufb.id as id, ufb.user_id as user_id, ufb.title as title, ufb.data as data, " + 
			"ufb.media_url as media_url, ufb.media_type as media_type, ud.user_name as user_name, " + 
			"ud.user_mobile as user_mobile,ud.id as uid FROM public.user_feedback ufb inner join user_details ud " + 
			"on ufb.user_id = ud.user_id  where  ud.user_id = ufb.user_id ";

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
				ufb.setUser_name(rs.getString("user_name"));
				ufb.setUser_mobile(rs.getString("user_mobile"));
				String url1 =rs.getString("media_url");
				
				JsonParser json = new JsonParser();
				JsonElement ele =  json.parse(url1);
				JsonArray jsonArray =  ele.getAsJsonArray();
				if (jsonArray.size()>0) {
					String[] arr = new String[jsonArray.size()];
					List<String> urls = new  ArrayList<String>() ;
					for (int itr=0;itr<jsonArray.size();itr++) {
						String eleValue = jsonArray.get(itr).getAsString();
						arr[itr]= eleValue;
					}
					
					ufb.setMedia_url(arr);
				}
				ufb.setUid(rs.getLong("uid"));
				ufb.setMedia_type(rs.getString("media_type"));
				return ufb;
			}
		});

	}
}
