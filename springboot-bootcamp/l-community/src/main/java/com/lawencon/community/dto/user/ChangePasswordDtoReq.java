package com.lawencon.community.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordDtoReq {
	@NotNull(message = "Id is Empty")
	private String id;

	@NotEmpty(message = "Email is Empty")
	@Size(max = 50, min = 5, message = "Email min 5 Character and max 30 Character")
	private String email;

	@NotEmpty(message = "Password is Empty")
	@Size(min = 5, message = "Password min 5 Character")
	private String password;

	@NotEmpty(message = "Password is Empty")
	@Size(min = 5, message = "Password min 5 Character")
	private String NewPassword;

	@NotNull(message = "Role Id is Empty")
	private String roleId;

	@NotNull(message = "Version is Empty")
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getNewPassword() {
		return NewPassword;
	}

	public void setNewPassword(String newPassword) {
		NewPassword = newPassword;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
