package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "payment_event_detail", uniqueConstraints = @UniqueConstraint(name="detail_ck", columnNames = {"event_id", "payment_event_id"}))
public class PaymentEventDetail extends BaseEntity {
	
	private static final long serialVersionUID = 1678004039513227174L;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event eventId;
	
	@ManyToOne
	@JoinColumn(name = "payment_event_id")
	private PaymentEvent paymentId;
	
	public Event getEventId() {
		return eventId;
	}

	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}

	public PaymentEvent getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(PaymentEvent paymentId) {
		this.paymentId = paymentId;
	}
}
