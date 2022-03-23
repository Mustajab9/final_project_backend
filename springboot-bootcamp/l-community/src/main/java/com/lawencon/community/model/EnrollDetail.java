package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "enroll_detail", uniqueConstraints = @UniqueConstraint(name="detail_ck", columnNames = {"event_id", "enroll_id"}))
public class EnrollDetail extends BaseEntity {
	
	private static final long serialVersionUID = 1678004039513227174L;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event eventId;
	
	@ManyToOne
	@JoinColumn(name = "enroll_id")
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
