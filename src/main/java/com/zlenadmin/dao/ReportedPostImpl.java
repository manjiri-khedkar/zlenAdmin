package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
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
			+ "usd.is_banned as postisbanned, usd.id as pid, ud1.id as uid, ud1.is_banned as userisbanned, "
			+ "ud.user_name as userName, ud.zlen_code as userZlenCode, ud1.zlen_code as postZlenCode,"
			+ "rp.type_id as typeId "
			+ "from public.reported_post rp "
			+ "inner join public.user_details ud on ud.user_id = rp.user_id "
			+ "inner join public.user_stories_details usd on usd.id = rp.post_id "
			+ "inner join public.user_details ud1 on ud1.user_id = usd.user_id "
			+ "where (cast(rp.created_at as date) = :createdAt or :createdAt1 is null) "
			+ "and (ud.zlen_code LIKE :userZlenCode or :userZlenCode1 is null) "
			+ "and (ud1.zlen_code LIKE :postZlenCode or :postZlenCode1 is null)"
			+ "order by rp.created_at desc";

	public static HashMap<Integer,String> typeMap;
	static {
		typeMap = new HashMap<Integer,String>();
		typeMap.put(1, "Self injury");
		typeMap.put(2, "Harassment or bullying");
		typeMap.put(3, "Sale or promotion of drugs");
		typeMap.put(4, "Sale or promotion of firearms");
		typeMap.put(5, "Nudity or pornography");
		typeMap.put(6, "Violence or harm");
		typeMap.put(7, "Hate speech or symbols");
		typeMap.put(8, "Intellectual property violation");
		typeMap.put(9, "I just don't like it");
		typeMap.put(10, "Other");
	}
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
				rpd.setPostisbanned(rs.getBoolean("postisbanned"));
				rpd.setUserisbanned(rs.getBoolean("userisbanned"));
				rpd.setPid(rs.getLong("pid"));
				rpd.setUid(rs.getLong("uid"));
				int typeId =rs.getInt("typeId");
				if (typeMap.containsKey(typeId)){
					rpd.setType(typeMap.get(typeId));
				}else{
					rpd.setType("");
				}

				return rpd;
			}
		});
	}

}
