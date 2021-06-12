package com.zlenadmin.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="a_admindata")
public class AdminData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int admin_id;
	
	private int adminrole_id;
	
	private int adminref_id;
	
	private String admin_name;
	
	private String admin_email;
	
	private String admin_password;
	
	private String admin_contactno;
	
	private boolean active;
	
	private Date registered_date;
	
	private int registered_by;
	
	private Date modified_date;
	
	private int modified_by;

	public AdminData() { }
	
	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getAdminrole_id() {
		return adminrole_id;
	}

	public void setAdminrole_id(int adminrole_id) {
		this.adminrole_id = adminrole_id;
	}

	public int getAdminref_id() {
		return adminref_id;
	}

	public void setAdminref_id(int adminref_id) {
		this.adminref_id = adminref_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_contactno() {
		return admin_contactno;
	}

	public void setAdmin_contactno(String admin_contactno) {
		this.admin_contactno = admin_contactno;
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
		return "AdminData [admin_id=" + admin_id + ", adminrole_id=" + adminrole_id + ", adminref_id=" + adminref_id
				+ ", admin_name=" + admin_name + ", admin_email=" + admin_email + ", admin_password=" + admin_password
				+ ", admin_contactno=" + admin_contactno + ", active=" + active + ", registered_date=" + registered_date
				+ ", registered_by=" + registered_by + ", modified_date=" + modified_date + ", modified_by="
				+ modified_by + "]";
	}
	
}
