package com.lawencon.community.dto.paymentevent;

import java.util.List;

public class InsertPaymentEventDtoReq {
	private String enrollCode;
	private List<String> eventId;
	private String attachmentId;
	private String paymentId;

	public String getEnrollCode() {
		return enrollCode;
	}

	public void setEnrollCode(String enrollCode) {
		this.enrollCode = enrollCode;
	}

	public List<String> getEventId() {
		return eventId;
	}

	public void setEventId(List<String> eventId) {
		this.eventId = eventId;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

}
