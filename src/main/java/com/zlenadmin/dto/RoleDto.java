package com.zlenadmin.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class RoleDto {

	 private long id;
	 
	 @NotEmpty
	 private String roleName;
	 
	 @NotEmpty
	 private String roleDescription;
	 
	 @NotEmpty
	 private String roleStatus;
	 
	 public long getId() {
		return id;
	 }
	 public void setId(long id) {
		this.id = id;
	 }
	 public String getRoleName() {
		return roleName;
	 }
	 public void setRoleName(String roleName) {
		this.roleName = roleName;
	 }
	 public String getRoleDescription() {
		return roleDescription;
	 }
	 public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	 }
	 public String getRoleStatus() {
		return roleStatus;
	 }
	 public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}	
		 
}
