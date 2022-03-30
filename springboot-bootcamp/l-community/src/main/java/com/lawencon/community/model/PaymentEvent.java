package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "payment_events", uniqueConstraints = @UniqueConstraint(name="payment_events_bk", columnNames = "payment_events_code"))
public class PaymentEvent extends BaseEntity {

	private static final long serialVersionUID = -5935000107670896042L;

	@Column(name = "payment_events_code", length=5)
	private String paymentEventCode;
	
	@Column(name = "payment_events_invoice", length=20)
	private String paymentEventInvoice;
	
	@Column(name = "is_approve")
	private Boolean isApprove = false;

	@ManyToOne
	@JoinColumn(name = "attachment_id")
	private Attachment attachmentId;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	private PaymentMethod paymentId;

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
