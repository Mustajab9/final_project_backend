package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class User extends BaseEntity {
	
	private static final long serialVersionUID = 9135646009105023631L;
	private String email;
	private String password;
	private String verificationCode;
	private Role roleId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public Role getRoleId() {
		return roleId;
	}
	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	
	
}
