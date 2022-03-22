package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class EnrollEvent extends BaseEntity {
	
	private static final long serialVersionUID = -2392366745534807997L;
	private String enrollInvoice;
	private String enrollCode;
	private Boolean isApprove;
	private Profiles profileId;
	private Attachment attachmentId;
	private PaymentMethod paymentId;
	
	public String getEnrollInvoice() {
		return enrollInvoice;
	}
	public void setEnrollInvoice(String enrollInvoice) {
		this.enrollInvoice = enrollInvoice;
	}
	public String getEnrollCode() {
		return enrollCode;
	}
	public void setEnrollCode(String enrollCode) {
		this.enrollCode = enrollCode;
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
	public Attachment getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Attachment attachmentId) {
		this.attachmentId = attachmentId;
	}
	public PaymentMethod getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(PaymentMethod paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
