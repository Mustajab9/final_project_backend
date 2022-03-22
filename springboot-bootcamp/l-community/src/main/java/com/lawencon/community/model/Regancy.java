package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Regancy extends BaseEntity {
	
	private static final long serialVersionUID = -7409702483893214502L;
	private String regancyName;
	private String regancyCode;
	private Province provinceId;
	
	public String getRegancyName() {
		return regancyName;
	}
	public void setRegancyName(String regancyName) {
		this.regancyName = regancyName;
	}
	public String getRegancyCode() {
		return regancyCode;
	}
	public void setRegancyCode(String regancyCode) {
		this.regancyCode = regancyCode;
	}
	public Province getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Province provinceId) {
		this.provinceId = provinceId;
	}
	
	
}
