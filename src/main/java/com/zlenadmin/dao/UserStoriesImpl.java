package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.StoriesDto;
import com.zlenadmin.dto.StoryCommentDto;

@Repository
public class UserStoriesImpl implements UserStories {

//	private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
//			+ "from public.user_stories_details usd inner join public.user_details ud on usd.user_id = ud.user_id "
//			+ "where ud.zlen_code like :zlenCode "; 

	private String SQL="select usd.id id, usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, "
			+ "usd.uploaded_path as uploadedPath, ud.zlen_code as zlenCode, ud.user_name as userName "
			+ "from public.user_stories_details usd "
			+ "inner join public.user_details ud on usd.user_id = ud.user_id "  
			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) " 
			+ "and (usd.mime_type LIKE :mimeType or :mimeType1 is null)"  
			+ "and (cast(usd.uploaded_date_time as date) = :uploadedDateTime or :uploadedDateTime1 is null)"
			+ "order by usd.uploaded_date_time desc ";
	
	private String SQL_LATEST="select usd.id id, usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, "
			+ "usd.uploaded_path as uploadedPath, ud.zlen_code as zlenCode, ud.user_name as userName,count(uscd.id) as commentCount "
			+ "from public.user_stories_details usd "
			+"left join public.user_stories_comment_details uscd  on usd.id = uscd.snap_id "
			+ "inner join public.user_details ud on usd.user_id = ud.user_id "  
			+"where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null)  " 
			+"and (usd.mime_type LIKE :mimeType or :mimeType1 is null)"   
			+ "and (cast(usd.uploaded_date_time as date) >= :uploadedDateTime   )"
			+"group by usd.id , usd.uploaded_date_time , usd.mime_type ,"
			+"usd.uploaded_path , ud.zlen_code , ud.user_name "
			+ "order by usd.uploaded_date_time desc ";
	
	private String SQL_comments = "SELECT ud.zlen_code as zlenCode, uscd.comment_message as comment, uscd.snap_id, "
			+ "uscd.is_active, uscd.created_date as dateTime "
			+ "FROM public.user_stories_comment_details uscd "
			+ "inner join public.user_details ud on uscd.commenter_user_id = ud.user_id "
			+ "WHERE uscd.snap_id = :snapId ";
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<StoriesDto> getUserStories(final String zlenCode,final String mimeType,final Date uploadedDateTime) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%"+zlenCode+"%",Types.VARCHAR)
				.addValue("zlenCode1", zlenCode,Types.VARCHAR)
				.addValue("mimeType1", mimeType,Types.VARCHAR)
				.addValue("mimeType", "%"+mimeType+"%",Types.VARCHAR)
				.addValue("uploadedDateTime1", uploadedDateTime,Types.VARCHAR)
				.addValue("uploadedDateTime", uploadedDateTime,Types.DATE);
		
		return jdbcTemplate.query(SQL,namedParameters,new RowMapper<StoriesDto>() {
			public StoriesDto mapRow(ResultSet rs, int rownumber) throws SQLException {  
				StoriesDto ud = new StoriesDto();
				ud.setZlenCode(rs.getString("zlenCode"));
				ud.setUserName(rs.getString("userName"));
				ud.setUploadedDateTime(rs.getDate("uploadedDateTime"));
				ud.setMimeType(rs.getString("mimeType"));
				ud.setUploadedPath(rs.getString("uploadedPath"));
				ud.setId(rs.getLong("id"));
				return ud;
			}
		});
	}
	
	@Override
	public List<StoriesDto> getLatestUserStories(final String zlenCode,final String mimeType,final Date uploadedDateTime) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%"+zlenCode+"%",Types.VARCHAR)
				.addValue("zlenCode1", zlenCode,Types.VARCHAR)
				.addValue("mimeType1", mimeType,Types.VARCHAR)
				.addValue("mimeType", "%"+mimeType+"%",Types.VARCHAR)
				.addValue("uploadedDateTime1", uploadedDateTime,Types.VARCHAR)
				.addValue("uploadedDateTime", uploadedDateTime,Types.DATE);
		
		return jdbcTemplate.query(SQL_LATEST,namedParameters,new RowMapper<StoriesDto>() {
			public StoriesDto mapRow(ResultSet rs, int rownumber) throws SQLException {  
				StoriesDto ud = new StoriesDto();
				ud.setZlenCode(rs.getString("zlenCode"));
				ud.setUserName(rs.getString("userName"));
				ud.setCommentCount(rs.getLong("commentCount"));
				ud.setUploadedDateTime(rs.getDate("uploadedDateTime"));
				ud.setMimeType(rs.getString("mimeType"));
				ud.setUploadedPath(rs.getString("uploadedPath"));
				ud.setId(rs.getLong("id"));
				return ud;
			}
		});
	}

	@Override
	public List<StoryCommentDto> getUserStoriesComments(Long storyId){ 
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("snapId", storyId);
	
		return jdbcTemplate.query(SQL_comments,namedParameters,new RowMapper<StoryCommentDto>() {
		public StoryCommentDto mapRow(ResultSet rs, int rownumber) throws SQLException {  
			StoryCommentDto ud = new StoryCommentDto();
			ud.setZlenCode(rs.getString("zlenCode"));
			ud.setDateTime(rs.getDate("dateTime"));
			ud.setComment(rs.getString("comment"));
			return ud;
		}
		});
	}

	
}

