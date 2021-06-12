package com.zlenadmin.dto;

import java.util.List;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.zlenadmin.constraint.FieldMatch;
import com.zlenadmin.model.Role;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPass", message = "The password fields must match")  
})
public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty (message="Please enter valid 10 digits mobile no.")
	private String mobileNo;
	
	@NotEmpty
	@Email
	@Column(name = "email", unique=true)
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPass;
	
	
	private List<Role> roles; 
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
}
