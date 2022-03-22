package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class PriceListMember extends BaseEntity {
	
	private static final long serialVersionUID = -6555299478346827003L;
	private String priceName;
	private String priceCode;
	private Integer priceNominal;
	private Integer duration;
	
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
	
}
