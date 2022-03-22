package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Province  extends BaseEntity {
	
	private static final long serialVersionUID = -8147464616589620630L;
	private String provinceName;
	private String provinceCode;
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	
}
