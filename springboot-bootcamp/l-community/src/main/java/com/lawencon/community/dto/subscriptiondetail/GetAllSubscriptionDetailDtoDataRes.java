package com.lawencon.community.dto.subscriptiondetail;

import java.math.BigInteger;

public class GetAllSubscriptionDetailDtoDataRes {
	private String id;
	private String priceId;
	private String priceCode;
	private BigInteger priceNominal;
	private Integer duration;
	private String subscriptionId;
	private String SubscriptionCode;
	private String SubscriptionName;
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

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	public BigInteger getPriceNominal() {
		return priceNominal;
	}

	public void setPriceNominal(BigInteger priceNominal) {
		this.priceNominal = priceNominal;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getSubscriptionCode() {
		return SubscriptionCode;
	}

	public void setSubscriptionCode(String subscriptionCode) {
		SubscriptionCode = subscriptionCode;
	}

	public String getSubscriptionName() {
		return SubscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		SubscriptionName = subscriptionName;
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
