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
import com.zlenadmin.dto.StoriesDto;

@Repository
public class PollImpl implements Poll {

	String Poll = "select po.id as id, po.content as content, po.created_at as createdAt, po.is_completed as iscompleted, po.is_zlen_world as zlenWorld, "
			+ "ud.zlen_code as zlenCode, ud.user_name as userName "
			+ "from public.poll po inner join public.user_details ud on po.user_id = ud.user_id "
			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) "
			+ "and (po.is_zlen_world = :zlenWorld or :zlenWorld1 is null) "
			+ "and (cast(po.created_at as date) = :createdAt or :createdAt1 is null) "
			+ "group by po.id,ud.zlen_code,ud.user_name order by po.created_at desc";
	
	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<PollDto> getPoll(final String zlenCode, final Date createdAt, final boolean zlenWorld) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%" + zlenCode + "%", Types.VARCHAR)
				.addValue("zlenCode1", zlenCode, Types.VARCHAR)
				.addValue("createdAt", createdAt, Types.DATE)
				.addValue("createdAt1", createdAt, Types.VARCHAR)
				.addValue("zlenWorld", zlenWorld,Types.BOOLEAN)
				.addValue("zlenWorld1",zlenWorld,Types.BOOLEAN);

		return jdbcTemplate.query(Poll, namedParameters, new RowMapper<PollDto>() {
			public PollDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				PollDto pd = new PollDto();
				pd.setZlenCode(rs.getString("zlenCode"));
				pd.setUserName(rs.getString("userName"));
				pd.setContent(rs.getString("content"));
				pd.setCreatedAt(rs.getDate("createdAt"));
				pd.setZlenWorld(rs.getBoolean("zlenWorld"));
				System.out.println("isCompleted ==:" + rs.getBoolean("iscompleted"));
				pd.setIscompleted(rs.getBoolean("iscompleted"));
				//ud.setIsActive(rs.getString("isActive"));
				return pd;
			}
		});
	}
	
	
}
