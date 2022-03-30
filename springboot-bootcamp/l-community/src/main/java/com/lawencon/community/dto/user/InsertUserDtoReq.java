package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertUserDtoReq {
	@NotEmpty(message = "Username is Empty")
	@Size(max = 30, min = 5, message = "Username min 5 Character and max 30 Character")
	private String username;

	@NotEmpty(message = "Password is Empty")
	@Size(max = 225, min = 5, message = "Password min 5 Character and max 225 Character")
	private String password;

	@NotNull(message = "Role Id Is Empty")
	private String roleId;

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
