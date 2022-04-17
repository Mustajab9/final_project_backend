package com.lawencon.community.dto.event;

public class InvoiceEventDtoReq {
	private String profileName;
	private String invoice;
	private String eventTitle;
	private String eventLocation;
	private String typeName;
	private String eventDateStart;
	private String eventDateEnd;
	private String eventTimeStart;
	private String eventTimeEnd;
	private String paymentId;
	private String email;

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEventDateStart() {
		return eventDateStart;
	}

	public void setEventDateStart(String eventDateStart) {
		this.eventDateStart = eventDateStart;
	}

	public String getEventDateEnd() {
		return eventDateEnd;
	}

	public void setEventDateEnd(String eventDateEnd) {
		this.eventDateEnd = eventDateEnd;
	}

	public String getEventTimeStart() {
		return eventTimeStart;
	}

	public void setEventTimeStart(String eventTimeStart) {
		this.eventTimeStart = eventTimeStart;
	}

	public String getEventTimeEnd() {
		return eventTimeEnd;
	}

	public void setEventTimeEnd(String eventTimeEnd) {
		this.eventTimeEnd = eventTimeEnd;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
