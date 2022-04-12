package com.lawencon.community.dto.paymentevent;

import java.util.List;

public class InsertPaymentEventDtoReq {
	private List<String> eventId;
	private String paymentId;

	public List<String> getEventId() {
		return eventId;
	}

	public void setEventId(List<String> eventId) {
		this.eventId = eventId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

}
