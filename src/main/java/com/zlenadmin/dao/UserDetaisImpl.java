package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.UserDetailsDto;
import com.zlenadmin.dto.UserUpdateDto;
import com.zlenadmin.dto.UsersDetailDto;

@Repository
public class UserDetaisImpl implements UserDetails {

	String userDetailsSql = "SELECT u.id as id, u.user_name as userName, u.user_mobile as userMobile, u.zlen_code as zlenCode,"
			+ "u.device_type as deviceType, u.age as age, u.gender as gender, u.created_on as createdOn , "
			+ "(Select count(ufd.friend_user_id) as frnds_count from user_friends_details ufd where ufd.friend_user_id = u.user_id)"
			+ " FROM user_details u WHERE (LOWER(u.user_name) LIKE  :userName or :userName1 is null ) "
			+ "and ( u.user_mobile LIKE :userMobile or :userMobile1 is null ) and ( LOWER(u.zlen_code)  LIKE  :zlenCode or :zlenCode1 is null ) "
			+ "and (u.device_type LIKE :deviceType or :deviceType1 is null ) and (u.age between :age and :age1 or :age is null) "
			+ "and (LOWER(u.gender) =  :gender or :gender1 is null ) and (Date(u.created_on) = :createdOn  or cast(:createdOn1 as date) is null) "
			+ "order by u.created_on desc";

	@Autowired
	@Qualifier("zlen-jdbc")
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<UsersDetailDto> getUserDetails(final String userName, final String userMobile, final String zlenCode,
			final String deviceType, final @Temporal Date createdOn, final String gender, final Integer age,
			final Integer age1) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("userName", "%" + userName + "%", Types.VARCHAR)
				.addValue("userName1", userName, Types.VARCHAR)
				.addValue("userMobile", "%" + userMobile + "%", Types.VARCHAR)
				.addValue("userMobile1", userMobile, Types.VARCHAR)
				.addValue("zlenCode", "%" + zlenCode + "%", Types.VARCHAR)
				.addValue("zlenCode1", zlenCode, Types.VARCHAR)
				.addValue("deviceType", "%" + deviceType + "%", Types.VARCHAR)
				.addValue("deviceType1", deviceType, Types.VARCHAR)
				.addValue("createdOn", createdOn, Types.DATE)
				.addValue("createdOn1", createdOn, Types.DATE)
				.addValue("age", age, Types.INTEGER)
				.addValue("age1", age1, Types.INTEGER)
				.addValue("gender", gender, Types.VARCHAR)
				.addValue("gender1", gender, Types.VARCHAR);

		return jdbcTemplate.query(userDetailsSql, namedParameters, new RowMapper<UsersDetailDto>() {
			public UsersDetailDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				UsersDetailDto ud = new UsersDetailDto();
				ud.setId(rs.getLong("id"));
				ud.setUserName(rs.getString("userName"));
				System.out.println("name == :" + rs.getString("userName"));
				ud.setUserMobile(rs.getString("userMobile"));
				ud.setZlenCode(rs.getString("zlenCode"));
				ud.setDeviceType(rs.getString("deviceType"));
				ud.setAge(rs.getInt("age"));
				ud.setCreatedOn(rs.getDate("createdOn"));
				ud.setGender(rs.getString("gender"));
				ud.setFrnds_count(rs.getString("frnds_count"));
				return ud;
			}
		});

	}

}
