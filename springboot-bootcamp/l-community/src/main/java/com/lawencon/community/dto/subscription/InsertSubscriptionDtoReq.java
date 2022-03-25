package com.lawencon.community.dto.subscription;

public class InsertSubscriptionDtoReq {
	private String profileId;
	private String priceId;

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
}
