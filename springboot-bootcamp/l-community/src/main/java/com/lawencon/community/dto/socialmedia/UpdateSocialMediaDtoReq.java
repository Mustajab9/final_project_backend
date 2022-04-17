package com.lawencon.community.dto.socialmedia;

public class UpdateSocialMediaDtoReq {
	private String id;
	private String socialMediaName;
	private String socialMediaIcon;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public String getSocialMediaIcon() {
		return socialMediaIcon;
	}

	public void setSocialMediaIcon(String socialMediaIcon) {
		this.socialMediaIcon = socialMediaIcon;
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
