package com.lawencon.community.dto.event;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class GetAllEventDtoDataRes {
	private String id;
	private String eventCode;
	private String eventTitle;
	private String eventProvider;
	private BigInteger eventPrice;
	private Time eventTimeStart;
	private Time eventTimeEnd;
	private Date eventDateStart;
	private Date eventDateEnd;
	private Boolean isApprove;
	private String categoryId;
	private String categoryName;
	private String typeId;
	private String typeName;
	private String priceId;
	private String priceName;
	private String attachmentId;
	private String attachmentExtension;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
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
