package com.lawencon.community.dto.subscriptiondetail;

public class InsertSubscriptionDetailDtoReq {
	private String subscriptionId;
	private String priceId;

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
}
