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

import com.zlenadmin.dto.PollDto;
import com.zlenadmin.dto.ReportPostDto;

@Repository
public class ReportedPostImpl implements ReportedPost {

	String reportedPost = "select rp.created_at as createdAt,  usd.mime_type as mimeType, usd.uploaded_path as uploadedPath, "
			+ "ud.user_name as userName, ud.zlen_code as userZlenCode, ud1.zlen_code as postZlenCode "
			+ "from public.reported_post rp "
			+ "inner join public.user_details ud on ud.user_id = rp.user_id "
			+ "inner join public.user_stories_details usd on usd.id = rp.post_id "
			+ "left join public.user_details ud1 on ud1.user_id = usd.user_id "
			+ "where (cast(rp.created_at as date) = :createdAt or :createdAt1 is null) "
			+ "and (ud.zlen_code LIKE :userZlenCode or :userZlenCode1 is null) "
			+ "and (ud1.zlen_code LIKE :postZlenCode or :postZlenCode1 is null)"
			+ "order by rp.created_at desc";

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<ReportPostDto> getReportPost(final String userZlenCode, final String postZlenCode, final Date createdAt) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("userZlenCode", "%" + userZlenCode + "%", Types.VARCHAR)
				.addValue("userZlenCode1", userZlenCode, Types.VARCHAR)
				.addValue("postZlenCode", "%" + postZlenCode + "%", Types.VARCHAR)
				.addValue("postZlenCode1", postZlenCode, Types.VARCHAR)
				.addValue("createdAt", createdAt, Types.DATE)
				.addValue("createdAt1", createdAt, Types.VARCHAR);

		return jdbcTemplate.query(reportedPost, namedParameters, new RowMapper<ReportPostDto>() {
			public ReportPostDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				ReportPostDto rpd = new ReportPostDto();
				rpd.setUserName(rs.getString("userName"));
				rpd.setCreatedAt(rs.getDate("createdAt"));
				rpd.setMimeType(rs.getString("mimeType"));
				rpd.setUploadedPath(rs.getString("uploadedPath"));
				rpd.setUserZlenCode(rs.getString("userZlenCode"));
				rpd.setPostZlenCode(rs.getString("postZlenCode"));

				return rpd;
			}
		});
	}

}
