package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class SubscriptionDetail extends BaseEntity {
	
	private static final long serialVersionUID = 8586014839352944158L;
	private PriceListMember priceId;
	private Subscription subscriptionId;
	
	public PriceListMember getPriceId() {
		return priceId;
	}
	public void setPriceId(PriceListMember priceId) {
		this.priceId = priceId;
	}
	public Subscription getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Subscription subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	
}
