package com.lawencon.community.dto.profiles;

public class InsertProfilesDtoReq {
	private String profileName;
	private String profileCompany;
	private String userId;
	private String industryId;
	private String positionId;
	private String profilePhone;

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfileCompany() {
		return profileCompany;
	}

	public void setProfileCompany(String profileCompany) {
		this.profileCompany = profileCompany;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getProfilePhone() {
		return profilePhone;
	}

	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}
}
