package com.zlenadmin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.zlenadmin.dto.StoriesDto;

@Repository
public class UserStoriesImpl implements UserStories {

//	private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
//			+ "from public.user_stories_details usd inner join public.user_details ud on usd.user_id = ud.user_id "
//			+ "where ud.zlen_code like :zlenCode "; 

	private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
			+ "from public.user_stories_details usd inner join public.user_details ud on usd.user_id = ud.user_id "
			+ "where ud.zlen_code like :zlenCode " 
			+ "and ( usd.mime_type = :mimeType or :mimeType is null ) ";
//			+ "and ( usd.uploaded_date_time = :uploadedDateTime or :uploadedDateTime is null ) ";
//	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<StoriesDto> getUserStories(final String zlenCode) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("zlenCode", zlenCode);
//		PreparedStatementSetter ps = new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, zlenCode);
//            }
//        };
		return jdbcTemplate.query(SQL,namedParameters,new RowMapper<StoriesDto>() {
			public StoriesDto mapRow(ResultSet rs, int rownumber) throws SQLException {  
				StoriesDto ud = new StoriesDto();
				ud.setZlenCode(rs.getString("zlenCode"));
				ud.setUploadedDateTime(rs.getDate("uploadedDateTime"));
				ud.setMimeType(rs.getString("mimeType"));
				
				return ud;
			}
		});
	}

	@Override
	public List<StoriesDto> getUserStories(String mimeType, String zlenCode) {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("mimeType", mimeType).addValue("zlenCode", zlenCode);
		
		return jdbcTemplate.query(SQL,namedParameters,new RowMapper<StoriesDto>() {
			public StoriesDto mapRow(ResultSet rs, int rownumber) throws SQLException {  
				StoriesDto ud = new StoriesDto();
				ud.setUploadedDateTime(rs.getDate("uploadedDateTime"));
				ud.setMimeType(rs.getString("mimeType"));
				ud.setZlenCode(rs.getString("zlenCode"));				
				return ud;
			}
		});
	}
	
}







//private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
//+ "from public.user_stories_details usd inner join public.user_details ud on usd.user_id = ud.user_id "
//+ "where ud.zlen_code like :zlenCode "
//+ "and ( ud.uploaded_date_time like :uploaded_date_time or :uploaded_date_time is null ) "
//+ "and ( ud.mime_type  like :mime_type or :mime_type is null ) "
//+ "and ((ud.zlen_code like :zlenCode or :zlenCode is null)) "; 
//
