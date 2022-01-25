package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.StoriesDto;
import com.zlenadmin.dto.UserUpdateDto;

@Repository
public class UserUpdateImpl  implements UserUpdate{
	
	String sql = "select cast(count(uup.event_type) as int) as count , et.status as event  "
			+ "from public.user_update uup "  
			+ "inner join public.event_type et on uup.event_type = et.id "
			+ "where uup.created_at between :fromdate  and  :todaydate  "
			+ "group by et.status ";
	
	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<UserUpdateDto> getEventType(final  Date todaydate, final  Date fromdate){
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("todaydate", todaydate, Types.DATE)
				.addValue("fromdate", fromdate, Types.DATE);
		
		return jdbcTemplate.query(sql, namedParameters, new RowMapper<UserUpdateDto>() {
			public UserUpdateDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				UserUpdateDto ud = new UserUpdateDto();
				ud.setCount(rs.getInt("count"));
				ud.setEvent(rs.getString("event"));
				return ud;
			}
		});
		
	
	}
}
