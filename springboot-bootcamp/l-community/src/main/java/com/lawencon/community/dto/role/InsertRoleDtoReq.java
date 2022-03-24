package com.lawencon.community.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertRoleDtoReq {
	@NotEmpty(message = "Role Code is Empty")
	@Size(max = 5, min = 3, message = "Code min 3 Character and max 5 Character")
	private String roleCode;
	
	@NotEmpty(message = "Role Name is Empty")
	@Size(max = 30, min = 5, message = "Name min 5 Character and max 30 Character")
	private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
