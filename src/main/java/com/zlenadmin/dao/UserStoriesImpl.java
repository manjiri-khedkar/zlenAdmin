package com.zlenadmin.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.StoriesDto;
import com.zlenadmin.dto.StoryCommentDto;
import com.zlenadmin.dto.UserLikesDto;

@Repository
public class UserStoriesImpl implements UserStories {

//	private String SQL="select usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, ud.zlen_code as zlenCode "
//			+ "from public.user_stories_details usd inner join public.user_details ud on usd.user_id = ud.user_id "
//			+ "where ud.zlen_code like :zlenCode "; 

	private String SQL = "select usd.id id, usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType,"
			+ "usd.uploaded_path as uploadedPath, usd.is_banned as isbanned, ud.id as uid, ud.zlen_code as zlenCode, ud.is_banned as isbanned1, ud.user_name as userName,count(uscd.id) as commentCount"
			+ ", count(l.id) as likesCount  "
			+ "from  public.user_stories_details usd left join public.likes l  on usd.id = l.post_id "
			+ "left join public.user_stories_comment_details uscd  on usd.id = uscd.snap_id "
			+ "inner join public.user_details ud on usd.user_id = ud.user_id "
			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) "
			+ "and (usd.mime_type LIKE :mimeType or :mimeType1 is null) "
			+ "and (cast(usd.uploaded_date_time as date) = :uploadedDateTime or :uploadedDateTime1 is null) "
			+ "and (usd.is_zlen_world = :zlenWorld or :zlenWorld1 =false) "
			//+ "and (usd.is_banned = :isbanned or :isbanned1 =false) "
			+ "group by usd.id , usd.uploaded_date_time , usd.mime_type ,"
			+ "usd.uploaded_path , ud.id, ud.zlen_code , ud.is_banned, ud.user_name " 
			+ "order by usd.uploaded_date_time desc ";

	private String SQL_LATEST = "select usd.id id, usd.uploaded_date_time as uploadedDateTime, usd.mime_type mimeType, "
			+ "usd.uploaded_path as uploadedPath, usd.is_banned as isbanned, ud.zlen_code as zlenCode, ud.user_name as userName,count(distinct uscd.id) as commentCount "
			+ ", count(distinct l.id) as likesCount, ud.id as uid, ud.is_banned as isbanned1  "
			+ "from public.user_stories_details usd "
			+ "left join public.likes l  on usd.id = l.post_id "
			+ "left join public.user_stories_comment_details uscd  on usd.id = uscd.snap_id "
			+ "inner join public.user_details ud on usd.user_id = ud.user_id "
			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null)  "
			+ "and (usd.mime_type LIKE :mimeType or :mimeType1 is null)"
			+ "and (cast(usd.uploaded_date_time as date) >= :uploadedDateTime   ) "
			+ "and usd.is_zlen_world=false "
			+ "group by usd.id , usd.uploaded_date_time , usd.mime_type ,"
			+ "usd.uploaded_path ,usd.is_banned , ud.id, ud.is_banned , ud.zlen_code , ud.user_name " 
			+ "order by usd.uploaded_date_time desc ";

	private String SQL_comments = "SELECT ud.zlen_code as zlenCode, uscd.comment_message as comment, uscd.snap_id, "
			+ "uscd.is_active, uscd.created_date as dateTime " + "FROM public.user_stories_comment_details uscd "
			+ "inner join public.user_details ud on uscd.commenter_user_id = ud.user_id "
			+ "WHERE uscd.snap_id = :snapId ";

	private String SQL_likes = "SELECT ud.zlen_code as zlenCode, ud.user_name as name, l.post_id, l.created_at as dateTime" + 
			" from public.likes l inner join public.user_details ud on l.reactor_user_id = ud.user_id " + 
			"WHERE l.post_id = :postId ";

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<StoriesDto> getUserStories(final String zlenCode, final String mimeType, final Date uploadedDateTime, final boolean zlenWorld) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%" + zlenCode + "%", Types.VARCHAR)
				.addValue("zlenCode1", zlenCode, Types.VARCHAR)
				.addValue("mimeType1", mimeType, Types.VARCHAR)
				.addValue("mimeType", "%" + mimeType + "%", Types.VARCHAR)
				.addValue("uploadedDateTime1", uploadedDateTime, Types.VARCHAR)
				.addValue("uploadedDateTime", uploadedDateTime, Types.DATE)
				.addValue("zlenWorld", zlenWorld, Types.BOOLEAN)
				.addValue("zlenWorld1",zlenWorld, Types.BOOLEAN);
				//.addValue("isbanned",isbanned, Types.BOOLEAN)
				//.addValue("isbanned1",isbanned, Types.BOOLEAN);

		return jdbcTemplate.query(SQL, namedParameters, new RowMapper<StoriesDto>() {
			public StoriesDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				StoriesDto ud = new StoriesDto();
				ud.setZlenCode(rs.getString("zlenCode"));
				ud.setUserName(rs.getString("userName"));
				ud.setCommentCount(rs.getLong("commentCount"));
				ud.setLikesCount(rs.getLong("likesCount"));
				ud.setUploadedDateTime(rs.getDate("uploadedDateTime"));
				ud.setMimeType(rs.getString("mimeType"));
				ud.setUploadedPath(rs.getString("uploadedPath"));
				ud.setId(rs.getLong("id"));
				ud.setIsbanned(rs.getBoolean("isbanned"));
				ud.setIsbanned1(rs.getBoolean("isbanned1"));
				ud.setUid(rs.getLong("uid"));
				//ud.setIsActive(rs.getString("isActive"));
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
				ud.setLikesCount(rs.getLong("likesCount"));
				ud.setUploadedDateTime(rs.getDate("uploadedDateTime"));
				ud.setMimeType(rs.getString("mimeType"));
				ud.setUploadedPath(rs.getString("uploadedPath"));
				ud.setId(rs.getLong("id"));
				ud.setIsbanned(rs.getBoolean("isbanned"));
				ud.setIsbanned1(rs.getBoolean("isbanned1"));
				ud.setUid(rs.getLong("uid"));
				//ud.setIsbanned(rs.getBoolean("isbanned"));
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

	@Override
		public List<UserLikesDto> getUserLikes(Long postId){ 
			SqlParameterSource namedParameters = new MapSqlParameterSource()
			.addValue("postId", postId);
			
			return jdbcTemplate.query(SQL_likes,namedParameters,new RowMapper<UserLikesDto>() {
				public UserLikesDto mapRow(ResultSet rs, int rownumber) throws SQLException {  
					UserLikesDto ud = new UserLikesDto();
					ud.setZlenCode(rs.getString("zlenCode"));
					ud.setName(rs.getString("name"));
					ud.setDateTime(rs.getDate("dateTime"));
					return ud;
			
			
			
	}
			});
	}
	}
	