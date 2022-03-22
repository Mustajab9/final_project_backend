package com.lawencon.community.model;

import java.sql.Date;

import com.lawencon.base.BaseEntity;

public class Subscription extends BaseEntity {
	
	private static final long serialVersionUID = -7204808085626448204L;
	private String subscriptionCode;
	private Date subscriptionDuration;
	private Boolean isApprove;
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
