package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.zlenadmin.dto.ActivityDto;

@Repository
public class UserActivityImpl implements UserActivity {
	
	
//	private String SQL="select uad.id id, uad.activity as activity, uad.created_date as createdDate, uad.notify_user_device_id as notifyUserDeviceId, uad.notify_user_id as notifyUserId, ud.zlen_code as zlenCode "
//			+ "from public.user_activity_details uad inner join public.user_details ud on uad.user_id = ud.user_id "
//			+ "where (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) ";
//	+ "where (uad.created_date = :createdDate or :createdDate1 is null)  "
	
//	private String SQL="select uad.id id, uad.activity as activity, uad.created_date as createdDate, uad.notify_user_device_id as notifyUserDeviceId, uad.notify_user_id as notifyUserId, ud.zlen_code as zlenCode , ud1.zlen_code as notify_zlenCode "
//	+ "from public.user_activity_details uad inner join public.user_details ud on uad.user_id = ud.user_id "
//	+ "inner join public.user_details ud1 on uad.notify_user_id = ud1.user_id  "
//	+ "where (uad.created_date = :createdDate or :createdDate1 is null)  "
//	+ "and (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) ";
	
	
	private String SQL="select uad.id id, uad.activity as activity, uad.created_date as createdDate, uad.notify_user_device_id as notifyUserDeviceId, uad.notify_user_id as notifyUserId, ud.zlen_code as zlenCode , ud1.zlen_code as notify_zlenCode "
			+ "from public.user_activity_details uad inner join public.user_details ud on uad.user_id = ud.user_id "
			+ "inner join public.user_details ud1 on uad.notify_user_id = ud1.user_id  "
			+ "where (cast(uad.created_date as date) = :createdDate or :createdDate1 is null)  "
			+ "and (ud.zlen_code LIKE :zlenCode or :zlenCode1 is null) ";
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<ActivityDto> getUserActivity(final String zlenCode, final Date createdDate) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("zlenCode", "%"+zlenCode+"%",Types.VARCHAR)
				.addValue("zlenCode1", zlenCode,Types.VARCHAR)
				.addValue("createdDate1", createdDate,Types.VARCHAR)
				.addValue("createdDate", createdDate,Types.DATE);

		return jdbcTemplate.query(SQL,namedParameters,new RowMapper<ActivityDto>() {
			public ActivityDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				
           
				ActivityDto ad = new ActivityDto();
				
				ad.setId(rs.getLong("id"));
				ad.setActivity(rs.getInt("activity"));
				ad.setCreatedDate(rs.getDate("createdDate"));
				ad.setNotifyUserDeviceId(rs.getString("notifyUserDeviceId"));
				ad.setZlenCode(rs.getString("zlenCode"));
				ad.setNotifyUserId(rs.getString("notify_zlenCode"));
				
				return ad;
			}
		});
	}

}
