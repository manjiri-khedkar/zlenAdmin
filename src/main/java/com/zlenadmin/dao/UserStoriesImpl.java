package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.UserDetails;

@Repository
public class UserStoriesImpl implements UserStories {

	private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
			+ "from user_stories_details usd inner join user_details ud on usd.user_id = ud.user_id";
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<UserDetails> getUserStories(String name) {
		return jdbcTemplate.query(SQL,new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet rs, int rownumber) throws SQLException {  
				UserDetails ud = new UserDetails();
				//ud.setDisplayName(rs.getString("displayName"));
				return ud;
			}
		});
	}

	
	
}
