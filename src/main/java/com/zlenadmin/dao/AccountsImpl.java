package com.zlenadmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.api.entity.LastSeenSummary;
import com.zlenadmin.dto.AccountsDto;

@Repository
public class AccountsImpl implements Accounts {

	private String SQL = " select to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate, count(*) as count  from public.accounts\r\n"
			+ "group by to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date\r\n"
			+ "order by cdate";

	private String lastSql = "select count(data -> 'devices'-> 0 ->'id') as count , "
			+ "Max(to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date) as cdate "
			+ "from public.accounts "
			+ "where to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date <= "
			+ "to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date";

	@Autowired
	@Qualifier("admin-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<AccountsDto> getGraphQuery31() {
		SqlParameterSource namedParameters = new MapSqlParameterSource();

		return jdbcTemplate.query(SQL, namedParameters, new RowMapper<AccountsDto>() {
			public AccountsDto mapRow(ResultSet rs, int rownumber) throws SQLException {

				AccountsDto acc = new AccountsDto();
				acc.setCdate(rs.getDate("cdate"));
				acc.setCount(rs.getInt("count"));
				return acc;
			}
		});
	}

	@Override
	public List<LastSeenSummary> getCreate(Date daysAgo) {
		SqlParameterSource namedParameters = new MapSqlParameterSource();

		return jdbcTemplate.query(lastSql, namedParameters, new RowMapper<LastSeenSummary>() {
			public LastSeenSummary mapRow(ResultSet rs, int rownumber) throws SQLException {

				LastSeenSummary acc = new LastSeenSummary();
				acc.setCdate(rs.getDate("cdate"));
				acc.setCount(rs.getInt("count"));
				return acc;
			}
		});

	}

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(LastSeenSummary lastSeenSummary) {

		String sql = "INSERT INTO last_seen_summary " + "(cdate, count) VALUES (?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, (java.sql.Date) lastSeenSummary.getCdate());
			ps.setLong(2, lastSeenSummary.getCount());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}
