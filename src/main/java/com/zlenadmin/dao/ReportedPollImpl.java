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
import com.zlenadmin.dto.ReportedPollDto;

@Repository
public class ReportedPollImpl implements ReportedPoll {

	String reportedPoll = "select rp.created_at as createdAt, ud.user_name as userName, ud.zlen_code as userZlenCode, ud1.zlen_code as pollZlenCode "
			+ "from public.reported_poll rp inner join public.user_details ud on ud.user_id = rp.user_id "
			+ "inner join public.poll pl on pl.id = rp.poll_id left join public.user_details ud1 on ud1.user_id = rp.user_id "
			+ "where (cast(rp.created_at as date) = :createdAt or :createdAt1 is null) and (ud.zlen_code LIKE :userZlenCode or :userZlenCode1 is null) "
			+ "and (ud1.zlen_code LIKE :pollZlenCode or :pollZlenCode1 is null) order by rp.created_at desc ";

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<ReportedPollDto> getReportPoll(final String userZlenCode, final String pollZlenCode, final Date createdAt) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("userZlenCode", "%" + userZlenCode + "%", Types.VARCHAR)
				.addValue("userZlenCode1", userZlenCode, Types.VARCHAR)
				.addValue("pollZlenCode", "%" + pollZlenCode + "%", Types.VARCHAR)
				.addValue("pollZlenCode1", pollZlenCode, Types.VARCHAR)
				.addValue("createdAt", createdAt, Types.DATE)
				.addValue("createdAt1", createdAt, Types.VARCHAR);

		return jdbcTemplate.query(reportedPoll, namedParameters, new RowMapper<ReportedPollDto>() {
			public ReportedPollDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				ReportedPollDto rpd = new ReportedPollDto();
				rpd.setUserName(rs.getString("userName"));
				rpd.setCreatedAt(rs.getDate("createdAt"));
				rpd.setUserZlenCode(rs.getString("userZlenCode"));
				rpd.setPollZlenCode(rs.getString("pollZlenCode"));
//				rpd.setPostisbanned(rs.getBoolean("postisbanned"));
//				rpd.setUserisbanned(rs.getBoolean("userisbanned"));
//				rpd.setPid(rs.getLong("pid"));
//				rpd.setUid(rs.getLong("uid"));

				return rpd;
			}
		});
	}
	
	
}
