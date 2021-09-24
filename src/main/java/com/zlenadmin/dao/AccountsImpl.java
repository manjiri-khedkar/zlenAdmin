package com.zlenadmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.PendingRegistrationDto;
import com.zlenadmin.dto.RegisterPendingDto;

@Repository
public class AccountsImpl implements Accounts {

	private String SQL = " select to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate, count(*) as count  from public.accounts\r\n"
			+ "group by to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date\r\n"
			+ "order by cdate";

	private String SELECT_LASTSEEN_SUMMARY = " select  cdate, count from last_seen_summary \r\n"
			+ "where cdate::date >= :varDate::date "
			+ "order by cdate";
	
	private String pending_Registration ="select(data ->'devices'-> 0 ->'name') as name , \r\n" + 
			"to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate ,\r\n" + 
			"pa.number as number\r\n" + 
			"from public.accounts acc\r\n" + 
			"inner join public.pending_accounts pa on pa.number = acc.number\r\n" + 
			"where pa.push_code is null "
			+ "and  ((to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date) > :varDate or :varDate1 is null) ";

//	private String pending_Registration = "select(data ->'devices'-> 0 ->'name') as name , \r\n"
//			+ "to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate ,\r\n"
//			+ "pa.number as number\r\n" + "from public.accounts acc\r\n"
//			+ "inner join public.pending_accounts pa on pa.number = acc.number\r\n" + "where pa.push_code is null";

	private String inActive="select(data ->'devices'-> 0 ->'name') as name ,acc.number as number, \r\n"
			+"to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate \r\n"
			+"from public.accounts acc \r\n"
			+"where to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date <= :varDate::date";

	private String lastSql = "select count(data -> 'devices'-> 0 ->'id') as count "
			//+ "to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate "
			+ "from public.accounts "
			+ "where to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date = :varDate::date";

	private String lastSeenSummary = "select count(data -> 'devices'-> 0 ->'id') as count , "
			+ "to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate "
			+ "from public.accounts "
			+ "group by to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date ";

	private String INSERT_SQL = "INSERT INTO last_seen_summary " + "(cdate, count) VALUES (?, ?)";
	
	private String PENDING_USER= "	select ud.user_name as name,otp.number as number,otp.created_at as date\r\n"
			+ "	from public.otp_verification otp \r\n"
			+ "	left outer join public.user_details ud on otp.number = replace(ud.user_mobile,' ', '') \r\n"
			+ "	where user_id is null "
			+ "and  (otp.created_at > :varDate or :varDate1 is null) " 
			+" order by date ";

	@Autowired
	@Qualifier("admin-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate zlenjdbcTemplate;

	@Override
	public List<AccountsDto> getGraphQuery31(Date daysAgo) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("varDate", daysAgo,Types.DATE);

		return zlenjdbcTemplate.query(SELECT_LASTSEEN_SUMMARY, namedParameters, new RowMapper<AccountsDto>() {
			public AccountsDto mapRow(ResultSet rs, int rownumber) throws SQLException {

				AccountsDto acc = new AccountsDto();
				acc.setCdate(rs.getDate("cdate"));
				acc.setCount(rs.getInt("count"));
				return acc;
			}
		});
	}
	
	@Override
	public List<RegisterPendingDto> getPendingRegistration(Date date) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("varDate", date,Types.DATE)
				.addValue("varDate1", date,Types.VARCHAR);
		
		return zlenjdbcTemplate.query(PENDING_USER, namedParameters, new RowMapper<RegisterPendingDto>() {
			public RegisterPendingDto mapRow(ResultSet rs, int rownumber) throws SQLException {

				RegisterPendingDto acc = new RegisterPendingDto();
				acc.setName(rs.getString("name"));
				acc.setNumber(rs.getString("number"));
				acc.setDate(rs.getDate("date"));
				return acc;
			}
		});
	}

	

	

	@Override
	public LastSeenSummary getCreate(Date daysAgo) {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("varDate", daysAgo,Types.DATE);

		
		
		return jdbcTemplate.queryForObject(lastSql, namedParameters, new RowMapper<LastSeenSummary>() {
			public LastSeenSummary mapRow(ResultSet rs, int rownumber) throws SQLException {
				LastSeenSummary acc = new LastSeenSummary();
				// acc.setCdate(daysAgo);
				acc.setCount(rs.getInt("count"));
				return acc;
			}
		});

	}

	@Override
	public List<LastSeenSummary> getSummary() {
		SqlParameterSource namedParameters = new MapSqlParameterSource();

		return jdbcTemplate.query(lastSeenSummary, namedParameters, new RowMapper<LastSeenSummary>() {
			public LastSeenSummary mapRow(ResultSet rs, int rownumber) throws SQLException {
				LastSeenSummary acc = new LastSeenSummary();
				acc.setCdate(new Date(rs.getDate("cdate").getTime()));
				acc.setCount(rs.getInt("count"));
				return acc;
			}
		});

	}

	@Override
	public List<PendingRegistrationDto> getPendingRegistrationDto(Date date) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("varDate1", date, Types.VARCHAR)
				.addValue("varDate", date, Types.DATE);
		return jdbcTemplate.query(pending_Registration, namedParameters, new RowMapper<PendingRegistrationDto>() {
			public PendingRegistrationDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				PendingRegistrationDto prd = new PendingRegistrationDto();
				prd.setName(rs.getString("name"));
				prd.setCdate(new Date(rs.getDate("cdate").getTime()));
				prd.setNumber(rs.getString("number"));
				return prd;
			}
		});

	}
	
	@Override
	public List<InactiveDto> getInactiveDto(Date daysAgo) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("varDate", daysAgo, Types.DATE);
			

		return jdbcTemplate.query(inActive, namedParameters, new RowMapper<InactiveDto>() {
			public InactiveDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				InactiveDto inActive = new InactiveDto();
				inActive.setName(rs.getString("name"));
				inActive.setCdate(new Date(rs.getDate("cdate").getTime()));
				inActive.setNumber(rs.getString("number"));
				return inActive;
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

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
			ps.setDate(1, new java.sql.Date(lastSeenSummary.getCdate().getTime()));
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
