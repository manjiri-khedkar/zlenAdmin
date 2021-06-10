package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.ActivityDto;

@Repository
public class UserActivityImpl implements UserActivity {
	
	private String SQL= "select  ua.activity as act, ua.created_date as crtdt, ud.user_name as uname, ud.user_mobile as mobile, ud.zlen_code as zcode "
			 + "from user_activity_details ua inner join user_details ud "
   		   	 + "on ua.user_id = ud.user_id";
 	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<ActivityDto> getUserActivity(final String userId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId);
		
		return jdbcTemplate.query(SQL,namedParameters,new RowMapper<ActivityDto>() {
			public ActivityDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				
           
				ActivityDto ud = new ActivityDto();
				
			      
				  ud.setUserName(rs.getString("uname"));
				  ud.setActivity(rs.getInt("act"));
				  ud.setCreatedDate(rs.getDate("crtdt"));
				  ud.setUserMobile(rs.getString("mobile"));
				  ud.setZlenCode(rs.getString("zcode"));
				 

				return ud;
			}
		});
	}



}
