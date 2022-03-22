package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 8361745271697416724L;
	private String roleName;
	private String roleCode;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
}
