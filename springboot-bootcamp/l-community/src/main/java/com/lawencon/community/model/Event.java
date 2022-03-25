package com.lawencon.community.model;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "events", uniqueConstraints = @UniqueConstraint(name="event_bk", columnNames = "event_code"))
public class Event extends BaseEntity {
	
	private static final long serialVersionUID = 1023072540840191613L;
	
	@Column(name = "event_title", length=100)
	private String eventTitle;
	
	@Column(name = "event_code", length=5)
	private String eventCode;
	
	@Column(name = "event_provider", length=100)
	private String eventProvider;
	
	@Column(name = "event_price")
	private BigInteger eventPrice;
	
	@Column(name = "event_time_start")
	private Time eventTimeStart;
	
	@Column(name = "event_time_end")
	private Time eventTimeEnd;
	
	@Column(name = "event_date_start")
	private Date eventDateStart;
	
	@Column(name = "event_date_end")
	private Date eventDateEnd;
	
	@Column(name = "is_approve")
	private Boolean isApprove;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private EventType typeId;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;
	
	@ManyToOne
	@JoinColumn(name = "price_id")
	private PriceListEvent priceId;
	
	@ManyToOne
	@JoinColumn(name = "attachment_id")
	private Attachment attachmentId;
	
	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

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
