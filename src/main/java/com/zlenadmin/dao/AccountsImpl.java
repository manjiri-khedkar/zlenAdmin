package com.zlenadmin.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.zlenadmin.dto.AccountsDto;

@Repository
public  class AccountsImpl implements Accounts {
	
	private String SQL = " select to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date as cdate, count(*) as count  from public.accounts\r\n"
			+ "group by to_timestamp((data -> 'devices'-> 0 -> 'lastSeen')::text::numeric/1000)::date\r\n"
			+ "order by cdate";
	
	
	
	    @Autowired
	    @Qualifier("admin-jdbc")
	    private NamedParameterJdbcTemplate jdbcTemplate;


	@Override
	public List<AccountsDto> getGraphQuery31() {
		SqlParameterSource namedParameters = new MapSqlParameterSource();		
				
		
		 return jdbcTemplate.query(SQL,namedParameters,new RowMapper<AccountsDto>() {
			public AccountsDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				
				AccountsDto acc = new AccountsDto();
				acc.setCdate(rs.getDate("cdate"));
				acc.setCount(rs.getInt("count"));
				return acc;
			}
		    });
	}







	





}
