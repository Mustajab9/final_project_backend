package com.lawencon.community.dto.event;

public class GetReportIncomeEventDto {
	private String eventTitle;
	private Long totalPeople;
	private String eventProvider;
	private String eventPrice;
	private String eventDateStart;
	private String eventDateEnd;
	private String location;
	
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public Long getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(Long totalPeople) {
		this.totalPeople = totalPeople;
	}
	public String getEventProvider() {
		return eventProvider;
	}
	public void setEventProvider(String eventProvider) {
		this.eventProvider = eventProvider;
	}
	public String getEventPrice() {
		return eventPrice;
	}
	public void setEventPrice(String eventPrice) {
		this.eventPrice = eventPrice;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
