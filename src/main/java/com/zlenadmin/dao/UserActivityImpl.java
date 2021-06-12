package com.zlenadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	
	private String SQL="select uad.id id, uad.activity as activity, uad.created_date as createdDate, uad.notify_user_device_id as notifyUserDeviceId, uad.notify_user_id as notifyUserId, ud.zlen_code as zlenCode "
			+ "from public.user_activity_details uad inner join public.user_details ud on uad.user_id = ud.user_id ";
 	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<ActivityDto> getUserActivity(final long id,final String zlenCode,final Integer activity,final Date createdDate,final String notifyUserId,final String notifyUserDeviceId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id)
				.addValue("zlenCode", zlenCode)
				.addValue("activity", activity)
				.addValue("createdDate", createdDate)
				.addValue("notifyUserId", notifyUserId)
				.addValue("notifyUserDeviceId", notifyUserDeviceId);
		
		return jdbcTemplate.query(SQL,namedParameters,new RowMapper<ActivityDto>() {
			public ActivityDto mapRow(ResultSet rs, int rownumber) throws SQLException {
				
           
				ActivityDto ad = new ActivityDto();
				
				ad.setId(rs.getLong("id"));
				ad.setActivity(rs.getInt("activity"));
				ad.setCreatedDate(rs.getDate("createdDate"));
				ad.setNotifyUserDeviceId(rs.getString("notifyUserDeviceId"));
				ad.setNotifyUserId(rs.getString("notifyUserId"));
				ad.setZlenCode(rs.getString("zlenCode"));
				
				return ad;
			}
		});
	}



}
