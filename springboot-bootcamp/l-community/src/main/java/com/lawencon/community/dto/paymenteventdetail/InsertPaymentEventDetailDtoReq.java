package com.lawencon.community.dto.paymenteventdetail;

public class InsertPaymentEventDetailDtoReq {
	private String paymentEventId;
	private String eventId;

	public String getPaymentEventId() {
		return paymentEventId;
	}

	public void setPaymentEventId(String paymentEventId) {
		this.paymentEventId = paymentEventId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
