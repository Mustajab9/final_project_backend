package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Industry extends BaseEntity {
	
	private static final long serialVersionUID = 7115665047388653760L;
	private String industryName;
	private String industryCode;
	
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	
	
}
