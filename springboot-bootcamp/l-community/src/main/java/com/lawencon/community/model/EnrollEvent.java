package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "enroll_events", uniqueConstraints = { 
		@UniqueConstraint(name = "enroll_bk", columnNames = "enroll_code"),
		@UniqueConstraint(name = "enroll_ck", columnNames = { "profile_id", "event_id" }) 
})
public class EnrollEvent extends BaseEntity {

	private static final long serialVersionUID = -2392366745534807997L;

	@Column(name = "enroll_invoice", length = 20)
	private String enrollInvoice;

	@Column(name = "enroll_code", length = 5)
	private String enrollCode;

	@Column(name = "is_approve")
	private Boolean isApprove = false;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profiles profileId;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event eventId;

	@ManyToOne
	@JoinColumn(name = "attachment_id")
	private Attachment attachmentId;

	@ManyToOne
	@JoinColumn(name = "payment_id")
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

	public Event getEventId() {
		return eventId;
	}

	public void setEventId(Event eventId) {
		this.eventId = eventId;
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
