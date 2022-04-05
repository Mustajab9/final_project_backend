package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "payment_method", uniqueConstraints = {
		@UniqueConstraint(name="payment_bk", columnNames = "payment_code"),
		@UniqueConstraint(name="payment_ck", columnNames = {"payment_name", "payment_code"})
})
public class PaymentMethod extends BaseEntity {
	
	private static final long serialVersionUID = 5907302571448524320L;
	
	@FullTextField
	@Column(name = "payment_name", length=20)
	private String paymentName;
	
	@FullTextField
	@Column(name = "payment_code", length=5)
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
