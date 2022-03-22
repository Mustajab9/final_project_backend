package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class EnrollDetail extends BaseEntity {
	
	private static final long serialVersionUID = 1678004039513227174L;
	private Event eventId;
	private EnrollEvent enrollId;
	
	public Event getEventId() {
		return eventId;
	}
	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}
	public EnrollEvent getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(EnrollEvent enrollId) {
		this.enrollId = enrollId;
	}
	
	
}
