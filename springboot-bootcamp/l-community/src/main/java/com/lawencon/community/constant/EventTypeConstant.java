package com.lawencon.community.constant;

public enum EventTypeConstant {
	COURSE("Course", "EY02", "PR02"), EVENT("Event", "EY01","PR01");
	
	private String detail;
	private String code;
	private String codePrice;
	
	private EventTypeConstant(String detail, String code, String codePrice){
		this.detail = detail;
		this.codePrice = code;
		this.codePrice = codePrice;
	}

	public String getDetail() {
		return detail;
	}
	
	public String getCode() {
		return code;
	}

	public String getCodePrice() {
		return codePrice;
	}
}
