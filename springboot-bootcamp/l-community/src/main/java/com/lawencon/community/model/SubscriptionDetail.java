package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "subscription_detail", 
		uniqueConstraints = 
			@UniqueConstraint(name = "detail_subs_ck", columnNames = { "price_id", "subscription_id" })
)
public class SubscriptionDetail extends BaseEntity {

	private static final long serialVersionUID = 8586014839352944158L;

	@ManyToOne
	@JoinColumn(name = "price_id")
	private PriceListMember priceId;

	@ManyToOne
	@JoinColumn(name = "subscription_id")
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
