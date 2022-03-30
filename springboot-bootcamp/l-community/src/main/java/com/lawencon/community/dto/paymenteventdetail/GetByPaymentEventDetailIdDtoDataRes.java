package com.lawencon.community.dto.paymenteventdetail;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

public class GetByPaymentEventDetailIdDtoDataRes {
	private String id;
	private String eventId;
	private String eventCode;
	private String eventTitle;
	private String eventProvider;
	private BigInteger eventPrice;
	private Time eventTimeStart;
	private Time eventTimeEnd;
	private Date eventDateStart;
	private Date eventDateEnd;
	private Boolean isEventApprove;
	private String categoryId;
	private String categoryName;
	private String typeId;
	private String typeName;
	private String priceId;
	private String priceName;
	private String attachmentEventId;
	private String attachmentEventExtension;
	private String paymentEventId;
	private String paymentEventCode;
	private String paymentEventInvoice;
	private Boolean ispaymentEventApprove;
	private String attachmentpaymentEventId;
	private String attachmentpaymentEventExtension;
	private String paymentId;
	private String paymentName;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public Boolean getIsEventApprove() {
		return isEventApprove;
	}

	public void setIsEventApprove(Boolean isEventApprove) {
		this.isEventApprove = isEventApprove;
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

	public String getAttachmentEventId() {
		return attachmentEventId;
	}

	public void setAttachmentEventId(String attachmentEventId) {
		this.attachmentEventId = attachmentEventId;
	}

	public String getAttachmentEventExtension() {
		return attachmentEventExtension;
	}

	public void setAttachmentEventExtension(String attachmentEventExtension) {
		this.attachmentEventExtension = attachmentEventExtension;
	}

	public String getPaymentEventId() {
		return paymentEventId;
	}

	public void setPaymentEventId(String paymentEventId) {
		this.paymentEventId = paymentEventId;
	}

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

	public Boolean getIspaymentEventApprove() {
		return ispaymentEventApprove;
	}

	public void setIspaymentEventApprove(Boolean ispaymentEventApprove) {
		this.ispaymentEventApprove = ispaymentEventApprove;
	}

	public String getAttachmentpaymentEventId() {
		return attachmentpaymentEventId;
	}

	public void setAttachmentpaymentEventId(String attachmentpaymentEventId) {
		this.attachmentpaymentEventId = attachmentpaymentEventId;
	}

	public String getAttachmentpaymentEventExtension() {
		return attachmentpaymentEventExtension;
	}

	public void setAttachmentpaymentEventExtension(String attachmentpaymentEventExtension) {
		this.attachmentpaymentEventExtension = attachmentpaymentEventExtension;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
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
