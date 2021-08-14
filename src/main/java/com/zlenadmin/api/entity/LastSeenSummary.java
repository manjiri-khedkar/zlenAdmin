package com.zlenadmin.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lastSeen_summary")
public class LastSeenSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	

	private Date cdate;
	private long  count;
	
	
	
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public long getCount() {
		
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
	

}
