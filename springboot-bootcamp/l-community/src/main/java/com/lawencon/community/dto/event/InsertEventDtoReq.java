package com.lawencon.community.dto.event;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class InsertEventDtoReq {
	private String eventTitle;
	private String eventProvider;
	private String eventLocation;
	private BigInteger eventPrice;
	private Time eventTimeStart;
	private Time eventTimeEnd;
	private Date eventDateStart;
	private Date eventDateEnd;
	private String eventTypeId;
	private String categoryId;

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventProvider() {
		return eventProvider;
	}

	public void setEventProvider(String eventProvider) {
		this.eventProvider = eventProvider;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public BigInteger getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(BigInteger eventPrice) {
		this.eventPrice = eventPrice;
	}

	public Time getEventTimeStart() {
		return eventTimeStart;
	}

	public void setEventTimeStart(Time eventTimeStart) {
		this.eventTimeStart = eventTimeStart;
	}

	public Time getEventTimeEnd() {
		return eventTimeEnd;
	}

	public void setEventTimeEnd(Time eventTimeEnd) {
		this.eventTimeEnd = eventTimeEnd;
	}

	public Date getEventDateStart() {
		return eventDateStart;
	}

	public void setEventDateStart(Date eventDateStart) {
		this.eventDateStart = eventDateStart;
	}

	public Date getEventDateEnd() {
		return eventDateEnd;
	}

	public void setEventDateEnd(Date eventDateEnd) {
		this.eventDateEnd = eventDateEnd;
	}

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
