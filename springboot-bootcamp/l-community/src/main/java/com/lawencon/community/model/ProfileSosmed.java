package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "profile_sosmed", uniqueConstraints = @UniqueConstraint(name="profile_sosmed_ck", columnNames = {"profile_id", "social_media_id"}))
public class ProfileSosmed extends BaseEntity {
	
	private static final long serialVersionUID = 7572139183219161907L;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profiles profileId;
	
	@ManyToOne
	@JoinColumn(name = "social_media_id")
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
