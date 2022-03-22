package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class PriceListEvent extends BaseEntity {
	
	private static final long serialVersionUID = -2231255448266140900L;
	private String priceName;
	private String priceCode;
	private Integer priceNominal;
	
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
	public Integer getPriceNominal() {
		return priceNominal;
	}
	public void setPriceNominal(Integer priceNominal) {
		this.priceNominal = priceNominal;
	}
	
	
}
