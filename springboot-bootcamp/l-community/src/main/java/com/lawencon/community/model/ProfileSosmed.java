package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class ProfileSosmed extends BaseEntity {
	
	private static final long serialVersionUID = 7572139183219161907L;
	private Profiles profileId;
	private SocialMedia socialMediaId;
	
	public Profiles getProfileId() {
		return profileId;
	}
	public void setProfileId(Profiles profileId) {
		this.profileId = profileId;
	}
	public SocialMedia getSocialMediaId() {
		return socialMediaId;
	}
	public void setSocialMediaId(SocialMedia socialMediaId) {
		this.socialMediaId = socialMediaId;
	}
	
	
}
