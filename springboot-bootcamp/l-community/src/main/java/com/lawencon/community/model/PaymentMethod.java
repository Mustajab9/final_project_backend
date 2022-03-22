package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class PaymentMethod extends BaseEntity {
	
	private static final long serialVersionUID = 5907302571448524320L;
	private String paymentName;
	private String paymentCode;
	
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	
	
}
