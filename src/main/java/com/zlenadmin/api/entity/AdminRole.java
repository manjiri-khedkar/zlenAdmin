package com.zlenadmin.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="a_adminrole")
public class AdminRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;
	
	private String role_name;
	
	private boolean active;
	
	private Date registered_date;
	
	private int registered_by;
	
	private Date modified_date;
	
	private int modified_by;
	
	public AdminRole() { }

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getRegistered_date() {
		return registered_date;
	}

	public void setRegistered_date(Date registered_date) {
		this.registered_date = registered_date;
	}

	public int getRegistered_by() {
		return registered_by;
	}

	public void setRegistered_by(int registered_by) {
		this.registered_by = registered_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public int getModified_by() {
		return modified_by;
	}

	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}

	@Override
	public String toString() {
		return "AdminRole [role_id=" + role_id + ", role_name=" + role_name + ", active=" + active
				+ ", registered_date=" + registered_date + ", registered_by=" + registered_by + ", modified_date="
				+ modified_date + ", modified_by=" + modified_by + "]";
	}
	
}
