package com.lawencon.community.dto.paymentevent;

public class GetPaymentEventByUserDtoDataRes {
	private String id;
	private String paymentEventCode;
	private String paymentEventInvoice;
	private Boolean isApprove;
	private String attachmentId;
	private String attachmentExtension;
	private String paymentId;
	private String paymentName;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentEventCode() {
		return paymentEventCode;
	}

	public void setPaymentEventCode(String paymentEventCode) {
		this.paymentEventCode = paymentEventCode;
	}

	public String getPaymentEventInvoice() {
		return paymentEventInvoice;
	}

	public void setPaymentEventInvoice(String paymentEventInvoice) {
		this.paymentEventInvoice = paymentEventInvoice;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentExtension() {
		return attachmentExtension;
	}

	public void setAttachmentExtension(String attachmentExtension) {
		this.attachmentExtension = attachmentExtension;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
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
