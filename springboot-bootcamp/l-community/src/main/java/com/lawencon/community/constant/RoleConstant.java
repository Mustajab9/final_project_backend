package com.lawencon.community.constant;

public enum RoleConstant {
	ADMIN("Admin", "R01"), MEMBER("Member", "R02"), USER("User", "R03");
	
	private String detail;
	private String code;
	
	private RoleConstant(String detail, String code){
		this.detail = detail;
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public String getCode() {
		return code;
	}
}
