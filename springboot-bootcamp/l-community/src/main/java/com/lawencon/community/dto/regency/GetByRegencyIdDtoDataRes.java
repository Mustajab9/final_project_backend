package com.lawencon.community.dto.regency;

public class GetByRegencyIdDtoDataRes {
	private String id;
	private String regancyCode;
	private String regancyName;
	private String provinceId;
	private String provinceCode;
	private String provinceName;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegancyCode() {
		return regancyCode;
	}

	public void setRegancyCode(String regancyCode) {
		this.regancyCode = regancyCode;
	}

	public String getRegancyName() {
		return regancyName;
	}

	public void setRegancyName(String regancyName) {
		this.regancyName = regancyName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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
