package com.lawencon.community.dto.subscription;

import java.sql.Date;

public class GetByUserIdDtoDataRes {
	private String id;
	private String SubscriptionCode;
	private Date subscriptionDuration;
	private Boolean isApprove;
	private String profileId;
	private String profileCode;
	private String profileName;
	private String profileCompany;
	private String profilePortalCode;
	private String userId;
	private String email;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubscriptionCode() {
		return SubscriptionCode;
	}

	public void setSubscriptionCode(String subscriptionCode) {
		SubscriptionCode = subscriptionCode;
	}

	public Date getsubscriptionDuration() {
		return subscriptionDuration;
	}

	public void setsubscriptionDuration(Date subscriptionDuration) {
		this.subscriptionDuration = subscriptionDuration;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

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

	public String getProfilePortalCode() {
		return profilePortalCode;
	}

	public void setProfilePortalCode(String profilePortalCode) {
		this.profilePortalCode = profilePortalCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
