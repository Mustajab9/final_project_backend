package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertUserDtoReq {
	@NotEmpty(message = "Username is Empty")
	private String username;

	@NotEmpty(message = "Password is Empty")
	@Size(max = 225, min = 5, message = "Password min 5 Character and max 225 Character")
	private String password;

	@NotNull(message = "Role Code Is Empty")
	private String roleCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
