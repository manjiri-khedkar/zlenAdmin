package com.zlenadmin.dao;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.StoriesDto;

@Repository
public class UserStoriesImpl implements UserStories {

	private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
			+ "from public.user_stories_details usd inner join public.user_details ud on usd.user_id = ud.user_id "
			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) "
			+ "and (usd.mime_type LIKE :mimeType or :mimeType1 is null)"; 
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<StoriesDto> getUserStories(final String zlenCode,final String mimeType) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%"+zlenCode+"%",Types.VARCHAR)
				.addValue("zlenCode1", zlenCode,Types.VARCHAR)
				.addValue("mimeType1", mimeType,Types.VARCHAR)
				.addValue("mimeType", "%"+mimeType+"%",Types.VARCHAR);
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

	
	
}
