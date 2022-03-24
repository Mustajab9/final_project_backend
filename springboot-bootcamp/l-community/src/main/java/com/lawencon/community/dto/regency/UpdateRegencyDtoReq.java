package com.lawencon.community.dto.regency;

public class UpdateRegencyDtoReq {
	private String id;
	private String regencyName;
	private Integer version;
	private Boolean isActive;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegencyName() {
		return regencyName;
	}
	public void setRegencyName(String regencyName) {
		this.regencyName = regencyName;
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
