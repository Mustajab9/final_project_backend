package com.lawencon.community.constant;

public enum CommonConstant {
	SUCCESS("Success"), ACTION_ADD("Add"), HAS_BEEN_ADDED("Has Been Added"), ACTION_EDIT("Edit"),
	HAS_BEEN_UPDATED("Has Been Updated"), ACTION_DELETE("Delete"), HAS_BEEN_DELETED("Has Been Deleted"),
	INVALID_LOGIN("Invalid Username or Password"), INVALID_TOKEN("Invalid Token"), TOKEN_EXIRED("Token Expired");
	
	private String detail;
	
	private CommonConstant(String detail) {
		this.detail = detail;
	}
	
	public String getDetail() {
		return this.detail;
	}
}
