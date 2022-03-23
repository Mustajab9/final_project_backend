package com.lawencon.community.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "subscriptions", uniqueConstraints = @UniqueConstraint(name="subs_bk", columnNames = "subscription_code"))
public class Subscription extends BaseEntity {
	
	private static final long serialVersionUID = -7204808085626448204L;
	
	@Column(name = "subscription_code", length=5)
	private String subscriptionCode;
	
	@Column(name = "subscription_duration")
	private Date subscriptionDuration;
	
	@Column(name = "is_approve")
	private Boolean isApprove;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profiles profileId;
	
	public String getSubscriptionCode() {
		return subscriptionCode;
	}
	public void setSubscriptionCode(String subscriptionCode) {
		this.subscriptionCode = subscriptionCode;
	}
	public Date getSubscriptionDuration() {
		return subscriptionDuration;
	}
	public void setSubscriptionDuration(Date subscriptionDuration) {
		this.subscriptionDuration = subscriptionDuration;
	}
	public Boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}
	public Profiles getProfileId() {
		return profileId;
	}
	public void setProfileId(Profiles profileId) {
		this.profileId = profileId;
	}
	
	
}
