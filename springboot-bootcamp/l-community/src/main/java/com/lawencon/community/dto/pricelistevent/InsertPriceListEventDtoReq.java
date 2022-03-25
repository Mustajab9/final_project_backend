package com.lawencon.community.dto.pricelistevent;

import java.math.BigInteger;

public class InsertPriceListEventDtoReq {
	private String priceName;
	private String priceCode;
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
