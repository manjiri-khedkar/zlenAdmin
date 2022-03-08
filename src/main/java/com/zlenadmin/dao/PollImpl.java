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
import com.zlenadmin.dto.PollOptionDto;
import com.zlenadmin.dto.PollVoteDto;
import com.zlenadmin.dto.StoriesDto;

@Repository
public class PollImpl implements Poll {

	String Poll = "select po.id as id, po.content as content, po.created_at as createdAt, po.is_completed as iscompleted, po.is_zlen_world as zlenWorld, "
			+ "po.is_banned as pollisbanned, ud.id as uid, ud.user_mobile as userMobile, ud.zlen_code as zlenCode, ud.user_name as userName, ud.is_banned as userisbanned "
			+ "from public.poll po inner join public.user_details ud on po.user_id = ud.user_id "
			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) and (po.is_zlen_world = :zlenWorld or :zlenWorld1 is null) "
			+ "and (cast(po.created_at as date) = :createdAt or :createdAt1 is null)"
			+ "and (ud.user_mobile LIKE :userMobile or :userMobile1 is null) "
			+ "group by po.id, po.is_banned, ud.zlen_code, ud.user_name, ud.id, ud.is_banned order by po.created_at desc";

	String pollOption = "select po.serial_no as serialNo,po.content as contents, po.created_at as createdAt from public.poll_option po where po.poll_id = :pollid";

	String pollResponse = " select pv.created_at as createdAt, ud.user_name as userName, ud.zlen_code as zlenCode, "
			+ "po.content as contents from public.poll_vote pv "
			+ "inner join public.poll_option po on po.id = pv.option_id  "
			+ "inner join public.user_details ud on ud.user_id = pv.voter_id  " 
			+ "where pv.poll_id= :pollid ";

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<PollDto> getPoll(final String zlenCode, final Date createdAt, final boolean zlenWorld,
			final String userMobile) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%" + zlenCode + "%", Types.VARCHAR)
				.addValue("zlenCode1", zlenCode, Types.VARCHAR).addValue("createdAt", createdAt, Types.DATE)
				.addValue("createdAt1", createdAt, Types.VARCHAR).addValue("zlenWorld", zlenWorld, Types.BOOLEAN)
				.addValue("zlenWorld1", zlenWorld, Types.BOOLEAN)
				.addValue("userMobile", "%" + userMobile + "%", Types.VARCHAR)
				.addValue("userMobile1", userMobile, Types.VARCHAR);

		return jdbcTemplate.query(Poll, namedParameters, new RowMapper<PollDto>() {
			public PollDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				PollDto pd = new PollDto();
				pd.setId(rs.getInt("id"));
				pd.setZlenCode(rs.getString("zlenCode"));
				pd.setUserName(rs.getString("userName"));
				pd.setContent(rs.getString("content"));
				pd.setCreatedAt(rs.getDate("createdAt"));
				pd.setZlenWorld(rs.getBoolean("zlenWorld"));
				pd.setIscompleted(rs.getBoolean("iscompleted"));
				pd.setPollisbanned(rs.getBoolean("pollisbanned"));
				pd.setUserisbanned(rs.getBoolean("userisbanned"));
				pd.setUid(rs.getLong("uid"));
				pd.setUserMobile(rs.getString("userMobile"));
				// ud.setIsActive(rs.getString("isActive"));
				return pd;
			}
		});
	}

	@Override
	public List<PollOptionDto> getPollOption(final long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("pollid", id);

		return jdbcTemplate.query(pollOption, namedParameters, new RowMapper<PollOptionDto>() {
			public PollOptionDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				PollOptionDto pod = new PollOptionDto();
				pod.setContents(rs.getString("contents"));
				pod.setCreatedAt(rs.getDate("createdAt"));
				// ud.setIsActive(rs.getString("isActive"));
				return pod;
			}
		});
	}
	
	@Override
	public List<PollVoteDto> getPollVote(final long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("pollid", id);

		return jdbcTemplate.query(pollResponse, namedParameters, new RowMapper<PollVoteDto>() {
			public PollVoteDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				PollVoteDto pvd = new PollVoteDto();
				pvd.setContents(rs.getString("contents"));
				pvd.setCreatedAt(rs.getDate("createdAt"));
				pvd.setUserName(rs.getString("userName"));
				pvd.setZlenCode(rs.getString("zlenCode"));
				
				// ud.setIsActive(rs.getString("isActive"));
				return pvd;
			}
		});
	}

}
