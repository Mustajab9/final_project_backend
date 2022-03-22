package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class SocialMedia extends BaseEntity {
	
	private static final long serialVersionUID = -800808443284028144L;
	private String socialMediaName;
	private String socialMediaCode;
	
	public String getSocialMediaName() {
		return socialMediaName;
	}
	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}
	public String getSocialMediaCode() {
		return socialMediaCode;
	}
	public void setSocialMediaCode(String socialMediaCode) {
		this.socialMediaCode = socialMediaCode;
	}
	
	
}
