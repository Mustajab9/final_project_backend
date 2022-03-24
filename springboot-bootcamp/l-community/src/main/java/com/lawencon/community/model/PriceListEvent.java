package com.lawencon.community.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "price_list_event", uniqueConstraints = {
		@UniqueConstraint(name="price_event_bk", columnNames = "price_code"),
		@UniqueConstraint(name="price_event_ck", columnNames = {"price_name", "price_code"})
})
public class PriceListEvent extends BaseEntity {
	
	private static final long serialVersionUID = -2231255448266140900L;
	
	@Column(name = "price_name", length=50)
	private String priceName;
	
	@Column(name = "price_code", length=5)
	private String priceCode;
	
	@Column(name="price_nominal")
	private BigInteger priceNominal;
	
	public String getPriceName() {
		return priceName;
	}
	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}
	public String getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	public BigInteger getPriceNominal() {
		return priceNominal;
	}
	public void setPriceNominal(BigInteger priceNominal) {
		this.priceNominal = priceNominal;
	}
	
	
}
