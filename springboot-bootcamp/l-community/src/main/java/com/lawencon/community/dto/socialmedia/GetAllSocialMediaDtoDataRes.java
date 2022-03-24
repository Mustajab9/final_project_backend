package com.lawencon.community.dto.socialmedia;

public class GetAllSocialMediaDtoDataRes {
	private String id;
	private String socialMediaCode;
	private String socialMediaName;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSocialMediaCode() {
		return socialMediaCode;
	}

	public void setSocialMediaCode(String socialMediaCode) {
		this.socialMediaCode = socialMediaCode;
	}

	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
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
