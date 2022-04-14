package com.lawencon.community.constant;

public enum ThreadTypeConstant {
	THREAD("Thread", "TY02"), POLLING("Polling", "TY01"), ARTICLE("Article", "TY03");
	
	private String detail;
	private String code;
	
	private ThreadTypeConstant(String detail, String code){
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
