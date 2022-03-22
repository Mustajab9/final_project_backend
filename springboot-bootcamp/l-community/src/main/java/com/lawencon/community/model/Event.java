package com.lawencon.community.model;

import java.sql.Date;
import java.sql.Time;

import com.lawencon.base.BaseEntity;

public class Event extends BaseEntity {
	
	private static final long serialVersionUID = 1023072540840191613L;
	private String eventTitle;
	private String eventCode;
	private String eventProvider;
	private Integer eventPrice;
	private Time eventTimeStart;
	private Time eventTimeEnd;
	private Date eventDateStart;
	private Date eventDateEnd;
	private Boolean isApprove;
	private EventType typeId;
	private PriceListEvent priceId;
	private Attachment attachmentId;
	
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getEventProvider() {
		return eventProvider;
	}
	public void setEventProvider(String eventProvider) {
		this.eventProvider = eventProvider;
	}
	public Integer getEventPrice() {
		return eventPrice;
	}
	public void setEventPrice(Integer eventPrice) {
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
	public Boolean getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}
	public EventType getTypeId() {
		return typeId;
	}
	public void setTypeId(EventType typeId) {
		this.typeId = typeId;
	}
	public PriceListEvent getPriceId() {
		return priceId;
	}
	public void setPriceId(PriceListEvent priceId) {
		this.priceId = priceId;
	}
	public Attachment getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Attachment attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	
	
}
