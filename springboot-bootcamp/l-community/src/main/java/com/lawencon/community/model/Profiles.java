package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Profiles extends BaseEntity {
	
	private static final long serialVersionUID = -6298603040813065280L;
	private String profileName;
	private String profileCode;
	private String profileCompany;
	private Attachment profileImage;
	private User userId;
	private Industry industryId;
	private Position positionId;
	private Province provinceId;
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileCode() {
		return profileCode;
	}
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}
	public String getProfileCompany() {
		return profileCompany;
	}
	public void setProfileCompany(String profileCompany) {
		this.profileCompany = profileCompany;
	}
	public Attachment getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Attachment profileImage) {
		this.profileImage = profileImage;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Industry getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Industry industryId) {
		this.industryId = industryId;
	}
	public Position getPositionId() {
		return positionId;
	}
	public void setPositionId(Position positionId) {
		this.positionId = positionId;
	}
	public Province getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Province provinceId) {
		this.provinceId = provinceId;
	}
	
	
	
}
