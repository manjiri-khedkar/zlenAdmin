package com.zlenadmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlenadmin.api.entity.Banner;

public class BannerDao {
	
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<Banner> selectBanner(int id) {
	ArrayList<Banner> result = new ArrayList<Banner>();

	Connection conn= null;
	String SQL_SELECT = "select * from public.banner where banner_id= :id";

	// auto close connection and preparedStatement
	try {
	conn = dataSource.getConnection();
	PreparedStatement ps = conn.prepareStatement(SQL_SELECT);

		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Banner banner = new Banner();
		while (rs.next()) {

			int banner_id = rs.getInt("id");
			Date start_date = rs.getDate("start_date");
			Date end_date = rs.getDate("end_date");
			Integer frequency = rs.getInt("frequency");
			String content = rs.getString("content");
			

			// System.out.println(Roll_no + " " + S_name + " " + Class + " " + Physhic + " "
			// + Chemistry + " "
			// + Biology + " " + Maths + " " + Total + " " + Percentage);
			banner.setBanner_id(banner_id);
			banner.setStart_date(start_date);
			banner.setEnd_date(end_date);
			banner.setFrequency(frequency);
			banner.setContent(content);
			

			result.add(banner);

		}
		// result.forEach(x -> System.out.println(x));

	} catch (SQLException e) {
		System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
	
	
	public static void main(String[] asd, Banner banner, int id) {

		BannerDao dao = new BannerDao();
		dao.selectBanner(id);

	}
	
	

}
