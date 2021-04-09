package mp.procurement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_activity",schema="main")
public class UserActivity implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	public UserActivity(){
		
	}
	
	public UserActivity(int party_id, String activity, long visit_time){
		this.party_id= party_id;
		this.activity = activity;
		this.visit_time = visit_time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParty_id() {
		return party_id;
	}
	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}
	
	public long getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(long visit_time) {
		this.visit_time = visit_time;
	}
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getOther_values() {
		return other_values;
	}

	public void setOther_values(String other_values) {
		this.other_values = other_values;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int party_id;
	private String activity;
	private String other_values;
	private long visit_time;
	

}
